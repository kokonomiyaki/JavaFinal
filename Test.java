public class Test implements PickRandomQuestions
{
    public static void main(String[] args){
        new Test();
    }
    public Test(){
        startRandom();
        Question[] questions = pickRandom();
        int count = 0;
        while(questions!=null){
            for(Question q : questions){
                System.out.println(q.getQuestion());
            }
            System.out.println(++count);
            questions = pickRandom();
        }
        
    }
}
