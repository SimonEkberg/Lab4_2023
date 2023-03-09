package service;

import db.DbConnectionManager;
import repository.DaoFactory;

import java.sql.SQLException;

/**
* @author
* @param <T>
*/
public abstract class BaseService<T> implements ServiceCommand<T>{
    protected DbConnectionManager dbConnectionManager;
    protected DaoFactory daoFactory;

    @Override
    public void init(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public abstract T execute() throws SQLException;
}
