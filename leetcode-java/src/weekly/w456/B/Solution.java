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

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        // 对于所有的元素 0 1 2 3 4 5 有以下相邻对
        //  - 0     1       2       3       4       5
        //       a      b       c       d       e
        //
        // 如果我们删除元素位置 i
        //  - 对于相邻对, 则会删除 j, j - 1
        //  - 但是会新增一个 (i - 1, i + 1)

        int[] cp = new int[n - 1];
        TreeMap<Integer, Integer> m = new TreeMap<>(); m.put(0, 1);
        for (int i = 0; i < n - 1; i++) {
            cp[i] = longestCommonPrefix(words, i, i + 1);
            m.merge(cp[i], 1, Integer::sum);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 移除 cp[i] 和 cp[i - 1]
            if (i < cp.length) m.merge(cp[i], -1, (a, b) -> (a + b == 0) ? null : (a + b));
            if (i - 1 >= 0) m.merge(cp[i - 1], -1, (a, b) -> (a + b == 0) ? null : (a + b));
            ans[i] = Math.max(m.lastKey(), longestCommonPrefix(words, i - 1, i + 1));
            // 恢复 cp[i] 和 cp[i - 1]
            if (i < cp.length) m.merge(cp[i], 1, Integer::sum);
            if (i - 1 >= 0) m.merge(cp[i - 1], 1, Integer::sum);
        }
        return ans;
    }

    private int longestCommonPrefix(String[] words, int i, int j) {
        if (i < 0 || j >= words.length) return 0;
        int minLen = Math.min(words[i].length(), words[j].length());
        for (int k = 0; k < minLen; k++) {
            if (words[i].charAt(k) != words[j].charAt(k)) {
                return k;
            }
        }
        return minLen;
    }

    private static class Optimization {
        int[] longestCommonPrefix(String[] words) {
            int[] cp = new int[words.length - 1];
            for (int i = 1; i < words.length; i++) {
                cp[i - 1] = lcp(words, i - 1, i);
            }

            // prefix[i] 表示在原字符串中 [0, i] 的相邻对的最大公共长度
            int[] prefix = new int[cp.length + 1];
            for (int i = 0; i < cp.length; i++) prefix[i + 1] = Math.max(prefix[i], cp[i]);

            // suffix[i] 表示在原字符串中 [i, n) 的相邻对的最大公共长度
            int[] suffix = new int[cp.length + 1];
            for (int i = cp.length - 1; i >= 0; i--) {
                suffix[i] = Math.max(suffix[i + 1], cp[i]);
            }

            int[] ans = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                ans[i] = Math.max(Math.max(i > 0 ? prefix[i - 1] : 0, i + 1 < words.length ? suffix[i + 1] : 0), lcp(words, i - 1, i + 1));
            }

            return ans;
        }

        private int lcp(String[] words, int i, int j) {
            if (i < 0 || j >= words.length) return 0;
            int minLen = Math.min(words[i].length(), words[j].length());
            for (int k = 0; k < minLen; k++) {
                if (words[i].charAt(k) != words[j].charAt(k)) return k;
            }
            return minLen;
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Optimization().longestCommonPrefix(new String[]{"jump","run","run","jump","run"}), new int[]{3,0,0,3,3});
        assert Checker.check(new Optimization().longestCommonPrefix(new String[]{"dog","racer","car"}), new int[]{0,0,0});

        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"jump","run","run","jump","run"}), new int[]{3,0,0,3,3});
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"dog","racer","car"}), new int[]{0,0,0});
    }

}
