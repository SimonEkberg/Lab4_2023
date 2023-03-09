package service;

import repository.DaoFactory;

import java.sql.SQLException;


public interface ServiceCommand<T> {
    public void init(DaoFactory daoFactory);
    public T execute() throws SQLException;
}
