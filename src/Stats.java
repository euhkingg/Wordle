/**
 * This class keeps track of the stats of the game.
 *
 * @author Suhrobjon Jurabev
 */
import java.util.Scanner;

public class Stats {
    // instance variables
    private String playerName;
    private int winCount;
    private int gamesPlayed;
    private int streakCount;
    private int highestStreak;
    private boolean streak;

    // class constructor for a new player
    public Stats(String name) {
        playerName = name;
        winCount = 0;
        gamesPlayed = 0;
        streakCount = 0;
        streak = true; // set to true for the first round
    }

    public Stats() { // constructor for a guest player
        playerName = "Guest";
        winCount = 0;
        gamesPlayed = 0;
        streakCount = 0;
        streak = true; // set to true for the first round
    }

    // private helper method that updates stats
    private void update(boolean outcome) {
        if (outcome) { // checks for outcome of the game
            winCount++;
            if (streak == true) { // checks if player is on a treak
                streakCount++;
            }
        } else {
            if (streakCount < highestStreak) { // setting record streak
                highestStreak = streakCount;
            }
            streakCount = 0;
            streak = false;
        }
        gamesPlayed++;
    }

    // method to claculate win rate
    private double winPercentage() {
        return (double) winCount / gamesPlayed * 100;
    }

    //utility method used to get a player's name
    public static String enterName() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = s.nextLine();
        return name;
    }

    // String method that returns a string value containing the stats
    public String statistics(boolean outcome) {
        update(outcome); // calling the helper method
        String str = "Current Stats:";
        str += " \nGames Played: " + gamesPlayed
                + "\nAverage Win: " + winPercentage() + "%"
                + "\nCurrent Streak: " + streakCount
                + "\nHighestStreak: " + highestStreak + "\n";
        return str;
    }
}
