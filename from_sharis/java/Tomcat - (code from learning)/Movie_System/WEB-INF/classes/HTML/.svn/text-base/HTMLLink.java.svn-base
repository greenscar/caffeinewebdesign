package HTML;

public class HTMLLink extends HTMLObject{
    private String href = null;
    
    /** Creates a new instance of HTMLLink */
    public HTMLLink() {
    }
    public HTMLLink(String href_string, HTMLObject obj){
        href = href_string;
        addObject(obj);
    }
    public void setHREF(String value){
        href = value;
    }
    public String getHREF(){
        return href;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<a href=\"");
        html.append(href + "\">");
        for(int x=0;x< htmlObjects.size();x++){
            try{
                HTMLObject o = (HTMLObject)htmlObjects.elementAt(x);
                html.append(o.toHTML() + "\n");
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }
            html.append("</a>");
        }
        return html.toString();
    }
    
}
