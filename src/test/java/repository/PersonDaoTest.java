package repository;

import db.DbConnectionManager;
import domainModell.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonDaoTest {
    PersonDao instance;
    Person personInstance = new Person(1, "Simon", 1989, 0);
    @Mock
    DbConnectionManager dbConnectionManagerMock;
    @Mock
    ResultSet resultSetMock;
    @Mock
    PreparedStatement preparedStatementMock;

    @BeforeEach
    public void setup() {
        openMocks(this);
        instance = new PersonDao(dbConnectionManagerMock);
    }

    @Test
    void testGet() throws SQLException {
        System.out.println("testGet");
        int id = 1;
        Person expResult = personInstance;
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getString(2)).thenReturn(expResult.getPersonName());
        when(resultSetMock.getInt(3)).thenReturn(expResult.getBirthYear());
        when(resultSetMock.next()).thenReturn(true);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year FROM persons WHERE id=" + id))
                .thenReturn(resultSetMock);

        Person result = instance.get(id);

        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getPersonName(), result.getPersonName());
        assertEquals(expResult.getBirthYear(), result.getBirthYear());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt(1);
        verify(resultSetMock, times(1)).getString(2);
        verify(resultSetMock, times(1)).getInt(3);
        verify(resultSetMock, times(1)).next();
        verify(dbConnectionManagerMock, times(1)).excecuteQuery(
                "SELECT id, name, birth_year FROM persons WHERE id=" + id);
    }

    @Test
    void testGetAll() throws SQLException {
        System.out.println("testGetAll");
        List<Person> expResult = List.of(personInstance,
                new Person(2, "Benke", 1948, 0));
        when(resultSetMock.getInt(1)).thenReturn(personInstance.getId()).thenReturn(2);
        when(resultSetMock.getString(2)).thenReturn(personInstance.getPersonName()).thenReturn("Benke");
        when(resultSetMock.getInt(3)).thenReturn(personInstance.getBirthYear()).thenReturn(1948);
        when(resultSetMock.getInt(4)).thenReturn(personInstance.getSiteId()).thenReturn(0);
        when(resultSetMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year, site_id FROM persons"))
                .thenReturn(resultSetMock);
        List<Person> result = instance.getAll();

        assertEquals(2, result.size());
        assertEquals(personInstance.getId(), result.get(0).getId());
        assertEquals(personInstance.getPersonName(), result.get(0).getPersonName());
        assertEquals(2, result.get(1).getId());
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(2)).getInt(1);
        verify(resultSetMock, times(2)).getString(2);
        verify(resultSetMock, times(2)).getInt(3);
        verify(resultSetMock, times(3)).next();
        verify(dbConnectionManagerMock, times(1))
                .excecuteQuery("SELECT id, name, birth_year, site_id FROM persons");
    }

    @Test
    void testSave() throws SQLException {
        System.out.println("testSave");
        Person expResult = personInstance;
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getString(2)).thenReturn(expResult.getPersonName());
        when(resultSetMock.getInt(3)).thenReturn(expResult.getBirthYear());
        when(dbConnectionManagerMock.prepareStatement(
                "INSERT INTO persons (name, birth_year) " +
                        "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);

        Person result = instance.save(expResult);

        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getPersonName(), result.getPersonName());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt(1);
        verify(preparedStatementMock, times(1)).setString(1, expResult.getPersonName());
        verify(preparedStatementMock, times(1)).setInt(2, expResult.getBirthYear());

        verify(resultSetMock, times(1)).next();
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("INSERT INTO persons (name, birth_year) " +
                        "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
    }

    @Test
    void testUpdate() throws Exception {
        System.out.println("testUpdate");
        int id = 1;
        Person expResult = personInstance;
        when(dbConnectionManagerMock.prepareStatement("UPDATE persons SET name=?, birth_year=? WHERE id=" + id, Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);
        when(resultSetMock.getString(2)).thenReturn(expResult.getPersonName());
        when(resultSetMock.getInt(3)).thenReturn(expResult.getBirthYear());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Person result = instance.update(personInstance);
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(preparedStatementMock, times(1)).setString(1, expResult.getPersonName());
        verify(preparedStatementMock, times(1)).setInt(2, expResult.getBirthYear());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("UPDATE persons SET name=?, birth_year=? WHERE id=" + id, Statement.RETURN_GENERATED_KEYS);
    }

    @Test
    void testDelete() throws SQLException {
        System.out.println("testDelete");
        int id = 1;
        Person expResult = personInstance;
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getString(2)).thenReturn(expResult.getPersonName());
        when(resultSetMock.getInt(3)).thenReturn(expResult.getBirthYear());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year FROM persons WHERE id=" + id))
                .thenReturn(resultSetMock);
        when(dbConnectionManagerMock.prepareStatement("DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Person result = instance.delete(id);
        assertEquals(expResult, result);

        verify(resultSetMock, times(1)).getInt(1);
        verify(resultSetMock, times(1)).getString(2);
        verify(resultSetMock, times(1)).getInt(3);
        verify(dbConnectionManagerMock, times(1)).prepareStatement(
                "DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        verify(preparedStatementMock, times(1)).setInt(1, id);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

}

