package exam;

/*
 * Header.java
 *
 * Created on April 24, 2003, 2:01 PM
 */
public final class Header extends ExamEntry {
    private String value;
    /** Creates a new instance of Header */
    public Header(String v) {
        this.value = v;
    }
    void changeValue(String newHeader) {
	processMod(newHeader);
    }
    void processMod(String newHeader) {
	this.value = newHeader;
    }
    String displayForm() {return null;}
    String displayGraded() {return null;}
    String displayToMod() {return null;}
    String displayToTake() {return null;}
    String displayToView() {return null;}
    void delete() {}
    
    
}
