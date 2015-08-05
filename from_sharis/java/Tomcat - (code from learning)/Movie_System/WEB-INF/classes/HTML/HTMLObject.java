package HTML;
import java.util.Vector;
public abstract class HTMLObject {
    //Vector used to hold other HTMLObjects
    protected Vector htmlObjects = null;
    //Static Alignment values
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    
    public static final int TOP = 0;
    public static final int MIDDLE = 1;
    public static final int BOTTOM = 2;
    //Set the objects initial alignment to LEFT
    private int alignment = LEFT;
    private int vAlign = TOP;
    private int hAlign = MIDDLE;
    public abstract String toHTML();
    private String backgroundColor = null;
    
    public HTMLObject() {
        htmlObjects = new Vector(5);
    }
    public void addObject(HTMLObject value){
        if(value != null)
            htmlObjects.addElement(value);
    }
    public boolean removeObject(HTMLObject v){
        if(v != null)
            htmlObjects.removeElement(v);
        return false;
    }
    public void setBackgroundColor(String v){
        if(v != null)
            backgroundColor = v;
    }
    public String getBackgroundColor(){
        return backgroundColor;
    }
    public void setVerticalAlign(int v){
        vAlign = v;
    }
    public int getVerticalAlign(){
        return vAlign;
    }
    public void setHorizontalAlign(int v){
        hAlign = v;
    }
    public int getHorizontalAlign(){
        return hAlign;
    }
    public void setAlignment(int v){
        if(v >= LEFT && v <= RIGHT)
            alignment = v;
    }
    public int getAlignment(){
        return alignment;
    }
}
