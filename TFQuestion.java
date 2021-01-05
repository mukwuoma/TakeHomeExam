package onlineTest;

public class TFQuestion extends Question {
	private static final long serialVersionUID = 1L;

	private boolean answer;

	public TFQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		super(examId, questionNumber, text, points);
		this.answer = answer;
	}

	@Override
	public String[] getAnswer() {
		String[] ans = { String.valueOf(answer) };

		return ans;
	}

	@Override
	public String toString() {
		return "Question Text: " + getText() + "\nPoints: " + getPoints() + "\nCorrect Answer: "
				+ getAnswer()[0].substring(0, 1).toUpperCase() + getAnswer()[0].substring(1) + "\n";

	}

}
