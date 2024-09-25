package weekly.w415.D;

import common.Tag;

import java.util.*;

/**
 * 3292. Minimum Number of Valid Strings to Form Target II
 *
 * https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-ii/description/
 *
 * You are given an array of strings words and a string target.
 *
 * A string x is called valid if x is a prefix of any string in words.
 *
 * Return the minimum number of valid strings that can be concatenated to form target.
 *
 * If it is not possible to form target, return -1.
 * @noinspection AssertWithSideEffects
 */

public class Solution {

    /** @noinspection BooleanMethodIsAlwaysInverted*/
    private static class Trie {
        private Trie fail = null;
        private int lens = 0;
        private final Map<Character, Trie> children = new HashMap<>();
        private boolean contains(char c) { return children.containsKey(c); }
        private Trie get(char c) { return children.get(c); }
        private Trie next(char c) { return children.computeIfAbsent(c, k -> new Trie()); }
    }

    private record Ref(Trie node, char c, Trie parent) {}

    @Tag({"AC自动机", "DP优化的AC自动机"})
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        Trie root = new Trie();

        // 构建字典树
        for (var word : words) {
            Trie curr = root;
            for (int i = 0; i < word.length() && i < n; i++) {
                curr = curr.next(word.charAt(i));
                curr.lens = Math.max(curr.lens, i + 1);
            }
        }

        // 构建失配指针
        root.fail = root;
        Queue<Ref> q = new ArrayDeque<>();
        for (var e : root.children.entrySet()) {
            q.add(new Ref(e.getValue(), e.getKey(), root));
        }

        while (!q.isEmpty()) {
            for (int i = 0, l = q.size(); i < l; i++) {
                var curr = q.remove();

                var fail = curr.parent.fail;
                while (fail != null && fail != root && !fail.contains(curr.c)) fail = fail.fail;
                curr.node.fail = (fail == null || fail.get(curr.c) == null || fail.get(curr.c) == curr.node) ? root : fail.get(curr.c);
                curr.node.lens = Math.max(curr.node.lens, curr.node.fail.lens);

                for (var e : curr.node.children.entrySet()) {
                    q.add(new Ref(e.getValue(), e.getKey(), curr.node));
                }
            }
        }

        Trie curr = root;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1); dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            char c = target.charAt(i - 1);
            while (curr != root && !curr.contains(c)) curr = curr.fail;
            if (curr == root && !curr.contains(c)) return -1;

            curr = curr.get(c);
            dp[i] = Math.min(dp[i], dp[i - curr.lens] + 1);
        }

        return dp[n] > n ? -1 : dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().minValidStrings(new String[]{"a","babc"}, "aacab") == -1;
        assert new Solution().minValidStrings(new String[]{"caacbbbbbcaccbcbcccbcbbbcbcabbcaaacabbcbbccabcac","baccccb","aacabcca"}, "aacaacbabb") == 5;
        assert new Solution().minValidStrings(new String[]{"babccdecceddbdaddcddcacedb"}, "b") == 1;

        assert new Solution().minValidStrings(new String[]{"abc","aaaaa","bcdef"}, "aabcdabc") == 3;
        assert new Solution().minValidStrings(new String[]{"abababab","ab"}, "ababaababa") == 2;
        assert new Solution().minValidStrings(new String[]{"abcdef"}, "xyz") == -1;
    }

}
