import javax.swing.*; v

public class Test extends JFrame implements PickRandomQuestions { //main class that runs TestFrame.java

    public static void main(String[] args){
        new Test();
    }

    public Test(){

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new TestFrame("Object Oriented Final");
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
