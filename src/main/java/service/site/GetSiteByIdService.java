package service.site;

import domainModell.person.Person;
import domainModell.site.Site;
import repository.Dao;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class GetSiteByIdService extends BaseService<Optional<Site>> {
    private int id;
    public GetSiteByIdService(int id) {
        this.id = id;
    }
    @Override
    public Optional<Site> execute() throws SQLException {
        Dao<Site> dao = daoFactory.get(DaoFactory.type.SITE);
        return dao.get(this.id);
    }
}
