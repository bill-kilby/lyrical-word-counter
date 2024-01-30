package org.bill_kilby.lyrical_word_counter.data;
import java.util.ArrayList;


/**
 * Class for handling the artist's Album.
 * Contains the songs on the album, and the name of the album, and functions to process them.
 */
public class Album {
    private final String name;
    private final ArrayList<String> songs;


    /**
     * Constructor for the Album class.
     *
     * @param albumInfo The information of the album. The first entry is the name, and the second the link.
     */
    public Album(String[] albumInfo) {
        this.name = albumInfo[0];
        this.songs = LyricScraper.scrapeAlbumSongs(albumInfo[1]);
    }


    /**
     * Gets the album word counts by looping through every {@link Song}
     * instance for the album, collecting its word count for every word,
     * and returning this as an array.
     *
     * @param words The words to search for.
     * @return The word count for every word.
     */
    public int[] getAlbumWordCounts(String[] words) {
        int[] albumWordCount = new int[words.length];
        // Loop through every song on the album.
        System.out.println("Starting song scanning...");
        for (String link : songs){
            System.out.println("Currently scanning song: https://www.lyrics.com/"+link);
            Song currentSong = new Song(link);
            int[] songWordCount = currentSong.getSongWordCount(words);
            // Add the total on.
            for (int word = 0; word < songWordCount.length; word++){
                albumWordCount[word] = albumWordCount[word] + songWordCount[word];
            }
        }
        System.out.println("Song scanning finished for this album!");
        return albumWordCount;
    }


    /**
     * Gets the name of the album.
     *
     * @return The name of the album.
     */
    public String getName(){
        return this.name;
    }
}
