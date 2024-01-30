package org.bill_kilby.lyrical_word_counter.data;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Utility class for scraping artists, albums, and songs from Lyrics.com.
 */
public final class LyricScraper {
    /**
     * Scrapes lyrics.com for every project by the artist,
     * and returns them. It calls validateAlbum() from the same class
     * to check if the album is by the artist or not.
     *
     * @param artistName The requested artist.
     * @return A list of valid albums created by the requested artist. Each entry contains ["project name", "link"].
     */
    public static ArrayList<String[]> scrapeArtistAlbums(String artistName){
        // Set up the albums list and format the URL.
        ArrayList<String[]> albums = new ArrayList<>();
        String artistURL = "https://www.lyrics.com/artist/" + artistName.replaceAll(" ", "-");
        try{
            // Get all the artist's albums (including features).
            Document artistDocument = Jsoup.connect(artistURL).get();
            Elements albumDivs = artistDocument.select(".artist-album-label");
            // Loop through every album, and check if it is their project. Only store projects which are the artist's.
            for (int albumIndex = 0; albumIndex < albumDivs.size(); albumIndex++) {
                try{
                    Element album = albumDivs.get(albumIndex);
                    // Calculate the album and output the current information to console.
                    System.out.println("Album "+(albumIndex + 1)+"/"+albumDivs.size());
                    String [] currentAlbum = new String[2];
                    currentAlbum[0] = album.selectFirst("a").text();
                    currentAlbum[1] = album.selectFirst("a").attr("href");
                    // Add if project is by the artist, or a collaboration album.
                    if(validateAlbum(currentAlbum[1], artistName)){
                        System.out.println("Adding album");
                        albums.add(currentAlbum);
                    } else {
                        System.out.println("It is not a solo album!");
                    }
                } catch (NullPointerException e){
                    // Don't stop the program - the album just doesn't exist and is an error of the website, not the program.
                    System.out.println("Album does not exist on Lyrics.com!");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        // Output that it is finished, return the found valid albums.
        System.out.println("Finished scraping artist.");
        return albums;
    }


    /**
     * Validates the artist's album, making sure that
     * the project is actually by the artist.
     *
     * @param albumLink The link (href from document) to the correct URL.
     * @param artistName The name of the artist to check.
     * @return True if by the artist, false if not.
     */
    private static boolean validateAlbum(String albumLink, String artistName){
        try{
            // Get the project's information
            System.out.println("Getting Album: https://www.lyrics.com/"+albumLink);
            Document albumDocument = Jsoup.connect("https://www.lyrics.com/"+albumLink).get();
            String albumCreator = albumDocument.selectFirst(".lyric hgroup h3 a").text();
            // Check if the project is made by the artist, returning true or false respectively.
            if (albumCreator.contains(artistName)){
                return true;
            }
        } catch (IOException e){
            // Don't stop the program - the album just doesn't exist and is an error of the website, not the program.
            System.out.println("Album doesn't exist on Lyrics.com!");
            return false;
        }
        return false;
    }


    /**
     * Scrapes the album's page for a list of the songs.
     * Unlike the artist scrape, it only returns the links
     * to all the songs on the album.
     *
     * @param albumLink The link (href) to the album.
     * @return Links to all the songs on the albums.
     */
    public static ArrayList<String> scrapeAlbumSongs(String albumLink){
        // Set up the song list and format the URL.
        ArrayList<String> songs = new ArrayList<>();
        String albumURL = "https://www.lyrics.com/"+albumLink;
        try{
            // Get all the album's song divs.
            Document albumDocument = Jsoup.connect(albumURL).get();
            Elements songLinks = albumDocument.select("table.tdata tbody a");
            for (Element link : songLinks){
                String href = link.attr("href");
                // If it is a valid link (to a song), then add.
                if(validateSong(href)){
                    songs.add(href);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return songs;
    }


    /**
     * Validates a song by making sure it is linking to
     * a lyric page, and not an artist page.
     *
     * @param songLink The song link to verify.
     * @return True if the link is valid, false if the link is invalid.
     */
    private static boolean validateSong(String songLink){
        // Check the song URL to make sure it is pointing to a "lyrics" page and not an artist one.
        if(songLink.contains("lyric")){
            return true;
        }
        return false;
    }


    /**
     * Scrapes the lyrics from the site's lyric page,
     * and returns them as a list of individual words.
     *
     * @param songLink The link to the song page.
     * @return The lyrics to the song as a list of individual words.
     */
    public static String[] scrapeSongLyrics(String songLink) {
        // Set up string for the lyrics, and format the URL.
        String lyrics = "";
        String songURL = "https://www.lyrics.com/"+songLink;
        try{
            // Get the song's lyrical content.
            Document songDocument = Jsoup.connect(songURL).get();
            lyrics = songDocument.selectFirst("pre#lyric-body-text").text();
            // Cleaning up the content.
            lyrics = lyrics.replaceAll("\n", " ");
        } catch (IOException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            // The song is not documented on the website, so just continue.
            System.out.println("Song doesn't exist on Lyrics.com!");
        }
        return lyrics.split(" ");
    }
}
