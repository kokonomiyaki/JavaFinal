import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.File;
import java.io.IOException;

public class LoadQuestions implements Runnable
{
    private ConcurrentLinkedQueue<ArrayList<Question>> que;
    //@Param1 The queue that you will be pulling questions from.
    public LoadQuestions(ConcurrentLinkedQueue<ArrayList<Question>> que){
        this.que = que;
    }

    public void run(){
        Scanner fileReader;
        //using FileIO and Exception to load questionBank.txt file  (Thau)
        try{
            fileReader = new Scanner(new File("questionBank.txt"));
        } catch(IOException e){
            System.out.println("File not found.");
            return;
        }
        String holder;
        ArrayList<Question> list = null;

        //loading each line of questionBank.txt file  (Thau)

        while(fileReader.hasNext()){
            holder = fileReader.nextLine();
            //slipt 100 quesions into 10 different topics using charAt() to check if the first character of the line is ":"  (Thau)
            if(holder.charAt(0)==':' || holder.charAt(1)==':'){
            	// Checks to make sure its not on the first rotation before offering the packet of questions.
                if(list!=null){
                    que.offer(list);
                }
                list = new ArrayList<Question>();//add to the Question Arraylist  (Thau)

                fileReader.nextLine();
            } else {
            	// Parsing a question.
                String question = holder;
                int answerIndex = Integer.parseInt(fileReader.nextLine());
                holder = fileReader.nextLine();
                ArrayList<String> answers = new ArrayList<String>();
                while(!holder.isEmpty()){
                    answers.add(holder);
                    holder = fileReader.nextLine();
                }
                list.add(new Question(question,answers,answerIndex));
            } 
        }
        que.offer(list);
        fileReader.close();
        return;
    }
}
