package onlineTest;

import java.util.Arrays;

public class MCQuestion extends Question {

	private static final long serialVersionUID = 1L;
	private String[] answer;

	public MCQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		super(examId, questionNumber, text, points);

		this.answer = answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	@Override
	public String[] getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Question Text: " + getText() + "\nPoints: " + getPoints() + "\nCorrect Answer: "
				+ Arrays.toString(getAnswer()) + "\n";
	}

}
