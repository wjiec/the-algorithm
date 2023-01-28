package lcci.s17.p25wordrectanglelcci;

import common.PrettyPrinter;

import java.util.*;

/**
 * 面试题 17.25. 单词矩阵
 *
 * https://leetcode.cn/problems/word-rectangle-lcci/?favorite=xb9lfcwi
 *
 * 给定一份单词的清单，设计一个算法，创建由字母组成的面积最大的矩形，其中每一行组成一个单词(自左向右)，
 * 每一列也组成一个单词(自上而下)。不要求这些单词在清单里连续出现，但要求所有行等长，所有列等高。
 *
 * 如果有多个面积最大的矩形，输出任意一个均可。一个单词可以重复使用。
 */

public class Solution {

    private static class Trie {
        private int maxLength = 0;
        private boolean leaf = false;
        private final Trie[] children = new Trie[26];
        public Trie set(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new Trie();
            }
            return children[c - 'a'];
        }
        public Trie get(char c) { return children[c - 'a']; }
    }

    private int area = 0, maxLength = 0;
    private List<char[]> list = new ArrayList<>();
    private final Map<Integer, List<char[]>> map = new HashMap<>();

    public String[] maxRectangle(String[] words) {
        Trie root = new Trie();
        for (var word : words) {
            maxLength = Math.max(maxLength, word.length());
            map.computeIfAbsent(word.length(), v -> new ArrayList<>())
                .add(word.toCharArray());

            Trie curr = root;
            for (var c : word.toCharArray()) {
                curr = curr.set(c);
                curr.maxLength = Math.max(curr.maxLength, word.length());
            }
            curr.leaf = true;
        }

        for (int i = 1; i <= maxLength; i++) {
            for (int j = i; j <= maxLength; j++) {
                if (map.containsKey(i) && map.containsKey(j) && i * j > area) {
                    Trie[] condition = new Trie[i];
                    Arrays.fill(condition, root);
                    dfs(i, j, map.get(i), new ArrayDeque<>(), condition, 0);
                }
            }
        }

        String[] ans = new String[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new String(list.get(i));
        }

        return ans;
    }

    private boolean dfs(int w, int h, List<char[]> candidates, Deque<char[]> curr, Trie[] condition, int n) {
        if (curr.size() == h) {
            if (n == w) {
                area = w * h;
                list = new ArrayList<>(curr);
                return true;
            }
            return false;
        }

        Trie[] next = new Trie[w];
        for (var candidate : candidates) {
            int trues = 0;
            boolean done = true;

            for (int i = 0; i < w; i++) {
                next[i] = condition[i].get(candidate[i]);
                if (next[i] == null || next[i].maxLength < h) { done = false; break; }
                trues += next[i].leaf ? 1 : 0;
            }

            if (done) {
                curr.addLast(candidate);
                if (dfs(w, h, candidates, curr, next, trues)) {
                    return true;
                }
                curr.removeLast();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().maxRectangle(new String[]{"this", "real", "hard", "trh", "hea", "iar", "sld"}));
        PrettyPrinter.println(new Solution().maxRectangle(new String[]{"aa"}));
    }

}
