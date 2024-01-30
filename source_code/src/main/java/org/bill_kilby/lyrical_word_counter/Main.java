package org.bill_kilby.lyrical_word_counter;
import org.bill_kilby.lyrical_word_counter.data.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The main class for handling input handling,
 * Artist object creation and output handling.
 */
public class Main {
    /**
     * The main function which handles all the project setup.
     *
     * @param args The arguments passed in by the user.
     */
    public static void main(String[] args) {
        // Return if no arguments.
        if (args.length == 0 || args.length > 2){
            System.out.println("ERROR: Please provide two arguments in the format <'Artist Name', 'Words to Find'>");
            return;
        }
        // Otherwise, process arguments.
        ArrayList<Object> processedArguments = processArgs(args);
        String artistName = (String) processedArguments.get(0);
        String[] wordsToFind = (String[]) processedArguments.get(1);
        // Create an Artist object with select name.
        Artist currentArtist = new Artist(artistName);
        // Gets the word counts, representated as {ALBUMNAME, WORDCOUNTS}
        HashMap<String, int[]> wordCounts = currentArtist.getArtistWordCounts(wordsToFind);
        // Output the results.
        outputResults(wordCounts, wordsToFind);
    }


    /**
     * Processes the arguments passed in by the user,
     * saving the first, and splitting the second into
     * a list of strings.
     *
     * @param args The arguments entered by the user.
     * @return The post-processed arguments.
     */
    private static ArrayList<Object> processArgs(String[] args){
        ArrayList<Object> processedArgs = new ArrayList<>();
        // First argument is the same, second is a list of all words entered.
        processedArgs.add(args[0]);
        processedArgs.add(args[1].split(" "));
        return processedArgs;
    }


    /**
     * Outputs the results obtained through the scraping.
     *
     * @param results The word counts found.
     * @param words The words which were searched for.
     */
    private static void outputResults(HashMap<String, int[]> results, String[] words){
        results.forEach((album, count) ->{
            // Output album name, and then loop through the counts of each word.
            System.out.println("\n"+album);
            for (int word = 0; word < words.length; word++){
                System.out.println("- "+words[word]+": "+count[word]);
            }
        });
    }
}