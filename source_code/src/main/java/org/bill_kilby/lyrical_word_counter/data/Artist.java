package org.bill_kilby.lyrical_word_counter.data;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class for handling the musical Artist.
 * Contains the albums they have made, and functions to process them.
 */
public class Artist {
    private final ArrayList<String[]> albums;


    /**
     * Constructor for the Artist class.
     *
     * @param name The name of the artist.
     */
    public Artist(String name){
        this.albums = LyricScraper.scrapeArtistAlbums(name);
    }


    /**
     * Processes each album, creating an instance of {@link Album}
     * for it, and then adding the total words found in said album
     * to a Hashmap, returning it after all albums have been procesed.
     *
     * @param checkWords The words to check for.
     * @return A hashmap of every album and their respective word counts for every word.
     */
    public HashMap<String, int[]> getArtistWordCounts(String[] checkWords) {
        HashMap<String, int[]> wordCounts = new HashMap<>();
        // Loop through every album.
        for (String[] album : this.albums){
            // Create a new album class
            Album currentAlbum = new Album(album);
            // Get the word count for each song and add to the hashmap.
            System.out.println("Scanning songs from: "+album[0]);
            int[] albumWordCounts = currentAlbum.getAlbumWordCounts(checkWords);
            wordCounts.put(currentAlbum.getName(), albumWordCounts);
        }
        return wordCounts;
    }
}
