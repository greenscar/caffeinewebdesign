package HTML;
public class HTMLTextInput extends HTMLInput{
    private int size = -1;
    private int maxlength = -1;
    
    /** Creates a new instance of HTMLTextInput */
    public HTMLTextInput() {
        setType("TEXT");
    }
    public void setSize(int x){
        size = x;
    }
    public int getSize(){
        return size;
    }
    public void setMaxLength(int x){
        maxlength = x;
    }
    public int getMaxLength(){
        return maxlength;
    }
    public String toHTML(){
        StringBuffer attribute = new StringBuffer("");
        if(size > -1)
            attribute.append(" SIZE=" + size);
        if(maxlength > 1)
            attribute.append(" MAXLENGTH=" + maxlength);
        setAttributes(attribute.toString());
        return super.toHTML();
    }
    
}
