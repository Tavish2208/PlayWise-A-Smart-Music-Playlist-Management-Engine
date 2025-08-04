package playlist;

import model.Song;
import java.util.Stack;

public class PlaybackHistory {
    public Stack<Song> history;

    public PlaybackHistory() {
        history = new Stack<>();
    }

    // Called when a song is played
    public void playSong(Song song) {
        history.push(song);
        System.out.println("▶️ Played: " + song.title + " by " + song.artist);
    }

    // Undo last played song and add back to playlist
    public void undoLastPlay(PlaylistEngine playlist) {
        if (history.isEmpty()) {
            System.out.println("No songs to undo.");
            return;
        }
        Song lastPlayed = history.pop();
        playlist.addSong(lastPlayed.id, lastPlayed.title, lastPlayed.artist, lastPlayed.genre, lastPlayed.duration, lastPlayed.rating, true); // silent = true
        System.out.println("➕ Re-added: " + lastPlayed.title);
    }

    // Display play history from most recent
    public void printHistory() {
        if (history.isEmpty()) {
            System.out.println("No playback history.");
            return;
        }
        System.out.println("Playback History (most recent first):");
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i).title + " by " + history.get(i).artist);
        }
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
