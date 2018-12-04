
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ChartPanel extends JPanel {
    private int[] values;

    private String[] names;

    private String title;
   
    

    public ChartPanel(int[] v, String[] n, String t) {
        names = n;
        values = v;
        title = t;

        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.getContentPane().add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i])
                minValue = values[i];
            if (maxValue < values[i])
                maxValue = values[i];
        }

        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length;

        Font titleFont = new Font("SansSerif", Font.BOLD, 30);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 15);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        
        Color myC;
        
        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0)
                valueY += (int) ((maxValue - values[i]) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }
            
            myC = new Color((int)(Math.random() * 0x1000000));
            
            g.setColor(myC);
            //g.setColor(Color.red);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(names[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(names[i], x, y);
        }
    }

    

    public static void main(String[] argv) {
        
        

        
        int[] values = new int[10];
        String[] names = new String[10];
        values[0] = 1;
        names[0] = "Arrays";

        values[1] = 2;
        names[1] = "Inheritance";

        values[2] = 3;
        names[2] = "Interface";

        values[3] = 1;
        names[3] = "Software Dev";
        
        values[4] = 2;
        names[4] = "Regex";
        
        values[5] = 3;
        names[5] = "File I/O";
        
        values[6] = 1;
        names[6] = "Lambdas";
        
        values[7] = 1;
        names[7] = "Generics";
        
        values[8] = 1;
        names[8] = "Recursion";
        
        values[9] = 3;
        names[9] = "Concurrency";

        new ChartPanel(values, names, "Answers Correct");
        

       
        
    }
}
