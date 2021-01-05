package onlineTest;

import java.io.*;
import java.util.*;

public abstract class Question implements Comparable<Question>, Serializable {

	private static final long serialVersionUID = 1L;
	private int examId, questionNumber;
	private String text;
	private double points;

	public Question(int examId, int questionNumber, String text, double points) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
	}

	public int getQuestionNumber() {

		return questionNumber;
	}

	public double getPoints() {
		return points;
	}

	public int getExamId() {
		return examId;

	}

	public String getText() {
		return text;
	}

	public int compareTo(Question ques) {

		return this.questionNumber - ques.questionNumber;

	}

	public String[] getAnswer() {
		return new String[] {};
	}

	@Override
	public String toString() {
		return "Question Text: " + getText() + "\nPoints: " + getPoints() + "\nCorrect Answer: "
				+ Arrays.toString(getAnswer()) + "\n";
	}

}
