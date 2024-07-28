import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            Random random = new Random();
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrectly = false;

            System.out.println("I have generated a random number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    guessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    int attemptsleft=10-attempts;
                    System.out.println("Too low! Try again.you still have "+attemptsleft+" attempts");
                } else {
                    int attemptsleft=10-attempts;
                    System.out.println("Too high! Try again.you still have "+attemptsleft+" attempts");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was " + numberToGuess);
            }
            int score=100-attempts*10;
            System.out.print("You scored : "+score+"%.");
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
