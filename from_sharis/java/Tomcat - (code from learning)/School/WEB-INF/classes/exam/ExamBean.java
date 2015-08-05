/*
 * Exam.java
 *
 * Created on April 24, 2003, 1:42 PM
 */
package exam;

import java.util.*;

public class ExamBean {
    private String name;    
    private float number;
    private Date dateCreated;
    private boolean enabled = false;
    private boolean displayAfterTaking;
    private ArrayList entries; //a Vector of ExamEntry Objects
    
    public ExamBean(){
    }
    public ExamBean(String name, float num, boolean enabled, boolean display) {
        this.name = name;
        this.enabled = enabled;
        this.number = num;
        this.displayAfterTaking = display;
    }
    
    // setXXX
    void setName(String newName){
        this.name = newName;
    }
    void setNumber(float newNum){
        this.number = newNum;
    }
    void setDateCreated(Date aDate){
        this.dateCreated = aDate;
    }
    void setEnabled(boolean e){
        this.enabled = e;
    }
    void setDisplayAfterTaking(boolean dispAftTak) {
        this.displayAfterTaking = dispAftTak;
    }
    void disable() {
        this.enabled = false;
    }
    
    // getXXX
    String getName(){
        return this.name;
    }
    float getNumber(){
        return this.number;
    }
    Date getDateCreated(){
        return this.dateCreated;
    }
    boolean getDisplayAfterTaking() {
        return this.displayAfterTaking;
    }
    boolean getEnabled(){
        return this.enabled;
    }
    boolean isEnabled(){
        return this.enabled;
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
