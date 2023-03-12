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
 *
 * @author awi
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
    public List<Person> getAll() throws SQLException {
        ArrayList<Person> list = new ArrayList<>();
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id" +
                " FROM persons");
        while (resultSet.next()) {
            list.add(new Person(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4))
            );
        }
        return list;
    }

    @Override
    public Optional<Person> save(Person person) throws SQLException {
        ResultSet resultSet;
        PreparedStatement preparedStatement = null;
        preparedStatement = dbConManagerSingleton.prepareStatement(
                "INSERT INTO persons (name, birth_year, site_id) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getPersonName());
        preparedStatement.setInt(2, person.getBirthYear());
        preparedStatement.setInt(3, person.getSiteId());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 1) {
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Optional.of(new Person(resultSet.getInt(1), person.getPersonName(), person.getBirthYear(), person.getSiteId()));
        }
        return Optional.ofNullable(person);
    }

    	@Override
        public Optional<Person> update(Person person) throws NoSuchElementException, SQLException {
            PreparedStatement preparedStatement = null;
            int rowCount = 0;
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE persons SET name=?, birth_year=?, site_id=? WHERE id=" + person.getId(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getPersonName());
            preparedStatement.setInt(2, person.getBirthYear());
            preparedStatement.setInt(3, person.getSiteId());
            rowCount = preparedStatement.executeUpdate();
            return rowCount == 1 ? Optional.of(person) : Optional.empty();
        }

    @Override
    public Optional<Person> delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Person person = null;
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery(
                "SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id);
        if (resultSet.next()) {
            person = new Person(id,
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4));
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return Optional.ofNullable(person);
    }


    public List<Person> getPersonsBySiteId(int id) throws SQLException {
        ArrayList<Person> list = new ArrayList<>();
        ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, site_id " +
                "FROM persons " +
                "WHERE site_id = " + id);
        while (resultSet.next()) {
            list.add(new Person(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4))
            );
        }
        return list;
    }
}



