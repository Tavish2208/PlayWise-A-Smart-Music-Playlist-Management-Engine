package lookup;

import model.Song;
import java.util.*;

public class SongLookup {
    private HashMap<String, Song> idMap;
    private HashMap<String, Song> titleMap;

    public SongLookup() {
        idMap = new HashMap<>();
        titleMap = new HashMap<>();
    }

    // Add song to lookup tables
    public void addSong(Song song) {
        idMap.put(song.id, song);
        titleMap.put(song.title.toLowerCase(), song);
    }

    public Song getById(String id) {
        return idMap.get(id);
    }

    public Song getByTitle(String title) {
        return titleMap.get(title.toLowerCase());
    }

    public void deleteSong(String id) {
        Song song = idMap.remove(id);
        if (song != null) {
            titleMap.remove(song.title.toLowerCase());
        }
    }

    public void printAllSongs() {
        for (String id : idMap.keySet()) {
            System.out.println(idMap.get(id));
        }
    }

    public boolean contains(String id) {
        return idMap.containsKey(id);
    }
}
