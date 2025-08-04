package playlist;

import model.Song;

/**
 * PlaylistEngine using Doubly Linked List
 */
public class PlaylistEngine {
    private Song head;
    private Song tail;
    private int size;

    /**
     * Adds a song to the end of the playlist.
     * Time: O(1), Space: O(1)
     */
    public void addSong(String id, String title, String artist, String genre, int duration, int rating) {
        addSong(id, title, artist, genre, duration, rating, false); // default: not silent
    }

    public void addSong(String id, String title, String artist, String genre, int duration, int rating, boolean silent) {
        Song newSong = new Song(id, title, artist, genre, duration, rating);
        if (head == null) {
            head = tail = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        size++;

        if (!silent) {
            System.out.println("➕ Added: " + title + " by " + artist);
        }
    }

    /**
     * Deletes a song at the given index.
     * Time: O(n), Space: O(1)
     */
    public void deleteSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }

        Song current = head;
        for (int i = 0; i < index; i++) current = current.next;

        if (current.prev != null) current.prev.next = current.next;
        else head = current.next;

        if (current.next != null) current.next.prev = current.prev;
        else tail = current.prev;

        size--;
    }

    /**
     * Moves a song from one index to another.
     * Optimized with single traversal and null safety.
     * Time: O(n), Space: O(1)
     */
    public void moveSong(int fromIndex, int toIndex) {
        if (fromIndex == toIndex || fromIndex < 0 || toIndex < 0 || fromIndex >= size || toIndex >= size) {
            System.out.println("Invalid move operation.");
            return;
        }

        Song fromNode = null, toNode = null;
        Song current = head;

        for (int i = 0; i <= Math.max(fromIndex, toIndex); i++) {
            if (i == fromIndex) fromNode = current;
            if (i == toIndex) toNode = current;
            current = current.next;
        }

        // Null safety
        if (fromNode == null || toNode == null) {
            System.out.println("One or both positions are invalid.");
            return;
        }

        // Unlink fromNode
        if (fromNode.prev != null) fromNode.prev.next = fromNode.next;
        else head = fromNode.next;

        if (fromNode.next != null) fromNode.next.prev = fromNode.prev;
        else tail = fromNode.prev;

        // Reinsert before toNode
        if (toNode == head) {
            fromNode.next = head;
            fromNode.prev = null;
            head.prev = fromNode;
            head = fromNode;
        } else {
            Song before = toNode.prev;
            fromNode.next = toNode;
            fromNode.prev = before;
            before.next = fromNode;
            toNode.prev = fromNode;
        }
    }

    /**
     * Reverses the playlist.
     * Time: O(n), Space: O(1)
     */
    public void reversePlaylist() {
        Song curr = head;
        Song temp = null;

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }

        if (temp != null) head = temp.prev;
    }

    /**
     * Prints all songs in the playlist.
     * Time: O(n), Space: O(1)
     */
    public void printPlaylist() {
        Song current = head;
        int index = 0;
        while (current != null) {
            System.out.println(index + ": " + current);
            current = current.next;
            index++;
        }
    }

    public int getSize() {
        return size;
    }

    public Song getHead() {
        return head;
    }
}
