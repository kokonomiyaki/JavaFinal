import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private int ansIndex;

    //@Param1 is the question being asked
    //@Param2 is an arraylist containing the answers to the question in seperate strings
    //@Param3 is the index in the previous arralist containing the correct answer
    public Question(String question, ArrayList<String> answers, int ansIndex){
        this.question = question;
        this.answers = answers;
        this.ansIndex = ansIndex;
    }

    //@returns if the index of the choice matches the answer index
    public boolean isCorrect(int answer){
        return answer==ansIndex;
    }

    //@returns a string of the question
    public String getQuestion(){
        return question;
    }

    //@returns an arraylist containg the possible answers in seperate strings
    public ArrayList<String> getAnswers(){
        return answers;
    }

}