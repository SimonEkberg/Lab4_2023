package service;

import domainModell.room.Room;
import repository.RoomDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomService {
    private RoomDao roomDao;
    public RoomService(){
        this(new RoomDao());
    }
    public RoomService(RoomDao roomDao){
        this.roomDao=roomDao;
    }
    public Optional<Room> getRoom(int id) throws SQLException {
        return roomDao.get(id);
    }
    public List<Room> findAllRoom() throws SQLException {
        return roomDao.getAll();
    }
    public Optional<Room> saveRoom(Room room) throws SQLException {
        return roomDao.save(room);
    }
    public Optional<Room> updateRoom(Room room) throws SQLException {
        return roomDao.update(room);
    }
    public Optional<Room> deleteRoom(int id) throws SQLException {
        return roomDao.delete(id);
    }
}
