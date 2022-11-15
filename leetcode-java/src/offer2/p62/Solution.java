package offer2.p62;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 062. 实现前缀树
 *
 * https://leetcode.cn/problems/QC3q1f/
 *
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class Trie {
        private static class Node {
            private boolean leaf = false;
            private final Map<Character, Node> children = new HashMap<>();
            private Node get(char c) { return children.get(c); }
            private Node set(char c) { return children.computeIfAbsent(c, v -> new Node()); }
            private boolean isLeaf() { return leaf; }
            private void asLeaf() { leaf = true; }
        }

        private final Node root = new Node();
        public Trie() {}

        public void insert(String word) {
            Node curr = root;
            for (var c : word.toCharArray()) curr = curr.set(c);
            curr.asLeaf();
        }

        public boolean search(String word) {
            Node curr = root;
            for (var c : word.toCharArray()) {
                if ((curr = curr.get(c)) == null) {
                    return false;
                }
            }
            return curr.isLeaf();
        }

        public boolean startsWith(String prefix) {
            Node curr = root;
            for (var c : prefix.toCharArray()) {
                if ((curr = curr.get(c)) == null) {
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
