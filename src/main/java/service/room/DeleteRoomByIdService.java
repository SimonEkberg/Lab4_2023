package service.room;

import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteRoomByIdService extends BaseService<Optional> {
    private int id;
    public DeleteRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Optional execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.ROOM).delete(id);
    }
}
