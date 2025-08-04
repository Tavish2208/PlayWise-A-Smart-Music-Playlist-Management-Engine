package dashboard;

import model.Song;
import playlist.PlaybackHistory;
import java.util.*;

public class SystemSnapshot {

    /**
     * Get top 5 longest songs
     * Time: O(n log n), Space: O(n)
     */
    public static List<Song> getTop5Longest(List<Song> songs) {
        songs.sort((a, b) -> Integer.compare(b.duration, a.duration));
        return songs.subList(0, Math.min(5, songs.size()));
    }

    /**
     * Get recent playback history
     * Time: O(n), Space: O(n)
     */
    public static List<Song> getRecentPlays(PlaybackHistory history) {
        List<Song> recent = new ArrayList<>();
        Stack<Song> stack = new Stack<>();

        // Clone stack so we don't modify the original
        for (int i = history.history.size() - 1; i >= 0; i--) {
            recent.add(history.history.get(i));
        }

        return recent;
    }

    /**
     * Count of songs grouped by rating
     * Time: O(n), Space: O(1)
     */
    public static Map<Integer, Integer> getSongCountByRating(List<Song> songs) {
        Map<Integer, Integer> ratingCount = new HashMap<>();
        for (int i = 1; i <= 5; i++) ratingCount.put(i, 0);

        for (Song s : songs) {
            ratingCount.put(s.rating, ratingCount.getOrDefault(s.rating, 0) + 1);
        }

        return ratingCount;
    }

    /**
     * Export snapshot to console or UI
     */
    public static void exportSnapshot(List<Song> allSongs, PlaybackHistory history) {
        System.out.println("\n📊 SYSTEM SNAPSHOT");

        System.out.println("\n🎵 Top 5 Longest Songs:");
        for (Song s : getTop5Longest(new ArrayList<>(allSongs))) {
            System.out.println(" - " + s.title + " (" + s.duration + "s)");
        }

        System.out.println("\n⏪ Recently Played:");
        for (Song s : getRecentPlays(history)) {
            System.out.println(" - " + s.title);
        }

        System.out.println("\n⭐ Song Count by Rating:");
        Map<Integer, Integer> counts = getSongCountByRating(allSongs);
        for (int rating = 1; rating <= 5; rating++) {
            System.out.println("Rating " + rating + ": " + counts.get(rating));
        }
    }
}
