package daily.d210611p279perfectsquares;

/**
 * 279. Perfect Squares
 *
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words,
 * it is the product of some integer with itself.
 *
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 */

public class Solution {

    private static int[] squares = new int[]{
        1, 4, 9, 16, 25, 36, 49, 64, 81, 100,
        121, 144, 169, 196, 225, 256, 289, 324, 361, 400,
        441, 484, 529, 576, 625, 676, 729, 784, 841, 900,
        961, 1024, 1089, 1156, 1225, 1296, 1369, 1444, 1521, 1600,
        1681, 1764, 1849, 1936, 2025, 2116, 2209, 2304, 2401, 2500,
        2601, 2704, 2809, 2916, 3025, 3136, 3249, 3364, 3481, 3600,
        3721, 3844, 3969, 4096, 4225, 4356, 4489, 4624, 4761, 4900,
        5041, 5184, 5329, 5476, 5625, 5776, 5929, 6084, 6241, 6400,
        6561, 6724, 6889, 7056, 7225, 7396, 7569, 7744, 7921, 8100,
        8281, 8464, 8649, 8836, 9025, 9216, 9409, 9604, 9801, 10000,
    };

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; squares[j] <= i; j++) {
                min = Math.min(min, dp[i - squares[j]]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().numSquares(43) == 3;
        assert new Solution().numSquares(12) == 3;
        assert new Solution().numSquares(13) == 2;
    }

}
