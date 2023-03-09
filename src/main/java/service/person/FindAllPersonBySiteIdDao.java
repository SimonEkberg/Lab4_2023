package service.person;

import domainModell.person.Person;
import repository.PersonDao;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class FindAllPersonBySiteIdDao extends BaseService<List<Person>> {
    private int siteId;
    public FindAllPersonBySiteIdDao(int siteId) {
        this.siteId = siteId;
    }
    @Override
    public List<Person> execute() throws SQLException {
        return new PersonDao().getPersonsBySiteId(siteId);
    }
}
