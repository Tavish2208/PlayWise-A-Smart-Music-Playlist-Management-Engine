import model.Song;
import playlist.PlaylistEngine;
import playlist.PlaybackHistory;
import rating.SongRatingTree;
import lookup.SongLookup;
import playlist.SortUtils;
import dashboard.SystemSnapshot;
import summary.PlaylistSummary;
import pinned.PinnedSongsHandler;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PlaylistEngine playlist = new PlaylistEngine();
        PlaybackHistory history = new PlaybackHistory();
        SongRatingTree ratingTree = new SongRatingTree();
        SongLookup lookup = new SongLookup();

        // 🎶 Add Songs
        playlist.addSong("S1", "Vaseegara", "Bombay Jayashri", "Romantic", 270, 5);
        playlist.addSong("S2", "Why This Kolaveri Di", "Dhanush", "Folk", 230, 4);
        playlist.addSong("S3", "Munbe Vaa", "Shreya Ghoshal", "Melody", 260, 4);
        playlist.addSong("S4", "Aalaporan Thamizhan", "Kailash Kher", "Folk", 250, 5);
        playlist.addSong("S5", "Anbil Avan", "Hariharan", "Melody", 240, 3);
        playlist.addSong("S6", "Rowdy Baby", "Dhanush & Dhee", "Dance", 215, 2);

        // 🎬 Current Playlist
        System.out.println("\n🎬 Current Playlist:");
        playlist.printPlaylist();

        // ⏫ Add songs to modules (lookup + BST)
        Song current = playlist.getHead();
        List<Song> songList = new ArrayList<>();
        while (current != null) {
            songList.add(current);
            ratingTree.insertSong(current);
            lookup.addSong(current);
            current = current.next;
        }

        // ▶️ Simulate Playback
        System.out.println("\n▶️ Playing songs:");
        history.playSong(lookup.getById("S3"));
        history.playSong(lookup.getById("S1"));
        history.playSong(lookup.getById("S5"));

        // 📜 Show Full Playback History
        System.out.println("\n📜 Playback History:");
        history.printHistory();

        // ↩️ Undo Last Playback
        System.out.println("\n↩️ Undo Last Played Song:");
        history.undoLastPlay(playlist);
        playlist.printPlaylist();

        // 📸 Export Snapshot
        SystemSnapshot.exportSnapshot(songList, history);

        // 🔠 Sort by Title
        System.out.println("\n🧩 Sorted by Title (A-Z):");
        SortUtils.printSorted(SortUtils.sortByTitle(songList, true));

        // ⏱ Sort by Duration
        System.out.println("\n📏 Sorted by Duration (Longest First):");
        SortUtils.printSorted(SortUtils.sortByDuration(songList, false));

        // 🌟 Songs Grouped by Rating
        System.out.println("\n⭐ Songs Grouped by Rating:");
        for (int i = 5; i >= 1; i--) {
            List<Song> songs = ratingTree.searchByRating(i);
            if (!songs.isEmpty()) {
                System.out.println("Rating " + i + ":");
                for (Song s : songs) {
                    System.out.println("  • " + s.title + " (by " + s.artist + ")");
                }
            }
        }

        // 🔍 Lookup by Title
        System.out.println("\n🔍 Lookup by Title:");
        System.out.println(lookup.getByTitle("Aalaporan Thamizhan"));

        // 🧪 PlaylistEngine Tests
        System.out.println("\n🎬 Full Playlist:");
        playlist.printPlaylist();

        System.out.println("\n🧽 Removing song at index 2:");
        playlist.deleteSong(2);
        playlist.printPlaylist();

        System.out.println("\n🔁 Reversing playlist:");
        playlist.reversePlaylist();
        playlist.printPlaylist();

        System.out.println("\n📦 Moving song from index 0 to 2:");
        playlist.moveSong(0, 2);
        playlist.printPlaylist();

        System.out.println("\n🎧 Total Songs in Playlist: " + playlist.getSize());

        // Pinned songs Handler
        PinnedSongsHandler pinnedHandler = new PinnedSongsHandler();
        pinnedHandler.pinSong("S1", 0);
        pinnedHandler.pinSong("S4", 2);
        pinnedHandler.showPinnedSongs();

        // Playlist Summary
        PlaylistSummary.generate(songList);
    }
}