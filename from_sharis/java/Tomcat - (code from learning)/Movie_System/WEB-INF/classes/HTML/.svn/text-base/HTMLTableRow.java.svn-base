package HTML;

public class HTMLTableRow extends HTMLObject {
    private String bgColor = "white";
    /** Creates a new instance of HTMLTableRow */
    public HTMLTableRow() {
    }
    public void setBackground(String color){
        bgColor = color;
    }
    public String getBackground(){
        return bgColor;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<TR COLOR=\"" + bgColor + "\">");
        for(int x=0;x<htmlObjects.size();x++){
            try{
                html.append(((HTMLObject)htmlObjects.elementAt(x)).toHTML());
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        html.append("</TR>\n");
        return html.toString();
    }
}
