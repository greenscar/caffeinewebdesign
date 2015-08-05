/*
 * Exam.java
 *
 * Created on April 24, 2003, 1:42 PM
 */
import java.util.*;

public class Exam {
    private String name;    
    private float number;
    private Date dateCreated;
    private boolean enabled = false;
    private boolean displayAfterTaking;
    private ArrayList entries; //a Vector of ExamEntry Objects
    
    public Exam(){}
    public Exam(String name, float num, boolean enabled, boolean display) {
        this.name = name;
        this.enabled = enabled;
        this.number = num;
        this.displayAfterTaking = display;
    }
    // Gets and Sets of arguments
    void setName(String newName){
        this.name = newName;
    }
    String getName(){
        return this.name;
    }
    void setNumber(float newNum){
        this.number = newNum;
    }
    float getNumber(){
        return this.number;
    }
    Date getDateCreated(){
        return this.dateCreated;
    }
    void setDateCreated(Date aDate){
        this.dateCreated = aDate;
    }
    void enable() {
        this.enabled = true;
    }
    void disable() {
        this.enabled = false;
    }
    boolean isEnabled(){
        return this.enabled;
    }
    void setDisplayAfterTaking(boolean dispAftTak) {
        this.displayAfterTaking = dispAftTak;
    }
    boolean getDisplayAfterTaking() {
        return this.displayAfterTaking;
    }
    // END Gets and Sets of arguments
    
    //METHODS
    void addEntry(int examLoc, ExamEntry newEntry) {
        this.entries.add(examLoc, newEntry);
    }
    void appendEntry(ExamEntry newEntry){
        this.entries.add(newEntry);
    }
    void replaceEntry(int examLoc, Question newEntry) {
        this.entries.set(examLoc, newEntry);
    }
    void deleteEntry(int entryNum){
        this.entries.remove(entryNum);
    }
    ExamEntry getEntryByLoc(int examLoc){
        return (ExamEntry)(this.entries.get(examLoc));
    }
    Question getQuestionByNum(int questNum){
        for(int i=0; i<this.entries.size(); i++){
            if(this.entries.get(i) instanceof Question){
                Question q = (Question)this.entries.get(i);
                if(q.getQuestionNum() == questNum)
                    return q;
            }
        }
        return null;
    }
    
    //END METHODS
}
