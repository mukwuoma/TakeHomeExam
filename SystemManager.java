package onlineTest;

import java.io.*;
import java.util.*;

public class SystemManager implements Manager, Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Integer, Exam> allExams = new TreeMap<>();
	private Map<String, Student> allStudents = new TreeMap<>();

	@Override
	public boolean addExam(int examId, String title) {
		Exam exam = new Exam(title);
		if (!allExams.containsValue(exam)) {
			allExams.put(examId, exam);
			return true;
		}
		return false;

	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {

		for (Integer id : allExams.keySet()) {
			if (id == examId) {
				Exam exam = allExams.get(id);
				Set<Question> questionSet = exam.getSet();
				exam.addTFQuestion(questionSet, examId, questionNumber, text, points, answer);
			}
		}
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {

		for (Integer id : allExams.keySet()) {
			if (id == examId) {
				Exam exam = allExams.get(id);
				Set<Question> questionSet = exam.getSet();
				exam.addMCQuestion(questionSet, examId, questionNumber, text, points, answer);
			}
		}
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {

		for (Integer id : allExams.keySet()) {
			if (id == examId) {
				Exam exam = allExams.get(id);
				Set<Question> questionSet = exam.getSet();
				exam.addFITBQuestion(questionSet, examId, questionNumber, text, points, answer);
			}
		}

	}

	@Override
	public String getKey(int examId) {

		Exam exam = allExams.get(examId);
		if (exam != null) {
			return exam.getQuestionsInSet();
		}
		return "Exam not found";

	}

	@Override
	public boolean addStudent(String name) {
		if (!allStudents.containsKey(name)) {
			allStudents.put(name, new Student(name));
			return true;
		}
		return false;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {

		Student student = allStudents.get(studentName);
		Exam exam = allExams.get(examId);
		Question question = exam.getQuestion(questionNumber);
		student.answerTFQuestion(student, examId, question, answer);

	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		Student student = allStudents.get(studentName);
		Exam exam = allExams.get(examId);
		Question question = exam.getQuestion(questionNumber);
		student.answerMCQuestion(student, examId, question, answer);

	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		Student student = allStudents.get(studentName);
		Exam exam = allExams.get(examId);
		Question question = exam.getQuestion(questionNumber);
		student.answerFITBQuestion(student, examId, question, answer);

	}

	@Override
	public double getExamScore(String studentName, int examId) {
		double examScore = 0;
		for (Student student : allStudents.values()) {
			if (student.getName().equals(studentName)) {
				examScore = student.getExamScore(examId);
			}
		}
		return examScore;

	}

	@Override
	public String getGradingReport(String studentName, int examId) {

		String result = "";
		for (Student student : allStudents.values()) {
			if (student.getName().equals(studentName)) {
				for (Integer id : allExams.keySet()) {
					if (id == examId) {
						Exam exam = allExams.get(examId);
						for (Question question : exam.getSet()) {
							result += "Question #" + question.getQuestionNumber() + " "
									+ student.getPointsRecieved(question, examId) + " points out of "
									+ question.getPoints() + "\n";

						}
						result += "Final Score: " + student.getExamScore(id) + " out of " + exam.getExamTotal();
					}
				}

			}
		}

		return result;

	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {

		for (Student student : allStudents.values()) {

			for (int i = 0; i < cutoffs.length; i++) {
				if ((getCourseNumericGrade(student.getName())) >= cutoffs[i]) {
					student.setGrade(letterGrades[i]);
					break;
				}
			}

		}
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double score = 0.0, finalGrade = 0.0;

		Student student = allStudents.get(studentName);
		for (Integer examId : allExams.keySet()) {
			Exam exam = allExams.get(examId);
			score = (student.getExamScore(examId) / exam.getExamTotal());
			finalGrade += score;
		}

		return (finalGrade / allExams.size()) * 100;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		Student student = allStudents.get(studentName);

		return student.getGrade();
	}

	@Override
	public String getCourseGrades() {
		String result = "";
		for (Student student : allStudents.values()) {
			result += student.getName() + " " + getCourseNumericGrade(student.getName()) + " "
					+ getCourseLetterGrade(student.getName()) + "\n";
		}
		return result;
	}

	@Override
	public double getMaxScore(int examId) {

		ArrayList<Double> score = new ArrayList<>();
		for (Student student : allStudents.values()) {
			score.add(student.getExamScore(examId));
		}

		return Collections.max(score);
	}

	@Override
	public double getMinScore(int examId) {
		ArrayList<Double> score = new ArrayList<>();
		for (Student student : allStudents.values()) {
			score.add(student.getExamScore(examId));
		}

		return Collections.min(score);

	}

	@Override
	public double getAverageScore(int examId) {
		double score = 0;
		for (Student student : allStudents.values()) {
			score += student.getExamScore(examId);
		}

		return score / allStudents.values().size();

	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		File file = new File(fileName);
		try {

			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Manager restoreManager(String fileName) {

		try {
			File file = new File(fileName);
			if (file.exists()) {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
				Manager manager = (SystemManager) input.readObject();
				input.close();
				return manager;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SystemManager();
	}

}