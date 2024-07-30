import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication1 {
    private static final int QUESTION_TIME_LIMIT = 10; // seconds
    private static String[] questions = {
        "What is the capital of France?",
        "What is 2 + 2?",
        "What is the square root of 16?"
    };
    private static String[][] options = {
        {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
        {"1. 3", "2. 4", "3. 5", "4. 6"},
        {"1. 2", "2. 3", "3. 4", "4. 5"}
    };
    private static int[] correctAnswers = {3, 2, 3};
    private static int score = 0;
    private static int questionIndex = 0;
    private static boolean timeUp = false;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (questionIndex = 0; questionIndex < questions.length; questionIndex++) {
            timeUp = false;
            System.out.println(questions[questionIndex]);
            for (String option : options[questionIndex]) {
                System.out.println(option);
            }

            TimerTask task = new TimerTask() {
                int countdown = QUESTION_TIME_LIMIT;

                public void run() {
                    if (countdown > 0) {
                        System.out.print("Time remaining: " + countdown + " seconds\r");
                        countdown--;
                    } else {
                        timeUp = true;
                        System.out.println("\nTime's up!");
                        this.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);

            System.out.print("Enter your answer (1-4): ");
            while (!timeUp && !scanner.hasNextInt()) {
                // Wait for valid input or time up
            }

            if (timeUp) {
                continue;
            }

            int answer = scanner.nextInt();
            task.cancel();

            if (answer == correctAnswers[questionIndex]) {
                score++;
            }
        }

        System.out.println("Quiz Over! Your score: " + score + "/" + questions.length);

        scanner.close();
    }
}
