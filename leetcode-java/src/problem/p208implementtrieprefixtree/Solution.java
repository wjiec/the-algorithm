package problem.p208implementtrieprefixtree;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and
 * retrieve keys in a dataset of strings. There are various applications of this data structure,
 * such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before),
 * and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word
 * that has the prefix prefix, and false otherwise.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class Trie {

        private static class Node {
            private final char c;
            private boolean leaf = false;
            private final Map<Character, Node> children;
            public Node(char c) { this.c = c; children = new HashMap<>(); }
            public Node next(char c) { return children.get(c); }
            public Node insert(char c) {
                if (!children.containsKey(c)) {
                    children.put(c, new Node(c));
                }
                return children.get(c);
            }
        }

        private final Node root = new Node(' ');
        public Trie() {}

        public void insert(String word) {
            Node curr = root;
            for (var c : word.toCharArray()) {
                curr = curr.insert(c);
            }
            curr.leaf = true;
        }

        public boolean search(String word) {
            Node curr = root;
            for (var c : word.toCharArray()) {
                curr = curr.next(c);
                if (curr == null) {
                    return false;
                }
            }
            return curr.leaf;
        }

        public boolean startsWith(String prefix) {
            Node curr = root;
            for (var c : prefix.toCharArray()) {
                curr = curr.next(c);
                if (curr == null) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        assert trie.search("apple");
        assert !trie.search("app");
        assert trie.startsWith("app");
        trie.insert("app");
        assert trie.search("app");
    }

}
