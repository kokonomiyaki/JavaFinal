import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class LoadQuestions
{
    public static ArrayList<ArrayList<Question>> loadQuestion(){
        ArrayList<ArrayList<Question>> list = new ArrayList<ArrayList<Question>>();
        Scanner fileReader;
        try{
            fileReader = new Scanner(new File("questionBank.txt"));
        } catch(IOException e){
            System.out.println("File not found.");
            return null;
        }
        String holder;
        int index = -1;
        while(fileReader.hasNext()){
            holder = fileReader.nextLine();
            if(holder.charAt(0)==':' || holder.charAt(1)==':'){
                index++;
                list.add(new ArrayList<Question>());
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
