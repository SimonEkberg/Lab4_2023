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
import java.util.Optional;

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

/*	@Override
	public Optional<Person> get(int id) throws NoSuchElementException, SQLException {
		Person person = null;
		//	try {
		ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id" +
				" FROM persons WHERE id=" + id);
		//	if (!resultSet.next())
		//		throw new NoSuchElementException("The person with id " + id + " doesn't exist in database");
		//	else
		if (resultSet.next()) {
			String personName = resultSet.getString(2);
			int birthYear = resultSet.getInt(3);
			int siteId = resultSet.getInt(4);
			person = new Person(id, personName, birthYear, siteId);
			/*	person = new Person(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3),
						resultSet.getInt(4));*/
			//	} catch (SQLException e) {
			//		e.printStackTrace();
			//	}
	//	}
//		return Optional.ofNullable(person);
//	}
	@Override
	public Optional<Person> get(int id) throws SQLException {
		Person person = null;
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id" +
					" FROM persons WHERE id=" + id);
			if (resultSet.next()) {
				String personName = resultSet.getString(2);
				int birthYear = resultSet.getInt(3);
				int siteId = resultSet.getInt(4);
				person = new Person(id, personName, birthYear, siteId);
			}
			return Optional.ofNullable(person);
		}


	@Override
	public List<Person> getAll() {
		ArrayList<Person> list = new ArrayList<>();
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id" +
					" FROM persons");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getInt(1),
						resultSet.getString(2).trim(),
						resultSet.getInt(3),
						resultSet.getInt(4))
				);
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Optional<Person> save(Person p) {
		ResultSet resultSet;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"INSERT INTO persons (name, birth_year, site_id) " +
							"VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, p.getPersonName());
			preparedStatement.setInt(2, p.getBirthYear());
			preparedStatement.setInt(3, p.getSiteId());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				return Optional.of(new Person(resultSet.getInt(1), p.getPersonName(), p.getBirthYear(), p.getSiteId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(p);
	}

	public Optional<Person> update(Person p) throws NoSuchElementException {
		PreparedStatement preparedStatement = null;
		int rowCount = 0;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"UPDATE persons SET name=?, birth_year=?, site_id=? WHERE id=" + p.getId(), Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, p.getPersonName());
			preparedStatement.setInt(2, p.getBirthYear());
			preparedStatement.setInt(3, p.getSiteId());
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rowCount == 0) {
			throw new NoSuchElementException("Person with ID " + p.getId() + " does not exist in the database.");
		}
		return Optional.of(new Person(p.getId(), p.getPersonName(), p.getBirthYear(), p.getSiteId()));
	}


	/*@Override
	public Optional<Person> delete(int id) {
		Person person = null;
		PreparedStatement preparedStatement = null;
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id" +
					" FROM persons WHERE id=" + id);
	/*		if (!resultSet.next())
				throw new NoSuchElementException("The person with id " + id + " doesn't exist in database");
			else*/
				/*person = new Person(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3),
						resultSet.getInt(4));*/
		/*	person = new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
					resultSet.getInt(4));
			person.setSiteId(resultSet.getInt(1));
			resultSet.next();
			person.setPersonName(resultSet.getString(2));
			resultSet.next();
			person.setBirthYear(resultSet.getInt(4));
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(person);
	}*/
	@Override
	public Optional<Person> delete(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		Person person = null;
		ResultSet resultSet = dbConManagerSingleton.excecuteQuery(
				"SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id);
		if (resultSet.next()) {
			String personName = resultSet.getString(2);
			int birthYear = resultSet.getInt(3);
			int siteId = resultSet.getInt(4);
			person = new Person(id, personName, birthYear, siteId);
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			}
		return Optional.of(person);
		}


	public List<Person> getPersonsBySiteId(int id) throws SQLException {
		ArrayList<Person> list = new ArrayList<>();
		ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id " +
				"FROM persons " +
				"WHERE site_id = " + id);
		while (resultSet.next()) {
			list.add(new Person(resultSet.getInt(1),
					resultSet.getString(2).trim(),
					resultSet.getInt(3),
					resultSet.getInt(4))
			);
			dbConManagerSingleton.close();
		}
		return list;
	}
}



