package rating;

import model.Song;

import java.util.*;

public class SongRatingTree {

    private class RatingNode {
        int rating;
        List<Song> songs;
        RatingNode left, right;

        RatingNode(int rating) {
            this.rating = rating;
            songs = new ArrayList<>();
        }
    }

    private RatingNode root;

    public void insertSong(Song song) {
        root = insert(root, song.rating, song);
    }

    private RatingNode insert(RatingNode node, int rating, Song song) {
        if (node == null) {
            RatingNode newNode = new RatingNode(rating);
            newNode.songs.add(song);
            return newNode;
        }
        if (rating < node.rating) {
            node.left = insert(node.left, rating, song);
        } else if (rating > node.rating) {
            node.right = insert(node.right, rating, song);
        } else {
            node.songs.add(song);
        }
        return node;
    }

    public List<Song> searchByRating(int rating) {
        RatingNode node = search(root, rating);
        return node != null ? node.songs : Collections.emptyList();
    }

    private RatingNode search(RatingNode node, int rating) {
        if (node == null || node.rating == rating) return node;
        return rating < node.rating ? search(node.left, rating) : search(node.right, rating);
    }

    public void deleteSong(String id) {
        root = delete(root, id);
    }

    private RatingNode delete(RatingNode node, String id) {
        if (node == null) return null;

        // Check each song list
        Iterator<Song> it = node.songs.iterator();
        while (it.hasNext()) {
            if (it.next().id.equals(id)) {
                it.remove();
                break;
            }
        }

        // If list empty, remove node from BST
        if (node.songs.isEmpty()) {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            RatingNode successor = minValue(node.right);
            node.rating = successor.rating;
            node.songs = successor.songs;
            node.right = deleteNode(node.right, successor.rating);
        }

        node.left = delete(node.left, id);
        node.right = delete(node.right, id);
        return node;
    }

    private RatingNode deleteNode(RatingNode node, int rating) {
        if (node == null) return null;
        if (rating < node.rating) {
            node.left = deleteNode(node.left, rating);
        } else if (rating > node.rating) {
            node.right = deleteNode(node.right, rating);
        } else {
            return (node.left == null) ? node.right : node.left;
        }
        return node;
    }

    private RatingNode minValue(RatingNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void printTree() {
        System.out.println("Songs grouped by rating:");
        inOrderPrint(root);
    }

    private void inOrderPrint(RatingNode node) {
        if (node == null) return;
        inOrderPrint(node.left);
        System.out.println("Rating " + node.rating + ":");
        for (Song s : node.songs) {
            System.out.println("  - " + s);
        }
        inOrderPrint(node.right);
    }
}
