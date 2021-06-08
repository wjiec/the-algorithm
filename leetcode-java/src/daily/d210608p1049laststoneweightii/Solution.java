package daily.d210608p1049laststoneweightii;

/**
 * 1049. Last Stone Weight II
 *
 * https://leetcode-cn.com/problems/last-stone-weight-ii/
 *
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together.
 * Suppose the stones have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 */

public class Solution {

    // @TODO dp
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length, sum = 0;
        if (n == 1) return stones[0];
        if (n == 2) return Math.abs(stones[0] - stones[1]);
        for (var weight : stones) {
            sum += weight;
        }

        int halfSum = sum / 2;
        boolean[][] dp = new boolean[n + 1][halfSum + 1]; dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= halfSum; j++) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }

        for (int i = halfSum; i >= 0; i--) {
            if (dp[n][i]) {
                return sum - 2 * i;
            }
        }
        return 0; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().lastStoneWeightII(new int[]{2,7,4,1,8,1}) == 1;
        assert new Solution().lastStoneWeightII(new int[]{31,26,33,21,40}) == 5;
        assert new Solution().lastStoneWeightII(new int[]{1,2}) == 1;
    }

}
