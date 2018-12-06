
/*
 * Programming assignment Final
 * Program or Class Name: ChartPanel
 * 
 * Description of program or class:
 * This class is used for its constructor which will take in answers correct int array.
 * creates a window with the title at the top of the window with the answers correct out of total questions, create the bars for each name/category, sizes the bars based on the answers correct.
 * 
 * 
 * Author: Patrick DeGuzman
 * Date: 12/5
 */



import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.text.NumberFormat;

public class ChartPanel extends JPanel {
    private int[] answersCorrect;

    private String[] names;

    private String title;
    
    private int totalQuestionsAsked;


    public ChartPanel(int[] c) {
        
        // constructor method
        // this takes in int array, string array, int, and string.
        // int array c takes the answersCorrect from each category to be used for the size of the bar as well as summed up to give totalanswersCorrect for the title.
        // creates String array with values of the categories (Arrays, inheritance, interface, etc) which will be used to draw under the bars
        // int totalQuestionsAsked is set to 30 questions
        // int totalAnswersCorrect which will be summed from for each loop using the int answersCorrect
        //title will display the answers correct, total questions, percentage from answers correct over total questions
        names = new String[10];
        names[0] = "Arrays";
        names[1] = "Inheritance";
        names[2] = "Interface";
        names[3] = "Software Dev";
        names[4] = "Regex";
        names[5] = "File I/O";
        names[6] = "Lambdas";
        names[7] = "Generics";
        names[8] = "Recursion";
        names[9] = "Concurrency";
        
        answersCorrect = c;
        
        totalQuestionsAsked = 30;
        
        
        
        // for each loop to add all of the answers correct together which will be used in the title
        int totalAnswersCorrect = 0;
        
        for (int i : answersCorrect) {
            totalAnswersCorrect += answersCorrect[i];
        }
        
        double correctPercentage =  ((double)totalAnswersCorrect / totalQuestionsAsked) * 100;
        
        title = String.format("Answers Correct: %d / %d  |  %.2f%% Correct" ,totalAnswersCorrect , totalQuestionsAsked , correctPercentage);
 
        // setting the jframes size and contents
        
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.getContentPane().add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (answersCorrect == null || answersCorrect.length == 0)
            return;
            
        
        // setting the max value to 3 and minimum value to 0
        double minValue = 0;
        double maxValue = 3;
        for (int i = 0; i < answersCorrect.length; i++) {
            if (minValue > answersCorrect[i])
                minValue = answersCorrect[i];
            if (maxValue < answersCorrect[i])
                maxValue = answersCorrect[i];
        }

        //settings to follow the exact settings as jframe's size
        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / answersCorrect.length;

        
        //setting fonts for title and bar labels
        Font titleFont = new Font("SansSerif", Font.BOLD, 30);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 15);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        //setting the title at the top of the window
        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y); //title at the top and centered

        // setting to put the bars to be between the title and bar labels.
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        Color myC;
        String stringValues[] = new String[10];
        
        // for loop to draw a bar with size based on answersCorrect[i] and fill the bars with random colors
        // also draw names underneath bars with the categories
        // label each bar with the number from answersCorrect[i] (answers correct in that category)
        for (int i = 0; i < answersCorrect.length; i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (answersCorrect[i] * scale);
            if (answersCorrect[i] >= 0)
                valueY += (int) ((maxValue - answersCorrect[i]) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            myC = new Color((int)(Math.random() * 0x1000000));

            stringValues[i] = Integer.toString(answersCorrect[i]);

            g.setColor(myC);
            //g.setColor(Color.red);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(names[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(names[i], x, y);
            g.drawString(stringValues[i], (x + (labelWidth / 2)), (y - 30));
            
            
        }
    }


    public static void main(String[] argv) {
        
        
        //test method as the Final project will just use the constructor to create the graph.
        int[] answersCorrect = new int[10];
        answersCorrect[0] = 0;
        answersCorrect[1] = 1;
        answersCorrect[2] = 2;
        answersCorrect[3] = 1;
        answersCorrect[4] = 1;
        answersCorrect[5] = 3;
        answersCorrect[6] = 1;
        answersCorrect[7] = 2;
        answersCorrect[8] = 3;
        answersCorrect[9] = 3;
        
        int totalQuestionsAsked = 30;

        new ChartPanel(answersCorrect);

       
    }
}
