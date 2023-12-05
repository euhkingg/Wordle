import java.util.Scanner;
public class Logic {
    private Words list;
    private final Scanner scan;
    private String guess;
    private String word;
    private String[][] board; // 2d array of tiles; https://www.geeksforgeeks.org/arrays-in-java/

    public Logic(String answerFile, String guessFile) {
        list = new Words(answerFile, guessFile); //list of allowed words
        scan = new Scanner(System.in);
        word = list.getRandomWord(); //random answer word
        board = new String[6][5]; //6 x 5 array
    }

    public Logic(String answerFile) {
        list = new Words(answerFile, "src/Guess.txt"); //list of allowed words
        scan = new Scanner(System.in);
        word = list.getRandomWord(); //random answer word
        board = new String[6][5]; //6 x 5 array
    }

    public String getWord() {
        return word;
    }

    public void newWord() {
        word = list.getRandomWord();
    }

    public void printBoard() { //prints board
        for(int i = 0; i < 6; i++) {
            for(int x = 0; x < 5; x++) {
                System.out.print(board[i][x]);
            }
            System.out.println("\n");
        }
    }

    public void makeBoard() { //adds color to each tile in the board
        for(int i = 0; i < 6; i++) {
            for(int x = 0; x < 5; x++) {
                board[i][x] = "\u001b[40;1m" + "   " + "\033[0m ";
            }
        }
    }

    public void changeBoard() { //takes user input and color and updates the board
        if (guessWord()) {
            for(int i = 0; i < 5; i++) {
                if(charColor(guess.charAt(i), i).equals("green")) { //https://codehs.com/tutorial/ryan/add-color-with-ansi-in-javascript
                    board[Wordle.getGuesses()][i] = "\u001b[42;1m " + guess.charAt(i) + " \033[0m "; //adding letters to the right row in the board
                } else if(charColor(guess.charAt(i), i).equals("yellow")) {
                    board[Wordle.getGuesses()][i] = "\u001b[43;1m " + guess.charAt(i) + " \033[0m ";
                } else {
                    board[Wordle.getGuesses()][i] = "\u001b[40;1m " + guess.charAt(i) + " \033[0m ";
                }
            }
        } else {
            list.printDictionary();
            System.out.println();
            Wordle.setIsPrinting(true);
            printBoard();
        }
    }

    public boolean guessWord() { //checks if the guess is a valid word
        System.out.print("Enter your guess: ");
        guess = scan.next().toUpperCase();
        if (guess.equals("/DICTIONARY")) {
            return false;
        }
        while(!list.isWordInList(guess)) {
            System.out.println("Invalid Word");
            System.out.print("Enter your guess: ");
            guess = scan.next().toUpperCase();
        }
        return true;
    }

    private String charColor(char letter, int index) { //helper method to fget color
        if (word.indexOf(letter) != -1) {
            if (word.substring(index, index + 1).equals(guess.substring(index, index + 1))) {
                return "green";
            } else {
                return "yellow";
            }
        }
        return "grey";
    }

    public boolean isGameOver(int guesses) {
        if (guesses != 0) {guesses -= 1;}
        for (int i = 0; i < 5; i++) {
            if (!board[guesses][i].contains("[42;1m")){ //checks if all the colors in a given row are green or not
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        for (int i = 0; i < 5; i++) {
            if (!board[6][i].contains("[42;1m")){ //checks if all the colors in a given row are green or not
                return false;
            }
        }
        return true;
    }

}
