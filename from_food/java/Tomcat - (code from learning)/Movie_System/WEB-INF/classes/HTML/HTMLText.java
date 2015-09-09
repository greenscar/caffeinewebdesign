package HTML;

public class HTMLText extends HTMLObject {
    private String text = new String("");
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private boolean center = false;
    private boolean performatted = false;
    
    public HTMLText() {
    }
    
    public HTMLText(String v){
        text = v;
    }
    public void setText(String v){
        text = v;
    }
    public String getText(){
        return text;
    }
    public void setBold(boolean v){
        bold = v;
    }
    public boolean isBold(){
        return bold;
    }
    public void setItalic(boolean v){
        italic = v;
    }
    public boolean isItalic(){
        return italic;
    }
    public void setUnderline(boolean v){
        underline = v;
    }
    public boolean isUnderline(){
        return underline;
    }
    public void setCenter(boolean v){
        center = v;
    }
    public boolean isCenter(){
        return center;
    }
    public void setPreformatted(boolean v){
        performatted = v;
    }
    public boolean isPreformatted(){
        return performatted;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer(text);
        if(isBold()){
            html.insert(0, "<B>");
            html.append("</B>");
        }
        if(isItalic()){
            html.insert(0, "<I>");
            html.append("</I>");
        }
        if(isUnderline()){
            html.insert(0, "<U>");
            html.append("</U>");
        }
        if(isCenter()){
            html.insert(0, "<CENTER>");
            html.append("</CENTER>");
        }
        if(isPreformatted()){
            html.insert(0, "<PRE>");
            html.append("</PRE>");
        }
        return html.toString();
    }
    
}
