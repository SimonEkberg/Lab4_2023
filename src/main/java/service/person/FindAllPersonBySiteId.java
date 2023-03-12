package service.person;     //INTEGRSTIONTESTA!!!!!!!!!

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllPersonBySiteId extends BaseService<List<Person>> {

    private int siteId;
    public FindAllPersonBySiteId(int siteId) {
        this.siteId = siteId;
    }
    @Override
    public List<Person> execute() throws SQLException {
      /*  return this.daoFactory.getPersonDao().getAll().stream().
                filter((p) -> p.getSiteId() == this.siteId).collect(Collectors.toList());*/
        return this.daoFactory.getPersonDao().getPersonsBySiteId(siteId);
    }
}

