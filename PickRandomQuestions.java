import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

public interface PickRandomQuestions {
	ConcurrentLinkedQueue<ArrayList<Question>> que = new ConcurrentLinkedQueue<ArrayList<Question>>();

	// Starts loading the questions.
	default void startRandom() {
		LoadQuestions ques = new LoadQuestions(que);
		Thread t = new Thread(ques);
		t.start();
	}

	// Returns an array of 3 random questions from a section.
	default Question[] pickRandom() {
		int count = 0;
		ArrayList<Question> alist = null;
		Question[] questions = null;
		while (count < 5) {
			alist = que.poll();
			if (alist == null) {
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				count = 0;
				questions = new Question[3];
				for (int i = 0; i < 3; i++) {
					int ran = (int) (Math.random() * alist.size());
					questions[i] = alist.remove(ran);
				}
				return questions;
			}

		}
		return questions;
	}
}
