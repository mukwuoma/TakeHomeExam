package onlineTest;

import java.io.*;
import java.util.*;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	class Answer implements Serializable {

		private static final long serialVersionUID = 1L;
		private int questionNumber;
		private String[] answer;

		public Answer(int questionNumber, String[] answer) {
			this.questionNumber = questionNumber;
			this.answer = answer;
		}

		public int getQuestionNumber() {
			return questionNumber;
		}

		public void setQuestionNumber(int questionNumber) {
			this.questionNumber = questionNumber;
		}

		public String[] getAnswer() {
			return answer;
		}

		public void setAnswer(String[] answer) {
			this.answer = answer;
		}

		@Override
		public String toString() {
			return "Question #" + questionNumber + "\nAnswer: " + Arrays.toString(answer) + "";
		}

	}

	private String name, grade;
	private Map<Integer, List<Double>> studentPoints = new TreeMap<>();
	private Map<Integer, Double> scores = new TreeMap<>();
	private Map<Integer, List<Answer>> allStudentAnswers = new HashMap<>();
	private double examScore = 0;

	public Student(String name) {
		this.name = name;

	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}

	public void answerTFQuestion(Student student, int examId, Question question, boolean answer) {
		String ans[] = new String[] { String.valueOf(answer) };

		List<Answer> answers = allStudentAnswers.get(examId);
		if (answers == null) {
			answers = new ArrayList<>();
			allStudentAnswers.put(examId, answers);
			examScore = 0;
		}
		List<Double> pointsRecieved = studentPoints.get(examId);
		if (pointsRecieved == null) {
			pointsRecieved = new ArrayList<>();
			studentPoints.put(examId, pointsRecieved);
		}
		answers.add(question.getQuestionNumber() - 1, new Answer(question.getQuestionNumber(), ans));
		if (question.getAnswer()[0].equals(ans[0])) {
			pointsRecieved.add(question.getQuestionNumber() - 1, question.getPoints());
			examScore += question.getPoints();
		} else {
			pointsRecieved.add(question.getQuestionNumber() - 1, 0.0);
		}
		scores.put(examId, examScore);
	}

	public void answerMCQuestion(Student student, int examId, Question question, String[] answer) {

		List<Answer> answers = allStudentAnswers.get(examId);
		if (answers == null) {
			answers = new ArrayList<>();
			allStudentAnswers.put(examId, answers);
			examScore = 0;
		}
		List<Double> pointsRecieved = studentPoints.get(examId);
		if (pointsRecieved == null) {
			pointsRecieved = new ArrayList<>();
			studentPoints.put(examId, pointsRecieved);
		}
		boolean correct = true;
		answers.add(question.getQuestionNumber() - 1, new Answer(question.getQuestionNumber(), answer));
		List<String> correctAns = new ArrayList<>();
		List<String> studentAns = new ArrayList<>();
		correctAns = Arrays.asList(question.getAnswer());
		studentAns = Arrays.asList(answer);
		if (correctAns.size() == studentAns.size()) {
			for (int i = 0; i < correctAns.size(); i++) {
				if (!correctAns.get(i).equals(studentAns.get(i))) {
					correct = false;
				}
			}
		} else {
			correct = false;
		}
		if (correct) {
			pointsRecieved.add(question.getQuestionNumber() - 1, question.getPoints());
			examScore += question.getPoints();
		} else {
			pointsRecieved.add(question.getQuestionNumber() - 1, 0.0);
		}
		scores.put(examId, examScore);

	}

	public void answerFITBQuestion(Student student, int examId, Question question, String[] answer) {
		List<Answer> answers = allStudentAnswers.get(examId);
		if (answers == null) {
			answers = new ArrayList<>();
			allStudentAnswers.put(examId, answers);
			examScore = 0;
		}
		List<Double> pointsRecieved = studentPoints.get(examId);
		if (pointsRecieved == null) {
			pointsRecieved = new ArrayList<>();
			studentPoints.put(examId, pointsRecieved);
		}
		answers.add(question.getQuestionNumber() - 1, new Answer(question.getQuestionNumber(), answer));
		List<String> correctAns = new ArrayList<>();
		List<String> studentAns = new ArrayList<>();
		int count = 0;
		studentAns = Arrays.asList(answer);
		correctAns = Arrays.asList(question.getAnswer());
		for (int i = 0; i < correctAns.size(); i++) {
			if (studentAns.contains(correctAns.get(i))) {
				count++;
			}
		}
		pointsRecieved.add(question.getQuestionNumber() - 1,
				(question.getPoints() / question.getAnswer().length) * count);
		examScore += (question.getPoints() / question.getAnswer().length) * count;
		double score = examScore;
		scores.put(examId, score);
	}

	public double getPointsRecieved(Question question, int examId) {
		List<Double> points = studentPoints.get(examId);
		return points.get(question.getQuestionNumber() - 1);
	}

	public Double getScores() {
		double allScores = 0;
		for (Double score : scores.values()) {
			allScores += score;
		}
		return allScores;

	}

	public double getExamScore(int examId) {
		double score = 0.0;
		for (Integer id : scores.keySet())
			if (id == examId) {
				score = scores.get(id);
			}
		return score;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nGrade: " + grade + "\nStudent Points: " + studentPoints + "\nAll Exam Scores: "
				+ scores + "\nAll Student Answers: " + allStudentAnswers + "\nExam Score: " + examScore + "\n";
	}

}
