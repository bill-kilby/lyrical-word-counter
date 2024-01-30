package org.bill_kilby.lyrical_word_counter.data;


/**
 * Class for handling the artist's Song.
 * Contains the lyrics for the song, and functions to process them.
 */
public class Song {
    private String[] lyrics;


    /**
     * Constructor for the Song class.
     *
     * @param songLink The link to the song.
     */
    public Song(String songLink){
        this.lyrics = LyricScraper.scrapeSongLyrics(songLink);
    }


    /**
     * Processes the lyrics, going through and calculating
     * the amount of time each word in words appears.
     *
     * @param words The words to search for.
     * @return The amount of time each word appears.
     */
    public int[] getSongWordCount(String[] words){
        System.out.println("Scanning song for word occurrences...");
        int[] wordCount = new int[words.length];
        // Loop through every word, storing its count in wordCount
        for (int word = 0; word < wordCount.length; word++){
            wordCount[word] = this.getSingleWordCount(words[word]);
        }
        return wordCount;
    }


    /**
     * Goes through the lyrics, returning the amount of time
     * the word appears.
     *
     * @param toFind The word to find.
     * @return The amount of times the word appears.
     */
    private int getSingleWordCount(String toFind){
        // Set up through words.
        int wordCount = 0;
        // Loop through lyrics, find word.
        for (String word : this.lyrics){
            String currentWord = word.toUpperCase().strip().replaceAll("[^A-Za-z0-9]", "");
            if (currentWord.equals(toFind.toUpperCase())){
                wordCount++;
            }
        }
        return wordCount;
    }
}
