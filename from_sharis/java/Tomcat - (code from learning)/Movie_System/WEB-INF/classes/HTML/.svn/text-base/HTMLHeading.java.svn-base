package HTML;
public class HTMLHeading extends HTMLObject{
    private String text = null;
    public static final int H1=1;
    public static final int H2=2;
    public static final int H3=3;
    public static final int H4=4;
    public static final int H5=5;
    public static final int H6=6;
    private int heading = H1;
    
    /** Creates a new instance of HTMLHeading */
    public HTMLHeading() {
    }
    public HTMLHeading(String txt, int head){
        text = txt;
        heading = head;
    }
    public void setText(String v){
        text = v;
    }
    public String getText(){
        return text;
    }
    public void setHeadingLevel(int v){
        heading = v;
    }
    public int getHeadingLevel(){
        return heading;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<H" + heading);
        switch(getAlignment()){
            case HTMLObject.LEFT:
                html.append(" ALIGN=\"LEFT\"");
                break;
            case HTMLObject.CENTER:
                html.append(" ALIGN=\"CENTER\"");
                break;
            case HTMLObject.RIGHT:
                html.append(" ALIGN=\"RIGHT\"");
                break;
        }
        html.append(">");
        if(text != null)
            html.append(text);
        html.append("</H" + heading + ">");
        return html.toString();
    }
    
}
