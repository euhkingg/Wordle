import java.util.Scanner;
/**
 * This class executes the game Wordle.
 *
 * @author Gavin Ng
 */
public class Wordle {
    Scanner s;
    private static int guesses;
    private Logic logic;
    private String name = Stats.enterName();
    private static boolean isPrinting;
    Stats stat = new Stats(name);
    //instance variables

    public Wordle(int guessNum) {
        isPrinting = false;
        s = new Scanner(System.in);
        guesses = guessNum;
        logic = new Logic("src/Answers.txt", "src/Guess.txt");
    }

    public static int getGuesses(){ //getter method for guesses
        return guesses;
    }

    public static void setIsPrinting(boolean val) {isPrinting = val;}


    public void round() {
        rules(); //prints rules
        logic.makeBoard();
        logic.printBoard();
        while (!logic.isGameOver(guesses) && guesses < 6) {
            logic.changeBoard(); //change board given guess
            if (!isPrinting) {
                System.out.println();
                logic.printBoard();
                guesses++;
            }
            isPrinting = false;
        }
        stats(); //prints stats

        if (playAgain()) {//asks if user wants to play again
            guesses = 0;
            logic.newWord();
            round();
        } else {
            System.out.println("Thank you for playing, " + name);
        }
    }

    public boolean playAgain() { //asks if user wants to play again
        System.out.print("Do you want to play again? (y/n): ");
        return s.nextLine().equals("y") || s.nextLine().equals("yes");
    }

    public void stats(){ //sends game over message
        if (logic.isGameOver(guesses)) {
            System.out.println("You Won! The word was " + logic.getWord());
            System.out.println(stat.statistics(true));
        } else {
            System.out.println("You Lost. The word was " + logic.getWord());
            System.out.println(stat.statistics(false));
        }
    }

    public void rules() { //prints out rules
        System.out.println("\n                  WORDLE\n" +
                "✦•·················•✦•·················•✦\n" +
                "           How to Play Wordle\n\n" +
                "Each guess must be a valid five-letter word.\n\n" +
                "The color of a tile will change to show you how close your guess was.\n\n" +
                "If the tile turns green, the letter is in the word, and it is in the correct spot.\n\n" +
                "If the tile turns yellow, the letter is in the word, but it is not in the correct spot.\n\n" +
                "If the tile turns gray, the letter is not in the word.\n\n" +
                "Type /dictionary as your guess to access the Wordle Dictionary (not recommended).\n\n" +
                "✦•······•✦•······•✦\n");
    }

}
