package weekly.w456.B;

import common.Checker;

import java.util.TreeMap;

/**
 * Q2. Longest Common Prefix Between Adjacent Strings After Removals
 *
 * https://leetcode.cn/contest/weekly-contest-456/problems/longest-common-prefix-between-adjacent-strings-after-removals
 *
 * You are given an array of strings words. For each index i in the range [0, words.length - 1], perform the following steps:
 *
 * Remove the element at index i from the words array.
 * Compute the length of the longest common prefix among all adjacent pairs in the modified array.
 *
 * Return an array answer, where answer[i] is the length of the longest common prefix between
 * the adjacent pairs after removing the element at index i. If no adjacent pairs remain or
 * if none share a common prefix, then answer[i] should be 0.
 */

public class Solution {

    private static class Trie {
        private int v1 = -99, v2 = -99;
        private final Trie[] tries = new Trie[26];
        public static int[] walk(Trie root, char[] chars, int i) {
            int d1 = 0, d2 = 0;
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 'a';
                if (root.tries[c] == null) root.tries[c] = new Trie();

                if (root.v1 == i - 1 || root.v2 == i - 1) d1 = j + 1;
                if (root.v1 == i - 2 || root.v2 == i - 2) d2 = j + 1;
                if (root.v1 < root.v2) root.v1 = i; else root.v2 = i;
            }
            return new int[]{d1, d2};
        }
    }

    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        // 对于所有的元素 0 1 2 3 4 5 有以下相邻对
        //  - 0     1       2       3       4       5
        //       a      b       c       d       e
        //
        // 如果我们删除元素位置 i
        //  - 对于相邻对, 则会删除 j, j - 1
        //  - 但是会新增一个 (i - 1, i + 1)

        Trie trie = new Trie();
        int[][] dp = new int[words.length - 1][2];
        TreeMap<Integer, Integer> cnt = new TreeMap<>(); cnt.put(0, 1);
        for (int i = 0; i < words.length; i++) {
            var curr = Trie.walk(trie, words[i].toCharArray(), i);
            if (i >= 1) {
                dp[i - 1] = curr;
                cnt.merge(curr[0], 1, Integer::sum);
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 移除当前位置 i, 会使得 i - 1, i + 1 相邻
            //  - 移除 i - 1 和 i 的 d1 位置
            //  - 新增 i + 1 的 d2 位置
            if (i > 0) cnt.merge(dp[i - 1][0], -1, (a, b) -> (a + b == 0) ? null : (a + b));
            if (i < dp.length) cnt.merge(dp[i][0], -1, (a, b) -> (a + b == 0) ? null : (a + b));

            // 计算最大值
            ans[i] = Math.max(cnt.lastKey(), i < dp.length ? dp[i][1] : 0);

            // 恢复位置 i
            if (i > 0) cnt.merge(dp[i - 1][0], 1, Integer::sum);
            if (i < dp.length) cnt.merge(dp[i][0], 1, Integer::sum);

        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"jump","run","run","jump","run"}), new int[]{3,0,0,3,3});
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"dog","racer","car"}), new int[]{0,0,0});
    }

}
