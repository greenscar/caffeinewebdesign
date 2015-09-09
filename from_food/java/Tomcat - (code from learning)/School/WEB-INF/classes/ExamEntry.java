/*
 * ExamEntry is an abstract class of all entrys in an exam
 *    and will be extended by Header.class and Question.class
 * Created on April 24, 2003, 1:55 PM
 */
public abstract class ExamEntry {
    //examLocation is where in the exam this entry will show up.
    //Each ExamEntry will have a unique examLocation.
    private int examLocation;
    
    public int getLocation(){
        return examLocation;
    }
    public void setLocation(int loc){
        this.examLocation = loc;
    }
    public int modLocation(int change){
        examLocation += examLocation;
        return examLocation;
    }
    abstract String displayForm();
    abstract String displayToTake();
    abstract String displayToMod();
    abstract String displayToView();
    abstract String displayGraded();
    abstract void delete();
}
