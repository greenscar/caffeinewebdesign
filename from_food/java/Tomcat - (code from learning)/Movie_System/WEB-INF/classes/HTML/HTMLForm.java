package HTML;
import java.util.*;

public class HTMLForm extends HTMLObject {
    public String action = null;
    private boolean post_method = false;
    /** Creates a new instance of HTMLForm */
    public HTMLForm() {
    }
    public void setAction(String v){
        action = v;
    }
    public String getAction(){
        return action;
    }
    public void setPostMethod(boolean value){
        post_method = value;
    }
    public boolean isPostmethod(){
        return post_method;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<FORM ACTION=\"");
        if(action != null)
            html.append(action);
        html.append("\"");
        html.append(" METHOD=");
        if(post_method)
            html.append("POST");
        else
            html.append("GET");
        String color = getBackgroundColor();
        if(color != null)
            html.append(" BGCOLOR=\"" + color + "\" ");
        html.append(">\n");
        for(int x=0;x<htmlObjects.size();x++){
            try{
                HTMLObject ho= (HTMLObject)htmlObjects.elementAt(x);
                html.append(ho.toHTML() + "\n");
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        html.append("\n</FORM>");
        if(getAlignment() == CENTER){
            html.insert(0, "<CENTER>");
            html.append("</CENTER>");
        }
        return html.toString();
    }
}
