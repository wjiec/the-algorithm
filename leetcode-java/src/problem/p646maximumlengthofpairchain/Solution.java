package problem.p646maximumlengthofpairchain;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 646. Maximum Length of Pair Chain
 *
 * https://leetcode-cn.com/problems/maximum-length-of-pair-chain/
 *
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 */

public class Solution {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(v -> v[1]));

        int curr = Integer.MIN_VALUE, ans = 0;
        for (var pair : pairs) {
            if (curr < pair[0]) {
                curr = pair[1];
                ans++;
            }
        }

        return ans;
    }

    public int findLongestChainDP(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(v -> v[0]));

        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                // i 可以排在 j 的后面，所以是 dp[j] + 1
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[pairs.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().findLongestChain(new int[][]{{1,2},{2,3},{3,4}}) == 2;
        assert new Solution().findLongestChain(new int[][]{{1,2},{7,8},{4,5}}) == 3;

        assert new Solution().findLongestChainDP(new int[][]{{1,2},{2,3},{3,4}}) == 2;
        assert new Solution().findLongestChainDP(new int[][]{{1,2},{7,8},{4,5}}) == 3;
    }

}
