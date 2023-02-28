package service;

import domainModell.room.Room;
import repository.RoomDao;

import java.util.List;

public class RoomService {
    private RoomDao roomDao;
    public RoomService(){
        this(new RoomDao());
    }
    public RoomService(RoomDao roomDao){
        this.roomDao=roomDao;
    }
    public Room getRoom(int id){
        return roomDao.get(id);
    }
    public List<Room> findAllRoom(){
        return roomDao.getAll();
    }
    public Room saveRoom(Room room){
        return roomDao.save(room);
    }
    public Room updateRoom(Room room){
        return roomDao.update(room);
    }
    public Room deleteRoom(int id){
        return roomDao.delete(id);
    }
}
