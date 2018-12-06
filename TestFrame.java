import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class TestFrame extends JFrame implements PickRandomQuestions, ActionListener {
	JPanel ansPanel = new JPanel();
	JLabel questionLabel = new JLabel("Click Start to begin test.", SwingConstants.CENTER);
	JButton startButton = new JButton("Start");
	JButton submit = new JButton("Submit");
	JButton retry = new JButton("Retry");
	JButton quit = new JButton("Quit");
	JTextField nameInput = new JTextField(30);
	ChartPanel graph;
	Container cp = getContentPane();
	String[] catagories = { "Arrays", "Inheritance", "Interface", "Software Dev", "Regex", "File I/O", "Lambdas",
			"Generics", "Recursion", "Concurrency" };

	List<Question> questions = new ArrayList<>();
	boolean[] answersCorrect = new boolean[30];
	int count = 0;

	List<String> radioStringList = new ArrayList<>();
	List<JRadioButton> radioButtonList = new ArrayList<>();

	public TestFrame(String title) {
		super(title);

		setLayout(new BorderLayout());

		ansPanel.setLayout(new BoxLayout(ansPanel, BoxLayout.PAGE_AXIS));

		cp.add(questionLabel, BorderLayout.NORTH);
		cp.add(startButton, BorderLayout.SOUTH);

		startButton.addActionListener(this);

	}

	public void setRadioButtons() {
		// Pretty self explanatory, sets up radio buttons
		ButtonGroup radioGroup = new ButtonGroup();

		radioStringList.clear();
		radioButtonList.clear();
		ansPanel.removeAll();

		System.out.println(questions.get(count).getQuestion());
		for (int i = 0; i < questions.get(count).getAnswers().size(); i++) {
			radioStringList.add("" + questions.get(count).getAnswers().get(i));
			JRadioButton answerButton = new JRadioButton(radioStringList.get(i));
			radioButtonList.add(answerButton);
			radioGroup.add(radioButtonList.get(i));
			ansPanel.add(radioButtonList.get(i));
		}

		count++;
	}

	public void setRetry() {
		cp.removeAll();
		JPanel retryPanel = new JPanel();
		retryPanel.add(retry);
		retryPanel.add(quit);
		cp.add(retryPanel, BorderLayout.SOUTH);
		quit.addActionListener(this);
		retry.addActionListener(this);
		retryPanel.setVisible(true);
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Put your name here:"));
		namePanel.add(nameInput);
		cp.add(namePanel, BorderLayout.CENTER);
		this.setSize(601, 400);
		this.setSize(600, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == startButton) {
			startRandom();
			Question[] questions1 = pickRandom();
			questionLabel.setText("<html>" + questions1[count].getQuestion() + "</html>");

			while (questions1 != null) {
				for (Question q : questions1) {
					questions.add(q);
				}
				questions1 = pickRandom();
			}
			setRadioButtons();

			cp.remove(startButton);
			cp.add(submit, BorderLayout.SOUTH);
			cp.add(ansPanel, BorderLayout.CENTER);
			submit.addActionListener(this);

		}

		if (source == submit) {
			for (int i = 0; i < radioButtonList.size(); i++) {
				if (radioButtonList.get(i).isSelected()) {
					answersCorrect[count - 1] = questions.get(count - 1).isCorrect(i);
					System.out.println((questions.get(count - 1).isCorrect(i) ? "Correct" : "Incorrect"));
					System.out.println(questions.get(count - 1).correctAnswer());
				}

			}
			if (count == 30) {
				setRetry();
				int[] correct = new int[10];
				for (int i = 0; i < answersCorrect.length; i++) {
					if (answersCorrect[i]) {
						correct[(i) / 3]++;
						System.out.print(i + " ");
					}
				}
				graph = new ChartPanel(correct);
			} else {

				questionLabel.setText("<html>" + questions.get(count).getQuestion() + "</html>");

				ansPanel.repaint();

				setRadioButtons();
			}
			cp.repaint();
		}
		if (source == retry) {
			saveTest();

			this.setVisible(false);
			graph.setVisible(false);

			new Test();

		}
		if (source == quit) {
			saveTest();
			System.exit(0);
		}
	}

	public void saveTest() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String fileName = nameInput.getText() + "_" + formatter.format(date) + ".txt";
		fileName = fileName.replaceAll(" ", "");
		try {
			PrintWriter writer = new PrintWriter(new File(fileName));
			for (int i = 0; i < 10; i++) {
				writer.println(catagories[i]);
				for (int j = 0; j < 3; j++) {
					writer.println((i * 3) + j + 1 + ") " + ((answersCorrect[(i * 3) + j]) ? "Correct" : "Incorrect"));
				}
			}
			int totalCorrect = 0;
			for (boolean i : answersCorrect) {
				if (i) {
					totalCorrect++;
				}
			}

			double correctPercentage = ((double) totalCorrect / 30) * 100;
			writer.printf("Percent of correct answers: %.2f", correctPercentage);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
