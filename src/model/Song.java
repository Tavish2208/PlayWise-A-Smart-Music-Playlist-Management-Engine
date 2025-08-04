package model;

public class Song {
    public String id;
    public String title;
    public String artist;
    public String genre;
    public int duration; // in seconds
    public int rating;   // 1 to 5
    public long addedTime; // for sorting by recently added

    public Song prev, next; // for doubly linked list

    public Song(String id, String title, String artist, String genre, int duration, int rating) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.addedTime = System.currentTimeMillis(); // captures time of addition
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + "s, Rating: " + rating + ")";
    }
}
