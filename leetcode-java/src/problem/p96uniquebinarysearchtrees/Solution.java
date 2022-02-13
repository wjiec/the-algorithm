package problem.p96uniquebinarysearchtrees;

/**
 * 96. Unique Binary Search Trees
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 */

public class Solution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1]; dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().numTrees(1) == 1;
        assert new Solution().numTrees(2) == 2;
        assert new Solution().numTrees(3) == 5;
    }

}
