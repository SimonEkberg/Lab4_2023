package repository;

import db.DbConnectionManager;
import domainModell.person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * DAO for the persistent handling of a Person object. It manages all
 * CRUD operations and conversion between the object world person and
 * the relational version person (DB version).
 * Due to the use of a DbConnectionManager the DAO doesn't need to
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in
 * relational sql strings, tables, columns and result sets with the database.
 *
 * @author Simon Siljamäki Ekberg
 */
public class PersonDao extends BasicDao<Person> implements Dao<Person> {
    public PersonDao() {
        super();
    }
    public PersonDao(DbConnectionManager dbConnectionManager) {
        super(dbConnectionManager);
    }

    @Override
    public Optional<Person> get(int id) throws SQLException {
        return super.get("SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id);
    }

    @Override
    public List<Person> getAll() throws SQLException {
        return super.getAll("SELECT id, name, birth_year, site_id FROM persons");
    }
    public Optional<Person> save(Person person) throws SQLException {
        return super.save("INSERT INTO persons (name, birth_year, site_id) VALUES (?, ?, ?)",
                                    person.getPersonName(), person.getBirthYear(), person.getSiteId());
    }

    @Override
    public Optional<Person> update(Person person) throws NoSuchElementException, SQLException {
        return super.update("UPDATE persons SET name=?, birth_year=?, site_id=? WHERE id=?",
                                    person.getPersonName(), person.getBirthYear(), person.getSiteId(),person.getId());
    }
   @Override
   public Optional<Person> delete(int id) throws SQLException {
       Optional<Person> person = super.get("SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id);
       if (person.isEmpty())
           return person;
       super.delete("DELETE FROM persons WHERE id=" + id);
       return person;

   }
    public List<Person> getPersonsBySiteId(int id) throws SQLException {
        return super.getAll("SELECT id, name, birth_year, site_id FROM persons WHERE site_id = " + id);
    }
    @Override
    protected Person convertResultSetToDomainObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int birthYear = resultSet.getInt("birth_year");
        int siteId = resultSet.getInt("site_id");
        return new Person(id, name, birthYear, siteId);
    }
    @Override
    protected Person convertFromSaveOrUpdateToDomainObject(Optional<ResultSet> resultSet, List<Object> argLlist) throws SQLException {
        int id;
        if (resultSet != null) {
            id = resultSet.get().getInt(1);
        }else
            id = (int) argLlist.get(3);
        String name = (String) argLlist.get(0);
        int birthyear = (int) argLlist.get(1);
        int siteId = (int) argLlist.get(2);
        return new Person(id, name, birthyear, siteId);
    }
}



