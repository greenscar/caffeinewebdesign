package HTML;

public class HTMLDocument extends HTMLObject {
    private String title = null;
    private String backgroundImage= null;
    private String backgroundColor = null;
    private String textColor = null;
    private String linkColor = null;
    private String vlink = null;
    private String alink = null;
    
    public HTMLDocument() {
    }
    public HTMLDocument(String v){
        title = v;
    }
    public void setTitle(String v){
        title = v;
    }
    public String getTitle(){
        return title;
    }
    public void setBackgroundImage(String v){
        backgroundImage = v;
    }
    public String getBackgroundImage(){
        return backgroundImage;
    }
    public void setBackgroundColor(String v){
        backgroundColor = v;
    }
    public String getBackgroundColor(){
        return backgroundColor;
    }
    public void setTextColor(String v){
        textColor = v;
    }
    public String getTextColor(){
        return textColor;
    }
    public void setLinkColor(String v){
        linkColor = v;
    }
    public String getLinkColor(){
        return linkColor;
    }
    public void setVLinkColor(String v){
        vlink = v;
    }
    public String getVLinkColor(){
        return vlink;
    }
    public void setALinkColor(String v){
        alink = v;
    }
    public String getALinkColor(){
        return alink;
    }
    public String toHTML() {
        StringBuffer document = new StringBuffer("<html><head>\n");
        document.append("<title>");
        if(title != null)
            document.append(title);
        document.append("</title></head>\n\n<body ");
        if(backgroundImage != null)
            document.append("BACKGROUND=\"" + backgroundImage + "\" ");
        String color = getBackgroundColor();
        if(color != null)
            document.append("BGCOLOR=\"" + color + "\" ");
        String textColor = getTextColor();
        if(textColor != null)
            document.append("TEXT=\"" + textColor + "\" ");
        if(linkColor != null)
            document.append("LINK=\"" + linkColor + "\" ");
        if(vlink != null)
            document.append("VLINK=\"" + vlink + "\" ");
        if(alink != null)
            document.append("ALINK=\"" + alink + "\" ");
        document.append(">\n");
        //Iterate through all objects in the htmlObjects Vector
        for(int x = 0; x < htmlObjects.size(); x++){
            try{
                HTMLObject t = (HTMLObject)htmlObjects.elementAt(x);
                document.append(t.toHTML() + "\n");
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        //Close the document
        document.append("\n</body>\n</html>\n");
        return document.toString();
    }
    
}
