import java.util.*;

public class Task4 {
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        loadQuestions();
        startQuiz();
        showResults();
    }

    private static void loadQuestions() {
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"1) Paris", "2) London", "3) Rome", "4) Berlin"},
                0
        ));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"1) Earth", "2) Mars", "3) Jupiter", "4) Saturn"},
                1
        ));
        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                new String[]{"1) Charles Dickens", "2) Leo Tolstoy", "3) William Shakespeare", "4) Mark Twain"},
                2
        ));
        // Add more questions as needed
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                
                public void run() {
                    System.out.println("\nTime's up!");
                }
            };
            timer.schedule(task, 20000); // 10-second timer

            int userAnswer = getUserAnswer(scanner);

            if (userAnswer == question.correctAnswerIndex + 1) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.options[question.correctAnswerIndex] + "\n");
            }

            timer.cancel();
        }
    }

    private static int getUserAnswer(Scanner scanner) {
        int userAnswer = -1;
        try {
            System.out.print("Your answer (1-4): ");
            userAnswer = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // Clear invalid input
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
        }
        return userAnswer;
    }

    private static void showResults() {
        System.out.println("\nQuiz Complete!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}
