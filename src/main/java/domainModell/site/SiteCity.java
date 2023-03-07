package domainModell.site;

import domainModell.room.RoomType;

public class SiteCity {
    private String siteCity;
    private enum SiteCities{STOCKHOLM, GÄVLE, MALMÖ, GÖTEBORG;}

    public SiteCity(String siteCity) {
        this.siteCity = cityTypefromString(siteCity);
    }
    public String getSiteCity(){
        return this.siteCity;
    }
    public void setSiteCity(String siteCity){
        this.siteCity = cityTypefromString(siteCity);
    }

    private String cityTypefromString(String siteCity) {
        if (siteCity == null || siteCity.trim().isEmpty())
            return "UNSPECIFIED";
        for (SiteCity.SiteCities type : SiteCity.SiteCities.values()) {
            if (siteCity.equalsIgnoreCase("UNSPECIFIED") || type.toString().equalsIgnoreCase(siteCity))
                return siteCity.trim();
        }
        throw new IllegalArgumentException("Invalid city: " + siteCity);
    }
}
