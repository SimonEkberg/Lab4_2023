package service.site;

import domainModell.person.Person;
import domainModell.site.Site;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class FindAllSiteService extends BaseService<List<Site>> {
    @Override
    public List<Site> execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.SITE).getAll();
    }
}
