# Lyrical Word Counter

A Java web scraper that calculates the number of specified words in an artist's discography, filtered by album.

## Project Overview

This project allows an artist's discography to be scanned by scraping [lyrics.com](https://www.lyrics.com/). 
This tool can be used to find patterns, trends, and more in a musical artist's evolution. 

The tool is completely run through the command line - please see below for instructions on how to run the project.
The project has been compiled as an uber Jar so that fewer dependencies are required. Please see below for the dependencies required.

### Note About Accuracy

This project scrapes from [lyrics.com](https://www.lyrics.com/) which has some minor discrepancies in data.
For example, some artists are missing albums, some albums are missing songs, and some songs are missing lyrics.
These discrepancies are rare, but should be noted if used for data collection.

## Getting Started

### Dependencies

* [Java 18.0+](https://www.oracle.com/java/technologies/downloads/) 

### Installing

For just the calendar generation:
* Download the `lyrical-word-counter.jar` file.

If you are interested in the documentation:
* JDoc documentation can be found under the `/docs/` folder, or [on my website.](https://billkilby.dev/docs/lyrical_word_counter/index.html)

### Executing program

#### Running the script

* Run the project by executing the following line in the same directory as the jar file.
  * The first argument, which must be a string, is the artist's name. 
  * The second argument, which must be a string, are the words you want to search for.
```
java -jar lyrical-word-counter.jar "ARTIST NAME" "WORDS TO SEARCH FOR"
```

## FAQ

### What limitations are there?
This project only analyses solo content - albums, singles, EPs, and more, which are under other names,
or its creators classified as "various artists", will not be scanned and their totals will not be calculated.

### Why is the artist not being found?
Make sure that the artist's name is stylized correctly as found on [lyrics.com](https://www.lyrics.com/). For example,
"A$AP" should not be "ASAP".
