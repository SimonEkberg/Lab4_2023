package service;

import domainModell.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repository.RoomDao;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class RoomServiceTest {
    RoomService instance;
    @Mock
    RoomDao roomDaoMock;
    @BeforeEach
    void setUp(){
        openMocks(this);
        instance = new RoomService(roomDaoMock);
    }
    @Test
    void getRoom() {
        System.out.println("getRoom");
        int id = 1;
        Room expResult = new Room(id, 8,88.8,"LIVINGROOM");
        when(roomDaoMock.get(id)).thenReturn(Optional.of(expResult));
        Optional<Room> result = instance.getRoom(id);
        assertTrue(expResult.equals(result));
        assertEquals(expResult.toString(), result.toString());
        verify(roomDaoMock, times(1)).get(id);
    }

    @Test
    void getAllRoom() {
        System.out.println("FindAll");
        List<Room> expResult = List.of(new Room(1, 22, 20, ""),
                new Room(2, 21, 30, "LIVINGROOM"),
                new Room(2, 33, 150, "WAREHOUSE"));
        when(roomDaoMock.getAll()).thenReturn(List.of(new Room(1, 22, 20, "UNSPECIFIED"),
                new Room(2, 21, 30, "LIVINGROOM"),
                new Room(2, 33, 150, "WAREHOUSE")));
        List<Room> result = instance.findAllRoom();
        assertArrayEquals(expResult.toArray(), result.toArray());
        assertEquals(expResult, result);
        assertTrue(expResult.equals(result));
        assertTrue(result instanceof List<Room>);
        verify(roomDaoMock, times(1)).getAll();
    }

    @Test
    void saveRoom() {
        System.out.println("saveRoom");
        Room expResult = new Room(1,22,22, "BEDROOM");
        when(roomDaoMock.save(expResult)).thenReturn(Optional.of(expResult));
        Optional<Room> result = instance.saveRoom(expResult);
        assertTrue(expResult.equals(result));
        assertEquals(expResult, result);
        verify(roomDaoMock, times(1)).save(expResult);
    }

    @Test
    void updateRoom() {
        System.out.println("updateRoom");
        Room expResult = new Room(1, 88, 88, null);
        when(roomDaoMock.update(expResult)).thenReturn(Optional.of(expResult));
        Optional<Room> result = instance.updateRoom(expResult);
        assertTrue(expResult.equals(result));
        assertEquals(expResult, result);
        verify(roomDaoMock, times(1)).update(expResult);
    }

    @Test
    void deleteRoom() {
        System.out.println("deleteRoom");
        int id = 1;
        Room room = new Room(1, 22, 22, "TOILET");
        when(roomDaoMock.delete(id)).thenReturn(Optional.of(room));
        Room expResult = room;
        Optional<Room> result = instance.deleteRoom(id);
        assertEquals(expResult, result);
        verify(roomDaoMock, times(1)).delete(id);
    }

}