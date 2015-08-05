package HTML;

public class HTMLImage extends HTMLObject {
    private String image = null;
    private String text = null;
    /** Creates a new instance of HTMLImage */
    public HTMLImage() {
    }
    public HTMLImage(String image_string, String text_string){
        image = image_string;
        text = text_string;
    }
    public void setImage(String v){
        image = v;
    }
    public String getImage(){
        return image;
    }
    public void setText(String t){
        text = t;
    }
    public String getText(){
        return text;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<img src=\"");
        if(image != null)
            html.append(image);
        html.append("\" alt=\"");
        if(text != null)
            html.append(text);
        html.append("\">");
        return html.toString();
    }
    
}
