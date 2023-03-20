package repository;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import repository.Dao;
import repository.DaoFactory;

class RoomDaoTest {
    RoomDao instance;
    Room roomInstance = new Room(1, 88, 88.0, "LIVINGROOM");
    @Mock
    DbConnectionManager dbConnectionManagerMock;
    @Mock
    ResultSet resultSetMock;
    @Mock
    PreparedStatement preparedStatementMock;

    @BeforeEach
    public void setup() {
        openMocks(this);
        instance = new RoomDao();
    }
 /*   @BeforeEach
    public void setup() throws SQLException {
        openMocks(this);
        Connection connectionMock = mock(Connection.class);
        when(dbConnectionManagerMock.getConnection()).thenReturn(connectionMock);
        instance = new RoomDao(dbConnectionManagerMock);
    }*/




    @Test
    void testGet() throws SQLException {
        System.out.println("testGet");
        int id = 1;
        Room expResult = roomInstance;
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getInt(2)).thenReturn(expResult.getRoomNumber());
        when(resultSetMock.getDouble(3)).thenReturn(expResult.getRoomArea());
        when(resultSetMock.getString(4)).thenReturn(expResult.getRoomType());
        when(resultSetMock.next()).thenReturn(true);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, room_number, room_area," +
                " room_type FROM rooms WHERE id=" + id))
                .thenReturn(resultSetMock);

        Optional<Room> result = instance.get(id);

      //  assertEquals(expResult.getId(), result.getId());
     //   assertEquals(expResult.getRoomNumber(), result.getRoomNumber());
    //    assertEquals(expResult.getRoomType(), result.getRoomType());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt(1);
        verify(resultSetMock, times(1)).getInt(2);
        verify(resultSetMock, times(1)).getDouble(3);
        verify(resultSetMock, times(1)).getString(4);
        verify(resultSetMock, times(1)).next();
        verify(dbConnectionManagerMock, times(1)).excecuteQuery(
                "SELECT id, room_number, room_area," +
                        " room_type FROM rooms WHERE id=" + id);
    }

    @Test
    void testGetAll() throws SQLException {
        System.out.println("testGetAll");
        List<Room> expResult = List.of(roomInstance,
                new Room(2, 4, 44.4, ""));
        when(resultSetMock.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSetMock.getInt(2)).thenReturn(88).thenReturn(4);
        when(resultSetMock.getDouble(3)).thenReturn(88.0).thenReturn(44.4);
        when(resultSetMock.getString(4)).thenReturn("LIVINGROOM").thenReturn("UNSPECIFIED");
        when(resultSetMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, room_number, room_area," +
                " room_type FROM rooms"))
                .thenReturn(resultSetMock);
        List<Room> result = instance.getAll();

        assertEquals(2, result.size());
        assertEquals(roomInstance.getId(), result.get(0).getId());
        assertEquals(roomInstance.getRoomNumber(), result.get(0).getRoomNumber());
        assertEquals(2, result.get(1).getId());
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(2)).getInt(1);
        verify(resultSetMock, times(2)).getInt(2);
        verify(resultSetMock, times(2)).getDouble(3);
        verify(resultSetMock, times(2)).getString(4);
        verify(resultSetMock, times(3)).next();
        verify(dbConnectionManagerMock, times(1))
                .excecuteQuery("SELECT id, room_number, room_area," +
                        " room_type FROM rooms");
    }

    @Test
    void testSave() throws SQLException {
        System.out.println("testSave");
        Room expResult = roomInstance;
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getInt(2)).thenReturn(expResult.getRoomNumber());
        when(resultSetMock.getDouble(3)).thenReturn(expResult.getRoomArea());
        when(resultSetMock.getString(4)).thenReturn(expResult.getRoomType());
        when(dbConnectionManagerMock.prepareStatement(
                "INSERT INTO rooms (room_number, room_area, room_type) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);

        Optional<Room> result = instance.save(expResult);

  //      assertEquals(expResult.getId(), result.getId());
  //      assertEquals(expResult.getRoomNumber(), result.getRoomNumber());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(resultSetMock, times(1)).getInt(1);
        verify(preparedStatementMock, times(1)).setInt(1, expResult.getRoomNumber());
        verify(preparedStatementMock, times(1)).setDouble(2, expResult.getRoomArea());
        verify(preparedStatementMock, times(1)).setString(3, expResult.getRoomType());
        verify(resultSetMock, times(1)).next();
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("INSERT INTO rooms (room_number, room_area, room_type) " +
                        "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    }

    @Test
    void testUpdate() throws Exception {
        System.out.println("testUpdate");
        int id = 1;
        Room expResult = roomInstance;
        when(dbConnectionManagerMock.prepareStatement("UPDATE rooms SET room_number=?, room_area=?, " +
                "room_type=? WHERE id=" + id, Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);
        when(resultSetMock.getInt(1)).thenReturn(expResult.getRoomNumber());
        when(resultSetMock.getDouble(2)).thenReturn(expResult.getRoomArea());
        when(resultSetMock.getString(3)).thenReturn(expResult.getRoomType());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Optional<Room> result = instance.update(roomInstance);
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));

        verify(preparedStatementMock, times(1)).setInt(1, expResult.getRoomNumber());
        verify(preparedStatementMock, times(1)).setDouble(2, expResult.getRoomArea());
        verify(preparedStatementMock, times(1)).setString(3, expResult.getRoomType());
        verify(preparedStatementMock, times(1)).executeUpdate();
        verify(dbConnectionManagerMock, times(1))
                .prepareStatement("UPDATE rooms SET room_number=?, room_area=?, " +
                        "room_type=? WHERE id=" + id, Statement.RETURN_GENERATED_KEYS);
    }

    @Test
    void testDelete() throws SQLException {
        System.out.println("testDelete");
        int id = 1;
        Room expResult = new Room(id, 101, 25.0, "");
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(expResult.getId());
        when(resultSetMock.getInt(2)).thenReturn(expResult.getRoomNumber());
        when(resultSetMock.getDouble(3)).thenReturn(expResult.getRoomArea());
        when(resultSetMock.getString(4)).thenReturn(expResult.getRoomType());
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(dbConnectionManagerMock.excecuteQuery("SELECT id, room_number, room_area, room_type FROM rooms WHERE id=" + id))
                .thenReturn(resultSetMock);
        when(dbConnectionManagerMock.prepareStatement("DELETE FROM rooms WHERE id = ?", Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        Optional<Room> result = instance.delete(id);
        assertEquals(expResult, result);

        verify(resultSetMock, times(1)).getInt(1);
        verify(resultSetMock, times(1)).getInt(2);
        verify(resultSetMock, times(1)).getDouble(3);
        verify(resultSetMock, times(1)).getString(4);
        verify(dbConnectionManagerMock, times(1)).prepareStatement(
                "DELETE FROM rooms WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        verify(preparedStatementMock, times(1)).setInt(1, id);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }
}
