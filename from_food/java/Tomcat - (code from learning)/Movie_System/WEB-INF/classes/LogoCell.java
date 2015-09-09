import HTML.*;

public class LogoCell extends HTMLTableCell{
    
    /** Creates a new instance of LogoCell */
    public LogoCell() {
        super(HTMLTableCell.DATA);
        HTMLImage image = new HTMLImage("http://127.0.0.1:6669/Movies/images/pyroglyphics_logo.jpg", "Sams Logo");
        if(image == null)
            System.err.println("image is null");
        addObject(image);
    }
    
}
