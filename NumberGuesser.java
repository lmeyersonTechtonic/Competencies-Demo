import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGuesser {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        Scanner in;
        String playerName;
        Integer randomNum;
        Integer playerGuess = 0;
        String gameConf = "";
        Integer upperLimit = 3;
        NumberGuesser ng = new NumberGuesser();

        in = new Scanner(System.in);

        playerName = ng.getPlayerName(in);

        gameConf = ng.toPlayOrNotToPlay(in, playerName, gameConf);

        if (gameConf.equals("y")) {
            while (gameConf.equals("y")) {
                randomNum = ng.newRando(upperLimit);
                ng.playGame(upperLimit, playerGuess, randomNum, in, ng);
                gameConf = ng.toPlayOrNotToPlay(in, playerName, gameConf);
            }
        }
        System.out.println("Goodbye, " + playerName + ".");
    }

    private void playGame(Integer maxNum, Integer numberGuess, Integer randomNumber, Scanner input, NumberGuesser ng) {
        System.out.println("Guess a number between 1 and " + maxNum + " (inclusive): ");
        while(!numberGuess.equals(randomNumber)) {
                try {
                    numberGuess = input.nextInt();
                } 
                catch(InputMismatchException e) {
                    System.out.println(ng.colorRed("input must be a whole number: "));
                    input.nextLine(); // capture the newline character to prevent an infinite loop here
                    continue;
                }
                ng.continueGame(numberGuess, randomNumber);
            }
        System.out.println(ng.colorYellow("You got it! The number was " + randomNumber));
    }

    private String toPlayOrNotToPlay(Scanner yesOrNo, String playerName, String confirmation) {
        if(confirmation.equals("")) {
            System.out.println("Hello, " + playerName + ". Shall we play a game?: Y/n");
        } else {
            System.out.println("Shall we play again?: Y/n");
        }
        String gameConfirmation = yesOrNo.next();
        // allows for confirmation with "y", "Y", "yes",... anything that start with a "y"
        return gameConfirmation.substring(0,1).toLowerCase();
    }

    private String getPlayerName(Scanner input) {
        System.out.println("Please enter your name: ");
        return input.next();
    } 

    private void continueGame(Integer num1, Integer num2) {
        if (!num1.equals(num2)) {
                    System.out.println("No, guess again: ");
        }
    }

    private Integer newRando(Integer maxNum) {
        return (int) (Math.random() * maxNum + 1);
    }

    private String colorRed(String msg) {
        return ANSI_RED + msg + ANSI_RESET;
    }

    private String colorYellow(String msg) {
        return ANSI_YELLOW + msg + ANSI_RESET;
    }
}