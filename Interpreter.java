package cmdLineInterpreter;

import java.util.*;

import onlineTest.Manager;
import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to execute commands
 * associated with the SystemManager.
 *
 */
public class Interpreter {

	public static void main(String[] args) {
		Manager manager = new SystemManager();
		Scanner input = new Scanner(System.in);
		String name, title, text;
		int choice, examId, questionNumber;
		double points;
		boolean studentAns, answer;
		System.out.println("Welcome to the Exam Database");
		do {
			String options = "Enter 1 to Add a New Student\n";
			options += "Enter 2 to Add an Exam\n";
			options += "Enter 3 to Add a True or False Question\n";
			options += "Enter 4 to Answer a True or False Question\n";
			options += "Enter 5 to Get an Exam Score\n";
			options += "Enter 6 to Quit";
			System.out.println(options);
			choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter Student Name: ");
				name = input.next();
				manager.addStudent(name);
				System.out.println(name + " has been added to the database.");
				break;
			case 2:
				System.out.print("Enter Exam Id: ");
				examId = input.nextInt();
				System.out.print("Enter Exam Title: ");
				title = input.next();
				manager.addExam(examId, title);
				System.out.println(title + " has been added has been added to the database");
				break;
			case 3:
				System.out.print("Enter Exam Id: ");
				examId = input.nextInt();
				System.out.print("Enter Question Number: ");
				questionNumber = input.nextInt();
				System.out.print("Enter Question Text: ");
				text = input.next();
				System.out.print("Enter Points Possible: ");
				points = input.nextDouble();
				System.out.print("Enter Expected Answer: ");
				answer = input.nextBoolean();
				manager.addTrueFalseQuestion(examId, questionNumber, text, points, answer);
				System.out.println("Your Question Has Been Added");
				break;
			case 4:
				System.out.print("Enter Student Name: ");
				name = input.next();
				System.out.print("Enter Exam Id: ");
				examId = input.nextInt();
				System.out.print("Enter Question Number: ");
				questionNumber = input.nextInt();
				System.out.print("Enter your answer: ");
				studentAns = input.nextBoolean();
				manager.answerTrueFalseQuestion(name, examId, questionNumber, studentAns);
				break;
			case 5:
				System.out.print("Enter Student Name: ");
				name = input.next();
				System.out.print("Enter Exam Id: ");
				examId = input.nextInt();
				System.out.println("Exam Score: " + manager.getExamScore(name, examId) );
				break;
			case 6:
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
		} while (choice != 6);
		input.close();
	}

}
