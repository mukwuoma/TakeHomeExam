package onlineTest;

import java.util.*;

public class FITBQuestion extends Question {
	private static final long serialVersionUID = 1L;

	private String[] answer;

	public FITBQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		super(examId, questionNumber, text, points);
		this.answer = answer;
	}

	@Override
	public String[] getAnswer() {
		Arrays.sort(answer);
		return answer;
	}

	@Override
	public String toString() {

		return "Question Text: " + getText() + "\nPoints: " + getPoints() + "\nCorrect Answer: "
				+ Arrays.toString(getAnswer()) + "\n";

	}

}
