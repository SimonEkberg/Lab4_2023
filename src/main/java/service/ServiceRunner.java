package service;

import db.DbConnectionManager;
import repository.DaoFactory;
import service.logging.Logger;

import java.util.NoSuchElementException;

public class ServiceRunner<T>{
    private final ServiceCommand<T> service;
    public ServiceRunner(ServiceCommand<T> serviceCommand) {
        this.service = serviceCommand;
    }
    public T execute() throws CleaningManagerServiceException {
        service.init(new DaoFactory());
        DbConnectionManager.getInstance().open();
        try {
            T result = this.service.execute();
            Class infoClass = this.service.getClass();
            String nameOfServiceClass = infoClass.getSimpleName();
            Logger.get().info(() -> String.format("Name of service: %s: Reult: %s", nameOfServiceClass, result));
            return result;
        }catch (NoSuchElementException e) {
         //   Logger.get().error(() ->);
            throw new CleaningManagerServiceException(e.getMessage());
        }finally {
            DbConnectionManager.getInstance().close();
        }
    }
}
