package exam;

/*
 * MultipleChoice.java
 *
 * Created on April 24, 2003, 2:15 PM
 */
public final class MultipleChoice extends Question {
    private String question;
    private String[] choice;
    private char solution;
    
    /** Creates a new instance of MultipleChoice */
    public MultipleChoice(String ques, String[] ch, char solution) {
        this.question = ques;
        this.solution = solution;
        for(int i=0; i<4;i++){
            choice[i] = ch[i];
        }   
    }
    public String getQuestion(){
        return this.question;
    }
    public String[] getChoices(){
        return choice;
    }
    public char getSolution(){
        return this.solution;
    }
    void changeQuestion(String newQuestion) {
        this.question = newQuestion;
    }
    void changeSolutions(java.util.Vector newSolutions) {
    }
    void delete(){}
    String displayForm(){return null;}
    String displayGraded() {return null;}
    String displayToMod() {return null;}
    String displayToTake() {return null;}
    String displayToView() {return null;}
    
}
