package domainModell.site;

import java.util.ArrayList;
import java.util.List;

public class SiteId {
    private int id;
 //   private List<Long> siteIdList = new ArrayList();

    public SiteId(int id) {
        this.id = id;
     //   siteIdList.add(id);
    }
    public SiteId(){
        this(0);
    }

    public int getId() {
        return this.id;
    }
 /*   public List<Long> getSiteIdList() {
        return siteIdList;
    }*/
}
