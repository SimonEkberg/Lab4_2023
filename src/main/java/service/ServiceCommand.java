package service;

import repository.DaoFactory;


public interface ServiceCommand<T> {
    public void init(DaoFactory daoFactory);
    public T execute();
}
