package service.person;     //INTEGRSTIONTESTA!!!!!!!!!

import domainModell.person.Person;
import service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllPersonBySiteId extends BaseService<List<Person>> {

    private int siteId;
    public FindAllPersonBySiteId(int siteId) {
        this.siteId = siteId;
    }
    @Override
    public List<Person> execute() {
        return this.daoFactory.getPersonDao().getAll().stream().
                filter((p) -> p.getSiteId() == this.siteId).collect(Collectors.toList());
      /*  List<Person> personList = this.daoFactory.get(DaoFactory.type.PERSON).getAll();
        List<Person> personInSite = new ArrayList<>();
        for (Person p: personList){
            if(p.getSiteId() == siteId)
                personInSite.add(p);
        }
        return personInSite;*/
    }
}

