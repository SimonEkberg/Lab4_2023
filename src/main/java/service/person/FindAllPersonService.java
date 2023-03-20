package service.person;

import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class FindAllPersonService extends BaseService<List> {
    @Override
    public List execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.PERSON).getAll();
    }
}