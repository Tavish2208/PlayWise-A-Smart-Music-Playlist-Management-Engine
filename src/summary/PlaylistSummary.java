package summary;

import model.Song;
import java.util.*;

public class PlaylistSummary {
    public static void generate(List<Song> songs) {
        Map<String, Integer> genreMap = new HashMap<>();
        Set<String> artists = new HashSet<>();
        int totalDuration = 0;

        for (Song song : songs) {
            totalDuration += song.duration;
            artists.add(song.artist);
            genreMap.put(song.genre, genreMap.getOrDefault(song.genre, 0) + 1);
        }

        System.out.println("\n📊 Playlist Summary:");
        System.out.println("🕒 Total Duration: " + totalDuration + " seconds");
        System.out.println("🎙️ Unique Artists: " + artists.size());
        System.out.println("🎼 Genre Count:");
        for (String genre : genreMap.keySet()) {
            System.out.println("  - " + genre + ": " + genreMap.get(genre));
        }
    }
}
