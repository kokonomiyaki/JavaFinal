import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends JFrame implements PickRandomQuestions, ActionListener{
    JPanel ansPanel = new JPanel(new FlowLayout());
    JLabel questionLabel = new JLabel("Click Start to begin test.", SwingConstants.CENTER);
    JButton startButton = new JButton("Start");
    JButton submit = new JButton("Submit");
    Container cp = getContentPane();
    Question[] questions;

    List<String> radioList = new ArrayList<>();

    public TestFrame(String title){
        super(title);

        setLayout(new BorderLayout());

        cp.add(questionLabel, BorderLayout.NORTH);
        cp.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(this);

    }



    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        if(source == startButton) {
             startRandom();
             questions = pickRandom();
             int count = 0;
             questionLabel.setText(questions[count].getQuestion());
             ButtonGroup radioGroup = new ButtonGroup();

             for(int i = 0; i < questions[count].getAnswers().size(); i++){
                 radioList.add("" + questions[count].getAnswers().get(i));
                 JRadioButton answerButton = new JRadioButton(radioList.get(i));
                 radioGroup.add(answerButton);
                 ansPanel.add(answerButton);
             }

             while(questions!=null){
             for(Question q : questions){

             System.out.println(q.getQuestion());
             System.out.println(q.getAnswers());
             }
             System.out.println(++count);
             questions = pickRandom();
             }

             cp.remove(startButton);
             cp.add(submit, BorderLayout.SOUTH);
             cp.add(ansPanel, BorderLayout.CENTER);
             submit.addActionListener(this);

        }

        if(source == submit) {

        }
    }
}
