import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Game configuration
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int rounds = 1;
        boolean playAgain = true;
        int score = 0;

        while (playAgain) {
            System.out.println("Round " + rounds + ": Guess the number between " + minRange + " and " + maxRange);
            
            // Generate a random number within the specified range
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;

            boolean hasGuessedCorrectly = false;
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.print("Attempt " + attempt + " - Enter your guess: ");
                int userGuess = scanner.nextInt();

                // Compare the user's guess with the generated number
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    hasGuessedCorrectly = true;
                    score += (maxAttempts - attempt + 1); // Higher score for fewer attempts
                    break;
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + targetNumber);
            }

            // Ask the user if they want to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String userResponse = scanner.next();

            if (userResponse.equalsIgnoreCase("no")) {
                playAgain = false;
            } else {
                rounds++;
            }
        }

        // Display the final score
        System.out.println("Game Over! Your final score is: " + score);
        scanner.close();
    }
}