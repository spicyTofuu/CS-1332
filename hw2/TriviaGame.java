import java.util.Scanner;
public class TriviaGame {
    public static void main(String[] args) {
        Scanner game = new Scanner(System.in);
        double hpoint = Double.NEGATIVE_INFINITY;
        System.out.println("Start game? [Y]es or [N]o");
        String answer = game.next();
        System.out.println();
        boolean test = true;
        if (!answer.equals("Y") || !answer.equals("N")) {
            test = false;
            if (answer.equals("Y") || answer.equals("N")) {
                test = true;
            }
        }
        while (!test) {
            System.out.println("Please input either Y or N.");
            answer = game.next();
            System.out.println();
            if (answer.equals("Y")) {
                test = true;
            } else if (answer.equals("N")) {
                test = false;
                break;
            }
        }
        while (answer.equals("Y")) {
            System.out.println("Please enter your name");
            game.nextLine();
            String name = game.nextLine();
            System.out.println("\nQuestion 1: What year was Java created?");
            double question1 = game.nextDouble();
        /*
         Setting up the points!
         */
            double point = 0;
        /*
         Question 1
         */
            if (question1 == 1996) {
                System.out.printf("Congratulations %s! You gained 100 points!%n", name);
                point += 100;
            } else {
                double lose = Math.abs(1996 - question1);
                System.out.printf("Sorry %s, the correct answer is 1996. You lost %.2f points.%n", name, lose);
                point -= lose;
            }
            /*
             question 2
             */
            System.out.println("\nQuestion 2: How many mL are in a cup?");
            double question2 = game.nextInt();
            if (question2 >= 231.588 && question2 <= 241.588) {
                System.out.printf("Congratulations %s! You gained 100 points!%n", name);
                point += 100;
            } else {
                double dif2 = Math.abs(question2 - 236.588);
                System.out.printf("Sorry %s, the correct answer is 236.588. You lost %.2f points.%n", name, dif2);
                point -= dif2;
            }
        /*
         Question 3
         */
            System.out.println("\nQuestion 3: What was the original name of the Varsity restaurant?");
            game.nextLine();
            String question3 = game.nextLine();
            if (question3.equalsIgnoreCase("The Yellow Jacket")) {
                System.out.printf("Congratulations %s! You gained 200 points!\n", name);
                point += 200;

            } else {
                System.out.printf("Sorry %s. The correct answer is %s", name , "\"The Yellow Jacket\"\n");
            }
            System.out.printf("%n%s, Your got %.2f total points!%n", name, point);
            if (point >= hpoint) {
                hpoint = point;
            }
            System.out.printf("Your highest score is: %.2f points.%n", hpoint);
            System.out.println("\nWould you like to play again? [Y]es or [N]o");
            answer = game.next();
            if (!answer.equals("Y") || !answer.equals("N")) {
                test = false;
                if (answer.equals("Y") || answer.equals("N")) {
                    test = true;
                }
            }
            while (!test) {
                System.out.println("Please input either Y or N.");
                answer = game.next();
                System.out.println();
                if (answer.equals("Y")) {
                    test = true;
                } else if (answer.equals("N")) {
                    break;
                }
            }

        }
        if (answer.equals("N")) {
            System.out.println("Goodbye");
        }
    }
}
