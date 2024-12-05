package Week10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Song {
    private String title;
    private String artist;
    private String genre;

    public Song(String title, String artist, String genre){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

class GenreFilterIterator implements Iterator<Song> {
    private List<Song> playlist;
    private String targetGenre;
    private int index = 0;

    public GenreFilterIterator(List<Song> playlist, String targetGenre){
        this.playlist = playlist;
        this.targetGenre = targetGenre;
    }

    public boolean hasNext(){
        while(index < playlist.size()){
            if(playlist.get(index).getGenre().equals(targetGenre)){
                return true;
            }
            index++;
        }
        return false;
    }

    public Song next(){
        if(!hasNext()){
            throw new NoSuchElementException("No more songs of that genre found.");
        }
        return playlist.get(index++);
    }
}

class Main {
    public static void main(String[] args){
        List<Song> playlist = new ArrayList<>();
        playlist.add(new Song("Shape of You", "Ed Sheeran", "Pop"));
        playlist.add(new Song("Bohemian Rhapsody", "Queen", "Rock"));
        playlist.add(new Song("Rolling in the Deep", "Adele", "Pop"));
        playlist.add(new Song("Stairway to Heaven", "Led Zeppelin", "Rock"));
        playlist.add(new Song("Blinding Lights", "The Weeknd", "Pop"));

        String targetGenre = "Pop";
        GenreFilterIterator iterator = new GenreFilterIterator(playlist, targetGenre);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
