package HTML;

public class HTMLInput extends HTMLObject {
    
    protected String type = null;
    protected String name = null;
    protected String input_value = null;
    protected String attributes = null;
    
    /** Creates a new instance of HTMLInput */
    public HTMLInput() {
    }
    protected void setType(String v){
        type = v;
    }
    public String getType(){
        return type;
    }
    public void setName(String v){
        name = v;
    }
    public String getName(){
        return name;
    }
    public void setValue(String v){
        input_value = v;
    }
    public String getValue(){
        return input_value;
    }
    protected void setAttributes(String value){
        attributes = value;
    }
    public String getAttributes(){
        return attributes;
    }
    
    public String toHTML() {
        StringBuffer html = new StringBuffer("<INPUT TYPE=\"");
        if(type!=null)
            html.append(type);
        html.append("\" NAME=\"");
        if(name!=null)
            html.append(name);
        html.append(" VALUE=\"" + input_value + "\"");
        if(attributes!=null)
            html.append(attributes);
        html.append(">");
        return html.toString();
    }
    
}
