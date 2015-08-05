package ch15;
import java.util.*;

public class HttpLinksBean {
    protected Set links;
    protected String linksName = "default";
    
    public HttpLinksBean() {
        super();
        links = new HashSet();
    }
    public add(HttpLinkBean link){
        links.add(link);
    }
    public java.util.Iterator getIterator(){
        if(links == null)
            links = new HashSet();
        return links.iterator();
    }
    public java.lang.String getLinksName(){
        return linksName;
    }
    public void setLinksName(java.lang.String newLinkName){
        linksName = newLinkName;
    }
}
