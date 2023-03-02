package domainModell.site;

public class SiteCity {
    private String city;
    private enum SiteCities{STOCKHOLM, GÄVLE, MALMÖ, GÖTEBORG;}

    public SiteCity(String city) {
        this.city = siteCityFromString(city);
    }
    public String getCity(){
        return this.city;
    }
    public void setSiteCity(String roomType){
        this.city = siteCityFromString(roomType);
    }

    private String siteCityFromString(String city) {
        if (city == null || city.trim().isEmpty())
            return "UNSPECIFIED";
        for (SiteCities c : SiteCities.values()) {
            if (this.city.equalsIgnoreCase("UNSPECIFIED") || city.toString().equalsIgnoreCase(city))
                return this.city.trim();
        }
        throw new IllegalArgumentException("Invalid room type: " + city);
    }
}
