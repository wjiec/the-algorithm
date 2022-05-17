package problem.p823binarytreeswithfactors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 823. Binary Trees With Factors
 *
 * https://leetcode.cn/problems/binary-trees-with-factors/
 *
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times.
 * Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 */

public class Solution {

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);

        long[] dp = new long[arr.length];
        Arrays.fill(dp, 1);

        final int MOD = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) map.put(arr[i], i);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] % arr[j] == 0) { // j is left
                    int right = arr[i] / arr[j];
                    if (map.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[map.get(right)]) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (var v : dp) ans = (ans + v) % MOD;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().numFactoredBinaryTrees(new int[]{2,4}) == 3;
        assert new Solution().numFactoredBinaryTrees(new int[]{2,4,5,10}) == 7;
    }

}
