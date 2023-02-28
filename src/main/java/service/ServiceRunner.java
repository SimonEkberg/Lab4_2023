package service;

import db.DbConnectionManager;
import repository.DaoFactory;

public class ServiceRunner<T>{
    private final ServiceCommand<T> service;

    public ServiceRunner(ServiceCommand<T> serviceCommand) {
        this.service = serviceCommand;
    }

    public T execute(){
        service.init(new DaoFactory());
        DbConnectionManager.getInstance().open();
        T result = this.service.execute();
        DbConnectionManager.getInstance().close();
        return result;
    }
}
