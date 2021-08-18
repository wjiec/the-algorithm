package problem.p1175primearrangements;

/**
 * 1175. Prime Arrangements
 *
 * https://leetcode-cn.com/problems/prime-arrangements/
 *
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
 *
 * (Recall that an integer is prime if and only if it is greater than 1,
 * and cannot be written as a product of two positive integers both smaller than it.)
 *
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 */

public class Solution {

    public int numPrimeArrangements(int n) {
        if (n < 3) return 1;

        int count = 1;
        boolean[] composites = new boolean[n + 1];
        for (int i = 2; i * i < composites.length; i++) {
            if (!composites[i]) {
                for (int j = i * i; j < composites.length; j += i) {
                    if (!composites[j]) {
                        count++;
                        composites[j] = true;
                    }
                }
            }
        }

        if (n < 8) count = n - count;

        long ans = 1;
        for (int i = 2; i <= count; i++) {
            ans = (ans * i) % 1000000007;
            if (i == n - count) {
                ans = (ans * ans) % 1000000007;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().numPrimeArrangements(5) == 12;
        assert new Solution().numPrimeArrangements(100) == 682289015;
    }

}
