package domainModell.person;

import domainModell.site.Site;
import domainModell.site.SiteId;
import repository.SiteDao;

public class Main {
    public static void main(String[] args) {
        Site site = new Site(1, "McDonalds", "STOCKHOLM");
     /*   Site site2 = new Site(0," apa", "GÄVLE");
        Person person = new Person(1, "Simon", 1989);
        Person person1 = new Person(2, "Test", 2000);
        System.out.println(SiteId.getInstance().getSiteIdList());*/
        SiteDao siteDao = new SiteDao();
        siteDao.save(site);
    }
}
