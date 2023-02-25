package weekly.w333.C;

/**
 * 2572. Count the Number of Square-Free Subsets
 *
 * https://leetcode.cn/problems/count-the-number-of-square-free-subsets/
 *
 * You are given a positive integer 0-indexed array nums.
 *
 * A subset of the array nums is square-free if the product of its elements is a square-free integer.
 *
 * A square-free integer is an integer that is divisible by no square number other than 1.
 *
 * Return the number of square-free non-empty subsets of the array nums.
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 * A non-empty subset of nums is an array that can be obtained by
 * deleting some (possibly none but not all) elements from nums.
 *
 * Two subsets are different if and only if the chosen indices to delete are different.
 */

public class Solution {

    private final int MOD = 1_000_000_007, NP = 10;
    private final int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    public int squareFreeSubsets(int[] nums) {
        int w = 1 << NP;
        int[] curr = new int[w];
        int[] next = new int[w];
        curr[0] = 1;

        for (int x : nums) {
            // 2*2 | 3*3 | 5*5
            if (x % 4 == 0 || x % 9 == 0 || x == 25) continue;

            int mask = 0;
            for (int j = 0; j < NP; j++) {
                if (x % primes[j] == 0) {
                    mask |= 1 << j;
                }
            }

            System.arraycopy(curr, 0, next, 0, w);
            for (int j = 0; j < w; j++) {
                if ((j & mask) == 0) {
                    next[j | mask] = (next[j | mask] + curr[j]) % MOD;
                }
            }

            int[] temp = curr;
            curr = next;
            next = temp;
        }

        int ans = 0;
        for (var v : curr) ans = (ans + v) % MOD;
        return (ans - 1 + MOD) % MOD;
    }

    public static void main(String[] args) {
        assert new Solution().squareFreeSubsets(new int[]{3,4,4,5}) == 3;
        assert new Solution().squareFreeSubsets(new int[]{1}) == 1;
    }

}
