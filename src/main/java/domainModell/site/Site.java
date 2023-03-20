package domainModell.site;

import domainModell.person.Person;

public class Site {
    private int id;
    private SiteName siteName;
    private SiteCity siteCity;

    public Site(int id, String name, String siteCity) {
        this.siteName = new SiteName(name);
        this.siteCity = new SiteCity(siteCity);
        this.id = id;
      //  setSiteid(id);
    }
   /* private void setSiteid(int id){
        if(SiteId.getInstance().addSiteSiteId(id)){
            this.id = id;
        }else {
            throw new IllegalArgumentException("Site ID already exist");
        }
    }*/

    public int getId() {
        return this.id;
    }

    public String getName() {
        return siteName.getName();
    }

    public void setName(String name) {
        this.siteName = new SiteName(name);
    }

    public String getSiteCity() {
        return siteCity.getSiteCity();
    }

    public void setSiteCity(String city) {
        this.siteCity = new SiteCity(city);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
                ", Stad: " + siteCity.getSiteCity() +
                '}';
    }
}
