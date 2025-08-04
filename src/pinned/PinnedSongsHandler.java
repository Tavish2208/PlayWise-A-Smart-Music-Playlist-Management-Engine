package pinned;

import java.util.*;

public class PinnedSongsHandler {
    private final Map<String, Integer> pinnedMap = new HashMap<>();

    public void pinSong(String songId, int index) {
        pinnedMap.put(songId, index);
        System.out.println("📌 Pinned song " + songId + " at index " + index);
    }

    public void unpinSong(String songId) {
        if (pinnedMap.containsKey(songId)) {
            pinnedMap.remove(songId);
            System.out.println("❌ Unpinned song " + songId);
        }
    }

    public boolean isPinned(String songId) {
        return pinnedMap.containsKey(songId);
    }

    public int getPinnedIndex(String songId) {
        return pinnedMap.getOrDefault(songId, -1);
    }

    public void showPinnedSongs() {
        System.out.println("\n📍 Pinned Songs:");
        for (Map.Entry<String, Integer> entry : pinnedMap.entrySet()) {
            System.out.println("  - " + entry.getKey() + " pinned at index " + entry.getValue());
        }
    }

    public Set<String> getAllPinnedIds() {
        return pinnedMap.keySet();
    }
}
