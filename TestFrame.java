import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends JFrame implements PickRandomQuestions, ActionListener{
    JPanel ansPanel = new JPanel();
    JLabel questionLabel = new JLabel("Click Start to begin test.", SwingConstants.CENTER);
    JButton startButton = new JButton("Start");
    JButton submit = new JButton("Submit");
    Container cp = getContentPane();

    List<Question> questions = new ArrayList<>();

    int count = 0;

    List<String> radioStringList = new ArrayList<>();
    List<JRadioButton> radioButtonList = new ArrayList<>();

    public TestFrame(String title){
        super(title);

        setLayout(new BorderLayout());

        ansPanel.setLayout(new BoxLayout(ansPanel, BoxLayout.PAGE_AXIS));

        cp.add(questionLabel, BorderLayout.NORTH);
        cp.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(this);

    }

    public void setRadioButtons(){
        //Pretty self explanatory, sets up radio buttons
        ButtonGroup radioGroup = new ButtonGroup();

        radioStringList.clear();
        radioButtonList.clear();
        ansPanel.removeAll();

        for(int i = 0; i < questions.get(count).getAnswers().size(); i++) {
            radioStringList.add("" + questions.get(count).getAnswers().get(i));
            JRadioButton answerButton = new JRadioButton(radioStringList.get(i));
            radioButtonList.add(answerButton);
            radioGroup.add(radioButtonList.get(i));
            ansPanel.add(radioButtonList.get(i));
        }

        count++;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        if(source == startButton) {
             startRandom();
             Question[] questions1 = pickRandom();
             questionLabel.setText("<html>" + questions1[count].getQuestion() + "</html>");


             while(questions1!=null){
             for(Question q : questions1){
                 questions.add(q);
             System.out.println(q.getQuestion());
             System.out.println(q.getAnswers());
             }
             //System.out.println(++count);
             questions1 = pickRandom();
             }
             setRadioButtons();

             cp.remove(startButton);
             cp.add(submit, BorderLayout.SOUTH);
             cp.add(ansPanel, BorderLayout.CENTER);
             submit.addActionListener(this);

        }

        if(source == submit){



            for(int i = 0; i < radioButtonList.size(); i++){
            if (radioButtonList.get(i).isSelected()){
                questions.get(i).isCorrect(radioButtonList.indexOf(i));
                System.out.println(questions.get(count).isCorrect(i));
            }
            }


            questionLabel.setText("<html>" + questions.get(count).getQuestion() + "</html>");

            ansPanel.repaint();

            setRadioButtons();
        }
    }
}