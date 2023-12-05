import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;

/**
 * This class reads from words.txt.
 *
 * @author Siheng Zheng
 */

public class Words {
    /** Contains all the words in words.txt */
    private List<String> answerList;
    private List<String> guessList;
    /**
     * Instantiates a Words object.
     */
    public Words(String answerFile, String guessFile) {
        try {
            answerList = Files.readAllLines(Paths.get(answerFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            guessList = Files.readAllLines(Paths.get(guessFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Gets a random word from wordList.
     *
     * @return random word from wordList
     */
    public String getRandomWord() {
        int randomIndex = getRandomNumber(answerList.size());
        return answerList.get(randomIndex).toUpperCase();
    }
    /**
     * Checks if a word is in wordlist.
     *
     * @param word the word to check
     * @return if the word is in wordlist
     */
    public boolean isWordInList(String word) {
        return guessList.contains(word.toLowerCase());
    }

    private int getRandomNumber(int listSize) {
        return (int) (Math.random() * listSize); //Getting random int from 0 - listsize
    }

    public void printDictionary() {
        for (int i = 0; i < guessList.size(); i++) {
            System.out.println(guessList.get(i));
        }
    }
}