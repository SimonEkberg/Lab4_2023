package domainModell.site;

import java.util.ArrayList;
import java.util.List;

public class SiteId {
    private static SiteId instance;
    private List<Integer> siteIdList;

    private SiteId() {
        siteIdList = new ArrayList<>();
    }

    public static SiteId getInstance() {
        if (instance == null) {
            instance = new SiteId();
        }
        return instance;
    }

    public List<Integer> getSiteIdList() {
        return siteIdList;
    }

    public boolean addSiteSiteId(int siteId) {
        if (isValidSiteIdForSites(siteId)) {
            siteIdList.add(siteId);
            return true;
        } else {
            throw new IllegalArgumentException("Site ID already exists: " + siteId);
        }
    }

    public boolean addEntitySiteId(int siteId) {
        if (isValidSiteIdForEntities(siteId)) {
            return true;
        } else {
            throw new IllegalArgumentException("Site ID not found: " + siteId);
        }
    }

    private boolean isValidSiteIdForEntities(int siteId) {
        return siteId == 0 || siteIdList.contains(siteId);
    }
    private boolean isValidSiteIdForSites(int siteId) {
        return siteId != 0 || !siteIdList.contains(siteId);
    }
}
