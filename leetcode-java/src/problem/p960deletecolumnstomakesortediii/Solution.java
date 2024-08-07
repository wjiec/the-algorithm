package problem.p960deletecolumnstomakesortediii;

/**
 * 960. Delete Columns to Make Sorted III
 *
 * https://leetcode.cn/problems/delete-columns-to-make-sorted-iii/description/
 *
 * You are given an array of n strings strs, all of the same length.
 *
 * We may choose any deletion indices, and we delete all the characters in those indices for each string.
 *
 * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then
 * the final array after deletions is ["bef", "vyz"].
 *
 * Suppose we chose a set of deletion indices answer such that after deletions, the final
 * array has every string (row) in lexicographic order.
 * (i.e., (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and
 * (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so on).
 *
 * Return the minimum possible value of answer.length.
 */

public class Solution {

    // 最长非递减子序列的二维版本
    public int minDeletionSize(String[] strs) {
        char[][] words = new char[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            words[i] = strs[i].toCharArray();
        }

        int n = words[0].length;
        int[] dp = new int[n]; dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (gte(words, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = n;
        for (var v : dp) ans = Math.min(ans, n - v);
        return ans;
    }

    private boolean gte(char[][] words, int l, int r) {
        for (var word : words) {
            if (word[r] < word[l]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minDeletionSize(new String[]{"baabab"}) == 2;
    }

}
