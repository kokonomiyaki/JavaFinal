import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Test extends JFrame implements PickRandomQuestions, ActionListener, ItemListener {

    public static void main(String[] args){
        new Test();
    }

    public Test(){

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new TestFrame("Object Oriented Final");
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        /**startRandom();
        Question[] questions = pickRandom();
        int count = 0;
        while(questions!=null){
            for(Question q : questions){
                System.out.println(q.getQuestion());
            }
            System.out.println(++count);
            questions = pickRandom();
        }*/

    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    @Override
    public void itemStateChanged(ItemEvent e){

    }
}