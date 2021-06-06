package problem.p720longestwordindictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 720. Longest Word in Dictionary
 *
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/
 *
 * Given an array of strings words representing an English Dictionary,
 * return the longest word in words that can be built one character at a time by other words in words.
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * If there is no answer, return the empty string.
 */

public class Solution {

    private static class TrieTree {

        private static class Node {
            private char val;
            private int index;
            private Map<Character, Node> children = new HashMap<>();
            public Node(char ch) { val = ch; }
            public Node next(char c) {
                children.putIfAbsent(c, new Node(c));
                return children.get(c);
            }
        }

        private final Node root;

        TrieTree() { root = new Node('-'); }
        public void add(String s, int index) {
            Node curr = root;
            for (var c : s.toCharArray()) {
                curr = curr.next(c);
            }
            curr.index = index;
        }
        public String dfs(String[] words) {
            String ans = "";
            Stack<Node> stack = new Stack<>(); stack.addAll(root.children.values());
            while (!stack.empty()) {
                var node = stack.pop();
                if (node.index > 0) {
                    String word = words[node.index - 1];
                    if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                        ans = word;
                    }
                    stack.addAll(node.children.values());
                }
            }

            return ans;
        }
    }

    public String longestWord(String[] words) {
        TrieTree tree = new TrieTree();
        for (int i = 0; i < words.length; i++) {
            tree.add(words[i], i + 1); // avoid index-0
        }
        return tree.dfs(words);
    }

    public static void main(String[] args) {
        assert new Solution().longestWord(new String[]{"w","wo","wor","worl","world"}).equals("world");
        assert new Solution().longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"}).equals("apple");
    }

}
