package playlist;

import model.Song;
import java.util.*;

public class SortUtils {

    public static List<Song> sortByTitle(List<Song> songs, boolean ascending) {
        return mergeSort(songs, (a, b) -> ascending
                ? a.title.compareToIgnoreCase(b.title)
                : b.title.compareToIgnoreCase(a.title));
    }

    public static List<Song> sortByDuration(List<Song> songs, boolean ascending) {
        return mergeSort(songs, (a, b) -> ascending
                ? Integer.compare(a.duration, b.duration)
                : Integer.compare(b.duration, a.duration));
    }

    public static List<Song> sortByAddedTime(List<Song> songs, boolean newestFirst) {
        return mergeSort(songs, (a, b) -> newestFirst
                ? Long.compare(b.addedTime, a.addedTime)
                : Long.compare(a.addedTime, b.addedTime));
    }

    private static List<Song> mergeSort(List<Song> songs, Comparator<Song> comparator) {
        if (songs.size() <= 1) return songs;

        int mid = songs.size() / 2;
        List<Song> left = mergeSort(new ArrayList<>(songs.subList(0, mid)), comparator);
        List<Song> right = mergeSort(new ArrayList<>(songs.subList(mid, songs.size())), comparator);

        return merge(left, right, comparator);
    }

    private static List<Song> merge(List<Song> left, List<Song> right, Comparator<Song> comparator) {
        List<Song> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    // Utility to print songs
    public static void printSorted(List<Song> sortedList) {
        for (Song s : sortedList) {
            System.out.println(s);
        }
    }
}
