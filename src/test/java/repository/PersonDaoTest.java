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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonDaoTest {
    PersonDao instance;
    Optional<Person> personInstance = Optional.of(new Person(0, "Simon", 1989, 0));
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
        Optional<Person> expResult = personInstance;
        when(resultSetMock.getInt("id")).thenReturn(expResult.get().getId());
        when(resultSetMock.getString("name")).thenReturn(expResult.get().getPersonName());
        when(resultSetMock.getInt("birth_year")).thenReturn(expResult.get().getBirthYear());
        when(resultSetMock.getInt("site_id")).thenReturn(expResult.get().getSiteId());
        when(resultSetMock.next()).thenReturn(true);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id))
                .thenReturn(resultSetMock);

        Optional<Person> result = instance.get(id);

        assertEquals(expResult.get().getId(), result.get().getId());
        assertEquals(expResult.get().getPersonName(), result.get().getPersonName());
        assertEquals(expResult.get().getBirthYear(), result.get().getBirthYear());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt("id");
        verify(resultSetMock, times(1)).getString("name");
        verify(resultSetMock, times(1)).getInt("birth_year");
        verify(resultSetMock, times(1)).next();
        verify(dbConnectionManagerMock, times(1)).excecuteQuery(
                "SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id);
    }

    @Test
    void testGetAll() throws SQLException {
        System.out.println("testGetAll");
        List<Person> expResult = List.of(new Person(0, "Simon", 1989, 0),
                new Person(2, "Benke", 1948, 0));
        when(resultSetMock.getInt("id")).thenReturn(personInstance.get().getId()).thenReturn(2);
        when(resultSetMock.getString("name")).thenReturn(personInstance.get().getPersonName()).thenReturn("Benke");
        when(resultSetMock.getInt("birth_year")).thenReturn(personInstance.get().getBirthYear()).thenReturn(1948);
        when(resultSetMock.getInt("site_id")).thenReturn(personInstance.get().getSiteId()).thenReturn(0);
        when(resultSetMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year, site_id FROM persons"))
                .thenReturn(resultSetMock);
        List<Person> result = instance.getAll();

        assertEquals(2, result.size());
        assertEquals(personInstance.get().getId(), result.get(0).getId());
        assertEquals(personInstance.get().getPersonName(), result.get(0).getPersonName());
        assertEquals(2, result.get(1).getId());
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(2)).getInt("id");
        verify(resultSetMock, times(2)).getString("name");
        verify(resultSetMock, times(2)).getInt("birth_year");
        verify(resultSetMock, times(2)).getInt("site_id");
        verify(resultSetMock, times(3)).next();
        verify(dbConnectionManagerMock, times(1))
                .excecuteQuery("SELECT id, name, birth_year, site_id FROM persons");
    }

    @Test
    void testSave() throws SQLException {
        System.out.println("testSave");
        Optional<Person> expResult = personInstance;
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.getInt("id")).thenReturn(expResult.get().getId());  //Sätter id till 0, behöver undersöka konvertering i PersonDao
        when(resultSetMock.getString("name")).thenReturn(expResult.get().getPersonName());
        when(resultSetMock.getInt("birth_year")).thenReturn(expResult.get().getBirthYear());
        when(resultSetMock.getInt("site_id")).thenReturn(personInstance.get().getSiteId());
        when(dbConnectionManagerMock.prepareStatement(
                "INSERT INTO persons (name, birth_year, site_id) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);

        Optional<Person> result = instance.save(new Person(
                personInstance.get().getId(),
                personInstance.get().getPersonName(),
                personInstance.get().getBirthYear(),
                personInstance.get().getSiteId()));

        assertEquals(expResult.get().getId(), result.get().getId());
        assertEquals(expResult.get().getPersonName(), result.get().getPersonName());
        assertEquals(expResult.get().getBirthYear(), result.get().getBirthYear());
        assertEquals(expResult.get().getSiteId(), result.get().getSiteId());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt(1);
        verify(preparedStatementMock, times(1)).setObject(1, expResult.get().getPersonName());
        verify(preparedStatementMock, times(1)).setObject(2, expResult.get().getBirthYear());

        verify(resultSetMock, times(1)).next();
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("INSERT INTO persons (name, birth_year, site_id) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    }

 /*   @Test
    void testUpdate() throws Exception {
        System.out.println("testUpdate");
        int id = 0;
        Optional<Person> expResult = personInstance;
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn(expResult.get().getPersonName());
        when(resultSetMock.getInt("birth_year")).thenReturn(expResult.get().getBirthYear());
        when(resultSetMock.getInt("site_id")).thenReturn(expResult.get().getSiteId());
        when(dbConnectionManagerMock.prepareStatement("UPDATE persons SET name=?, birth_year=?, site_id=?" +
                " WHERE id=" + id, Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);

        Optional<Person> result = instance.update(new Person(
                id,
                personInstance.get().getPersonName(),
                personInstance.get().getBirthYear(),
                personInstance.get().getSiteId()));
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));*/

  /*      verify(preparedStatementMock, times(1)).setObject(1, expResult.get().getPersonName());
        verify(preparedStatementMock, times(1)).setObject(2, expResult.get().getBirthYear());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("UPDATE persons SET name=?, birth_year=?, site_id=?" +
                        " WHERE id=" + id, Statement.RETURN_GENERATED_KEYS);*/
  //  }

    @Test
    void testDelete() throws SQLException {
        System.out.println("testDelete");
        int id = 1;
        Optional<Person> expResult = personInstance;
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id")).thenReturn(expResult.get().getId());
        when(resultSetMock.getString("name")).thenReturn(expResult.get().getPersonName());
        when(resultSetMock.getInt("birth_year")).thenReturn(expResult.get().getBirthYear());
        when(resultSetMock.getInt("site_id")).thenReturn(expResult.get().getSiteId());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, name, birth_year, site_id FROM persons WHERE id=" + id))
                .thenReturn(resultSetMock);
        when(dbConnectionManagerMock.prepareStatement("DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

     //   Optional<Person> result = instance.delete(String.valueOf(id));
      //  assertEquals(expResult, result);

    /*    verify(resultSetMock, times(1)).getInt(1);
        verify(resultSetMock, times(1)).getString(2);
        verify(resultSetMock, times(1)).getInt(3);*/
  /*      verify(dbConnectionManagerMock, times(1)).prepareStatement(
                "DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        verify(preparedStatementMock, times(1)).executeUpdate();*/
    }

}

