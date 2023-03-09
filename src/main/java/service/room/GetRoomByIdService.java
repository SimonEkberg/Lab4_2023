package service.room;

import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class GetRoomByIdService extends BaseService<Optional> {

    private int id;
    public GetRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Optional execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.ROOM).get(this.id);
    }
}
