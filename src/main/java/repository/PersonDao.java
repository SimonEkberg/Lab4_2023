package repository;

import db.DbConnectionManager;
import domainModell.person.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAO for the persistent handling of a Person object. It manages all
 * CRUD operations and conversion between the object world student and
 * the relational version person (DB version).
 * Due to the use of a DbConnectionManager the DAO doesn't need to
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in 
 * relational sql strings, tables, columns and result sets with the database.
 * @author awi
 *
 */
public class PersonDao implements Dao<Person> {

	DbConnectionManager dbConManagerSingleton = null;
	public PersonDao() {
		this(new DbConnectionManager());
		dbConManagerSingleton = DbConnectionManager.getInstance();
	}
	public PersonDao(DbConnectionManager dbConnectionManager) {
		this.dbConManagerSingleton = dbConnectionManager;
	}

	@Override

	public Person get(int id) throws NoSuchElementException {
		Person person = null;
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM persons WHERE id=" + id);
			if (!resultSet.next())
				throw new NoSuchElementException("The person with id " + id + " doesn't exist in database");
			else
				person = new Person(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person> list = new ArrayList<>();
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM persons");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getInt(1),
						resultSet.getString(2).trim(),
						resultSet.getInt(3))
				);
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Person save(Person t) {
		ResultSet resultSet;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"INSERT INTO persons (name, birth_year) " +
							"VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, t.getPersonName());
			preparedStatement.setInt(2, t.getBirthYear());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				return new Person(resultSet.getInt(1), t.getPersonName(), t.getBirthYear());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public Person update(Person t) throws NoSuchElementException {
		PreparedStatement preparedStatement = null;
		int rowCount = 0;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"UPDATE persons SET name=?, birth_year=? WHERE id=" + t.getId(), Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, t.getPersonName());
			preparedStatement.setInt(2, t.getBirthYear());
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rowCount == 0) {
			throw new NoSuchElementException("Person with ID " + t.getId() + " does not exist in the database.");
		}
		return new Person(t.getId(), t.getPersonName(), t.getBirthYear());
	}


	@Override
	public boolean delete(int id) {
		PreparedStatement preparedStatement = null;
		int rowCount = 0;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rowCount == 0)
			throw new NoSuchElementException("Person with ID " + id + " does not exist in the database.");
		return true;
	}


}



