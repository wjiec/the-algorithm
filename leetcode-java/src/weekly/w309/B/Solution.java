package weekly.w309.B;

/**
 * 6168. Number of Ways to Reach a Position After Exactly k Steps
 *
 * https://leetcode.cn/contest/weekly-contest-309/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/
 *
 * You are given two positive integers startPos and endPos. Initially, you are standing at position startPos
 * on an infinite number line. With one step, you can move either one position
 * to the left, or one position to the right.
 *
 * Given a positive integer k, return the number of different ways to reach the position endPos
 * starting from startPos, such that you perform exactly k steps. Since the answer may be very
 * large, return it modulo 109 + 7.
 *
 * Two ways are considered different if the order of the steps made is not exactly the same.
 *
 * Note that the number line includes negative integers.
 */

public class Solution {

    public int numberOfWays(int startPos, int endPos, int k) {
        int MOD = 1_000_000_007;
        long[] curr = new long[4001];
        long[] next = new long[4001];
        curr[id(startPos)] = 1;

        for (int i = 0; i < k; i++) {
            for (int j = 1; j < 4000; j++) {
                next[j - 1] = (curr[j] + next[j - 1]) % MOD;
                next[j + 1] = (curr[j] + next[j + 1]) % MOD;
                curr[j] = 0;
            }

            long[] temp = curr;
            curr = next;
            next = temp;
        }
        return (int) (curr[id(endPos)] % MOD);
    }

    private int id(int v) { return 2000 + v; }

    public static void main(String[] args) {
        assert new Solution().numberOfWays(989, 1000, 99) == 934081896;

        assert new Solution().numberOfWays(1, 2, 3) == 3;
        assert new Solution().numberOfWays(2, 5, 10) == 0;
    }

}
