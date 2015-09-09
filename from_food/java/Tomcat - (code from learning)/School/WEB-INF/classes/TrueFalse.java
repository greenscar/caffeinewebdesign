/*
 * TrueFalse.java
 *
 * Created on April 24, 2003, 2:10 PM
 */
public final class TrueFalse extends Question {
    private boolean solution;
    private String question;
    
    /** Creates a new instance of TrueFalse */
    public TrueFalse(String question, boolean solution) {
        this.question = question;
        this.solution = solution;
    }
    
    void changeQuestion(String newQuestion) {
        this.question = newQuestion;
    }
    
    void changeSolution(boolean newSolution) {
        this.solution = newSolution;
    }
    void delete(){}
    String displayForm(){return null;}
    String displayGraded() {return null;}
    String displayToMod() {return null;}
    String displayToTake() {return null;}
    String displayToView() {return null;}
}
