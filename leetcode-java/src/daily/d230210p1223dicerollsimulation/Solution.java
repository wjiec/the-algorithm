package daily.d230210p1223dicerollsimulation;

import java.util.Arrays;

/**
 * 1223. Dice Roll Simulation
 *
 * https://leetcode.cn/problems/dice-roll-simulation/
 *
 * A die simulator generates a random number from 1 to 6 for each roll.
 * You introduced a constraint to the generator such that it cannot roll
 * the number i more than rollMax[i] (1-indexed) consecutive times.
 *
 * Given an array of integers rollMax and an integer n, return the number of
 * distinct sequences that can be obtained with exact n rolls.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 * Two sequences are considered different if at least one element differs from each other.
 */

public class Solution {

    public int dieSimulator(int n, int[] rollMax) {
        final int MOD = 1_000_000_007;

        // dp[i][j] 表示投掷第 i 个骰子是 j 的次数, 且连续 k 次 j 的序列数
        //int[][][] dp = new int[n][7][16];
        // 状态压缩
        int[][] prevState = new int[7][16];
        int[][] currState = new int[7][16];
        // 初始情况下, 投掷一次骰子每个面的概率都相同
        for (int j = 1; j <= 6; j++) prevState[j][1] = 1;

        for (int i = 1; i < n; i++) { // 第 i 个骰子
            for (int prev = 1; prev <= 6; prev++) { // 上一次骰子的点数是 prev
                for (int k = 1; k <= rollMax[prev - 1]; k++) { // 连续 prev 出现的次数
                    for (int curr = 1; curr <= 6; curr++) { // 当前的骰子点数 curr
                        if (curr != prev) {
                            // 如果骰子点数不想等, 则可以直接转移到出现1次上
                            currState[curr][1] = (currState[curr][1] + prevState[prev][k]) % MOD;
                        } else if (k + 1 <= rollMax[prev - 1]) { // 如果想等且加上当前的不超过最大限制
                            // 出现次数需要加 1
                            currState[curr][k + 1] = (currState[curr][k + 1] + prevState[prev][k]) % MOD;
                        }
                    }
                }
            }

            int[][] temp = prevState;
            prevState = currState;
            currState = temp;
            for (int j = 1; j <= 6; j++) Arrays.fill(currState[j], 0);
        }

        int ans = 0;
        for (int j = 1; j <= 6; j++) {
            for (int k = 1; k <= rollMax[j - 1]; k++) {
                ans = (ans + prevState[j][k]) % MOD;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().dieSimulator(1000, new int[]{9,1,5,2,2,3}) == 867999592;

        assert new Solution().dieSimulator(2, new int[]{1,1,2,2,2,3}) == 34;
        assert new Solution().dieSimulator(2, new int[]{1,1,1,1,1,1}) == 30;
        assert new Solution().dieSimulator(3, new int[]{1,1,1,2,2,3}) == 181;
    }

}
