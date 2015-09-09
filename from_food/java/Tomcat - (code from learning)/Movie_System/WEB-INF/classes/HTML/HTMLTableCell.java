package HTML;

/**
 *
 * @author  jsandlin
 */
public class HTMLTableCell extends HTMLObject {
    private int horizontal = LEFT;
    private int vertical = MIDDLE;
    private int type = DATA;
    private int colspan = 1;
    private int width = 0;
    public static final int TOP = 3;
    public static final int MIDDLE = 4;
    public static final int BOTTOM = 5;
    public static final int HEADING = 6;
    public static final int DATA = 7;
    
    public void setWidth(int x){
        width = x;
    }
    public int getWidth(){
        return width;
    }
    public HTMLTableCell() {
    }
    public HTMLTableCell(int cell_type){
        type = cell_type;
    }
    public void setColspan(int v){
        colspan = v;
    }
    public int getColspan(){
        return colspan;
    }
    public void setHorizontalAlign(int v){
        horizontal = v;
    }
    public int getHorizontalAlign(){
        return horizontal;
    }
    public void setVerticalAlign(int v){
        vertical = v;
    }
    public int getVerticalAlign(){
        return vertical;
    }
    public String toHTML(){
        StringBuffer html = new StringBuffer("");
        String tag = null;
        String valign = null;
        String align = null;
        switch(vertical){
            case TOP:
                valign = "TOP";
                break;
            case MIDDLE:
                valign = "MIDDLE";
                break;
            case BOTTOM:
                valign = "BOTTOM";
                break;
        }
        switch(horizontal){
            case LEFT:
                valign = "LEFT";
                break;
            case CENTER:
                valign = "CENTER";
                break;
            case RIGHT:
                valign = "RIGHT";
                break;
        }
        if(type == DATA)
            tag = new String("TD");
        else
            tag = new String("TH");
        html.append("<" + tag + " VALIGN=" + valign + " ALIGN=" + align);
        if(width > 0)
             html.append(" WIDTH=" + width);
        html.append(" COLSPAN=" + colspan + ">");
        for(int x = 0; x < htmlObjects.size(); x++){
            try{
                html.append(((HTMLObject)htmlObjects.elementAt(x)).toHTML());
            }catch(ArrayIndexOutOfBoundsException e){
                //Log the error to stderr
                System.err.println(e.getMessage());
            }catch(Exception e){
                //Log the error to stderr
                System.err.println(e.getMessage());
            }
        }
        html.append("</" + tag + ">");
        return html.toString();
    }
}
