import java.util.*;
import java.io.*;
public class Test
{
    public static void main(String[] args){
        //printFile();
        new Test();
    }
    private Test(){
        ArrayList<ArrayList<Question>> list = LoadQuestions.loadQuestion();
        int index = 0;
        for(ArrayList<Question> alist : list){
            System.out.println(index++);
            for(Question ques : alist){
                System.out.println(ques.getQuestion());
            }
        }
    }
    public static void printFile(){
        Scanner scan;
        try{
            scan = new Scanner(new File("questionBank.txt"));
            while(scan.hasNext()){
                System.out.println(scan.nextLine());
            }
            scan.close();
        } catch (IOException e){
            System.out.println("File not found");
        }
    }
}
