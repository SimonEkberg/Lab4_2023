package spike;

import domainModell.person.Person;
import domainModell.site.Site;
import repository.SiteDao;
import service.ServiceRunner;
import service.person.FindAllPersonService;
import service.person.SavePersonService;
import service.person.UpdatePersonService;
import service.site.FindAllSiteService;
import service.site.GetSiteByIdService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainSite {
    public static void main(String[] args) throws SQLException {
        int id =1;
     /*   Site site = new Site(4, "IT specialisten", "STOCKHOLM");
        SiteDao siteDao = new SiteDao();
        siteDao.get(id);*/
        ServiceRunner<Site> serviceRunner1 = new ServiceRunner(new GetSiteByIdService(id));
        serviceRunner1.execute();
     /*   Person person = new Person(72, "Simon Ekberg", 2019,1);
        ServiceRunner<Person> serviceRunner1 = new ServiceRunner(new UpdatePersonService(person));
        serviceRunner1.execute();*/
   /*     ServiceRunner<List<Site>> serviceRunner = new ServiceRunner(new FindAllSiteService());
        Optional<List<Site>> siteList = serviceRunner.execute();
        for(Site p: siteList){
            System.out.println(p);
        }*/
    }
}
