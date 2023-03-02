package domainModell.site;

import domainModell.person.Person;

public class Site {
    private SiteId id;
    private SiteName siteName;
    private SiteCity siteCity;

    public Site(int id, String name, String siteCity) {
        this.id = new SiteId(id);
        this.siteName = new SiteName(name);
        this.siteCity = new SiteCity(siteCity);
    }

    public long getId() {
        return this.id.getId();
    }

    public String getName() {
        return siteName.getName();
    }

    public void setName(String name) {
        this.siteName = new SiteName(name);
    }

    public String getSiteCity() {
        return siteCity.getCity();
    }

    public void setSiteCity(String city) {
        this.siteCity = new SiteCity(city);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) { //if same object
            return true;
        }
        if (o == null || getClass() != o.getClass()) { //if null or not the same class
            return false;
        }
        Site site = (Site) o;
        return getId() == site.getId() &&
                getName() == site.getName() &&
                getSiteCity() == site.getSiteCity();
    }

    @Override
    public String toString() {
        return "Site{" +
                "Id: " + id +
                ", Namn: " + siteName.getName() +
                ", Stad: " + siteCity.getCity() +
                '}';
    }
}
