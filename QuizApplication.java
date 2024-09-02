import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Class to represent a quiz question
class Question {
    String questionText;
    String[] options;
    int correctOption;

    // Constructor to initialize the question, options, and correct answer
    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

// Class to manage the quiz application
class Quiz {
    private Question[] questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;
    private boolean answered;
    private Timer timer;

    // Constructor to initialize the quiz with an array of questions
    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the quiz
    public void start() {
        while (currentQuestionIndex < questions.length) {
            displayQuestion(currentQuestionIndex);
            startTimer(15); // 15 seconds timer for each question
            int userAnswer = getUserAnswer();
            checkAnswer(userAnswer);
            currentQuestionIndex++;
        }
        displayResult();
    }

    // Method to display the current question and its options
    private void displayQuestion(int index) {
        Question question = questions[index];
        System.out.println("Question " + (index + 1) + ": " + question.questionText);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }
    }

    // Method to start the timer for each question
    private void startTimer(int seconds) {
        answered = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    checkAnswer(-1); // -1 indicates no answer was given in time
                }
            }
        }, seconds * 1000);
    }

    // Method to get the user's answer
    private int getUserAnswer() {
        int userAnswer = -1;
        while (true) {
            System.out.print("Enter your answer (1-4): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= 4) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }
        answered = true;
        timer.cancel();
        return userAnswer;
    }

    // Method to check if the user's answer is correct and update the score
    private void checkAnswer(int userAnswer) {
        Question question = questions[currentQuestionIndex];
        if (userAnswer == question.correctOption) {
            System.out.println("Correct!");
            score++;
        } else if (userAnswer == -1) {
            System.out.println("You didn't answer the question.");
        } else {
            System.out.println("Incorrect. The correct answer was: " + question.correctOption);
        }
    }

    // Method to display the final result after the quiz ends
    private void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);
    }
}

// Main class to run the quiz application
public class QuizApplication {

    public static void main(String[] args) {
        Question[] questions = new Question[]{
            new Question("Who is the current CEO of Tesla?", new String[]{"Jeff Bezos", "Tim Cook", "Elon Musk", "Sundar Pichai"}, 3),
            new Question("What is the square root of 64?", new String[]{"6", "8", "10", "12"}, 2),
            new Question("In which year did the Titanic sink?", new String[]{"1912", "1905", "1920", "1918"}, 1),
            new Question("Which language is the most spoken worldwide?", new String[]{"Spanish", "English", "Mandarin", "Hindi"}, 3),
            new Question("Which country hosted the 2020 Summer Olympics?", new String[]{"Brazil", "Japan", "China", "Germany"}, 2)
        };
        

        // Create the quiz instance and start the quiz
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}