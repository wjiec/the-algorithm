package problem.p211designaddandsearchwordsdatastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 211. Design Add and Search Words Data Structure
 *
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 *
 * Design a data structure that supports adding new words and finding
 * if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that
 * matches word or false otherwise. word may contain dots '.' where dots can be
 * matched with any letter.
 */

public class Solution {

    private static class WordDictionary {
        private static class Node {
            private boolean leaf = false;
            private final Map<Character, Node> subs;
            public Node() { subs = new HashMap<>(); }
            public void end() { leaf = true; }
            public boolean isLeaf() { return leaf; }
            public Node insert(char c) {
                if (!subs.containsKey(c)) {
                    subs.put(c, new Node());
                }
                return subs.get(c);
            }
            private Node search(char c) { return subs.get(c); }
            private Collection<Node> any() { return subs.values(); }
        }

        private final Node root = new Node();
        public WordDictionary() {}

        public void addWord(String word) {
            Node curr = root;
            for (var c : word.toCharArray()) {
                curr = curr.insert(c);
            }
            curr.end();
        }

        public boolean search(String word) { return search(word.toCharArray(), 0, root); }

        private boolean search(char[] chars, int i, Node parent) {
            if (parent == null) return false;
            if (i == chars.length) return parent.isLeaf();

            if (chars[i] == '.') {
                for (var next : parent.any()) {
                    if (search(chars, i + 1, next)) {
                        return true;
                    }
                }
                return false;
            }

            return search(chars, i + 1, parent.search(chars[i]));
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assert !wordDictionary.search("pad");
        assert wordDictionary.search("bad");
        assert wordDictionary.search(".ad");
        assert wordDictionary.search("b..");
        assert wordDictionary.search("...");
    }

}
