package problem.p1866numberofwaystorearrangestickswithksticksvisible;

import java.util.Arrays;

/**
 * 1866. Number of Ways to Rearrange Sticks With K Sticks Visible
 *
 * https://leetcode.cn/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible
 *
 * There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange
 * the sticks such that exactly k sticks are visible from the left. A stick is visible from the
 * left if there are no longer sticks to the left of it.
 *
 * For example, if the sticks are arranged [1,3,2,5,4], then the sticks with
 * lengths 1, 3, and 5 are visible from the left.
 *
 * Given n and k, return the number of such arrangements. Since the answer
 * may be large, return it modulo 109 + 7.
 */

public class Solution {

    public int rearrangeSticks(int n, int k) {
        final int MOD = 1_000_000_007;
        long[] curr = new long[k + 1]; curr[0] = 1;
        long[] next = new long[k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                next[j] = (curr[j - 1] + (i - 1) * curr[j]) % MOD;
            }

            long[] temp = curr;
            curr =  next; next = temp;
            Arrays.fill(next, 0);
        }
        return (int) curr[k];
    }

    public static void main(String[] args) {
        assert new Solution().rearrangeSticks(3, 2) == 2;
        assert new Solution().rearrangeSticks(5, 5) == 1;
        assert new Solution().rearrangeSticks(20, 11) == 647427950;
    }

}
