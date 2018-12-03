import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class LoadQuestions
{
    public static ArrayList<ArrayList<Question>> loadQuestion(){
        //creating an ArrayList of an Arraylis named "Question" to store 100 questions of 10 topics  (Thau)
        ArrayList<ArrayList<Question>> list = new ArrayList<ArrayList<Question>>();
        Scanner fileReader;
        //using FileIO and Exception to load questionBank.txt file  (Thau)
        try{
            fileReader = new Scanner(new File("questionBank.txt"));
        } catch(IOException e){
            System.out.println("File not found.");
            return null;
        }
        String holder; 
        int index = -1;
        //loading each line of questionBank.txt file  (Thau)
        while(fileReader.hasNext()){
            holder = fileReader.nextLine();
            //slipt 100 quesions into 10 different topics using charAt() to check if the first character of the line is ":"  (Thau)
            if(holder.charAt(0)==':' || holder.charAt(1)==':'){
                index++;
                list.add(new ArrayList<Question>()); //add to the Question Arraylist  (Thau)
                fileReader.nextLine();
            } else {
                String question = holder;
                int answerIndex = Integer.parseInt(fileReader.nextLine());
                holder = fileReader.nextLine();
                ArrayList<String> answers = new ArrayList<String>();
                while(!holder.isEmpty()){
                    answers.add(holder);
                    holder = fileReader.nextLine();
                }
                list.get(index).add(new Question(question,answers,answerIndex));
            } 
        }
        fileReader.close();
        return list;
    }
}
