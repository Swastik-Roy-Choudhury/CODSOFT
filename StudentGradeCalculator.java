import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        // Array to store marks for each subject
        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        // Input: Take marks obtained in each subject
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        // Calculate Total Marks
        System.out.println("Total Marks Obtained: " + totalMarks);

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;
        System.out.println("Average Percentage: " + averagePercentage + "%");

        // Grade Calculation based on the average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display Results: Total Marks, Average Percentage, and Grade
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}