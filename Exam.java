package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private double examTotal = 0;
	private Set<Question> set;

	public Exam(String title) {
		this.title = title;
		set = new TreeSet<>();
	}

	public double getExamTotal() {
		return examTotal;
	}

	public String getTitle() {
		return title;
	}

	public void addMCQuestion(Set<Question> questionSet, int examId, int questionNumber, String text, double points,
			String[] answer) {

		Question question = new MCQuestion(examId, questionNumber, text, points, answer);
		contains(question);

	}

	public void addFITBQuestion(Set<Question> questionSet, int examId, int questionNumber, String text, double points,
			String[] answer) {

		Question question = new FITBQuestion(examId, questionNumber, text, points, answer);
		contains(question);

	}

	public void addTFQuestion(Set<Question> questionSet, int examId, int questionNumber, String text, double points,
			boolean answer) {
		Question question = new TFQuestion(examId, questionNumber, text, points, answer);
		contains(question);

	}

	private void contains(Question question) {
		if (!set.contains(question)) {
			set.add(question);
			examTotal += question.getPoints();
		}
	}

	public String getQuestionsInSet() {
		String result = "";
		for (Question question : set) {
			result += question.toString();
		}
		return result;
	}

	public Question getQuestion(int questionNumber) {
		for (Question question : set) {
			if (question.getQuestionNumber() == questionNumber) {
				return question;
			}
		}
		return null;
	}

	public Set<Question> getSet() {
		return set;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nExam Total: " + examTotal + "\n Questions: " + set + "\n";
	}

}
