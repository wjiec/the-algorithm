package weekly.bw141.C;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 3316. Find Maximum Removals From Source String
 *
 * https://leetcode.cn/problems/find-maximum-removals-from-source-string/
 *
 * You are given a string source of size n, a string pattern that is a
 * subsequence
 *  of source, and a sorted integer array targetIndices that contains distinct numbers in the range [0, n - 1].
 *
 * We define an operation as removing a character at an index idx from source such that:
 *
 * idx is an element of targetIndices.
 * pattern remains a subsequence of source after removing the character.
 * Performing an operation does not change the indices of the other characters in source.
 *
 * For example, if you remove 'c' from "acb", the character at index 2 would still be 'b'.
 *
 * Return the maximum number of operations that can be performed.
 */

public class Solution {

    private final Set<Integer> banned = new HashSet<>();

    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        memo = new int[source.length()][pattern.length() + 1];
        for (var row : memo) Arrays.fill(row, -1);

        for (var i : targetIndices) banned.add(i);
        return dp(source.toCharArray(), source.length() - 1, pattern.toCharArray(), pattern.length() - 1);
    }

    private int[][] memo = null;

    private int dp(char[] source, int i, char[] pattern, int j) {
        if (i < j) return Integer.MIN_VALUE;
        if (i < 0) return 0;

        // 如果 pattern 已经过完了, 那么 j 就是 -1, 需要排除这种情况
        long key = (long) i << 32 | (j + 1);
        if (memo[i][j + 1] != -1) return memo[i][j + 1];

        // 其次考虑丢弃 source[i], 如果这个索引在集合中, 那我们就找到了一个可以删除的索引
        int ans = dp(source, i - 1, pattern, j) + (banned.contains(i) ? 1 : 0);
        // 首先考虑保留 source[i], 如果与 pattern[j] 匹配, 则缩小 j 范围
        if (j >= 0 && source[i] == pattern[j]) ans = Math.max(ans, dp(source, i - 1, pattern, j - 1));

        memo[i][j + 1] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxRemovals("edde", "de", new int[]{1}) == 1;

        assert new Solution().maxRemovals("abbaa", "aba", new int[]{0,1,2}) == 1;
        assert new Solution().maxRemovals("bcda", "d", new int[]{0,3}) == 2;
        assert new Solution().maxRemovals("dda", "dda", new int[]{0,1,2}) == 0;
        assert new Solution().maxRemovals("yeyeykyded", "yeyyd", new int[]{0,2,3,4}) == 2;
    }

}
