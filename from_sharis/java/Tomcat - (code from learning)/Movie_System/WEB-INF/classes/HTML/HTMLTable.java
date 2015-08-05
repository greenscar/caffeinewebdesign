package HTML;

public class HTMLTable extends HTMLObject {
    private int width = 0;
    private int height = 0;
    private int border = 0;
    private int cellspacing = -1;
    private int cellpadding = -1;
    private HTMLObject caption = null;
    private boolean percentage_width = true;
    public HTMLTable() {
    }
    
    public void setCaption(HTMLObject v){
        caption = v;
    }
    public HTMLObject getCaption(){
        return caption;
    }
    public void setBorder(int v){
        border = v;
    }
    public int getBorder(){
        return border;
    }
    public void setCellSpacing(int v){
        cellspacing = v;
    }
    public int getCellSpacing(){
        return cellspacing;
    }
    public void setCellPadding(int v){
        cellpadding = v;
    }
    public int getCellPadding(){
        return cellpadding;
    }
    public void setWidth(int x){
        width = x;
        percentage_width = true;
    }
    public void setWidthByPixel(int x){
        width = x;
        percentage_width = false;
    }
    public int getWidth(){
        return width;
    }
    public void setHeight(int x){
        height = x;
    }
    public int getHeight(){
        return height;
    }
    public String toHTML() {
        StringBuffer html = new StringBuffer("<TABLE");
        if(width > 0){
            html.append(" WIDTH=" + width);
            if(percentage_width)
                html.append("%");
        }
        if(height > 0)
            html.append(" HEIGHT=" + height);
        if(border > -1)
            html.append(" BORDER=" + border);
        if(cellspacing > -1)
            html.append(" CELLSPACING=" + cellspacing);
        if(cellpadding > -1)
            html.append(" CELLPADDING=" + cellpadding);
        String color = getBackgroundColor();
        if(color != null)
            html.append(" BGCOLOR=\"" + color + "\" ");
        html.append(">\n");
        if(caption != null)
            html.append("\n<CAPTION>" + caption.toHTML() + "</CAPTION>\n");
        for(int x=0;x<htmlObjects.size();x++){
            try{
                HTMLObject ho = (HTMLObject)htmlObjects.elementAt(x);
                html.append(ho.toHTML() + "\n");
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        html.append("\n</TABLE>");
        return html.toString();
    }
}
