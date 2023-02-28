package service.room;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import repository.DaoFactory;
import repository.RoomDao;
import service.BaseService;
import service.CleaningManagerServiceException;
import service.ServiceCommand;

import java.util.NoSuchElementException;

public class DeleteRoomByIdService extends BaseService<Room> {
    private int id;
    public DeleteRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Room execute() {
        try{
            return (Room) daoFactory.get(DaoFactory.FactoryType.ROOM).delete(id);
        }catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
    }
}
