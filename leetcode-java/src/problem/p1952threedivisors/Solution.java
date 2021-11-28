package problem.p1952threedivisors;

/**
 * 1952. Three Divisors
 *
 * https://leetcode-cn.com/problems/three-divisors/
 *
 * Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
 *
 * An integer m is a divisor of n if there exists an integer k such that n = k * m.
 */

public class Solution {

    public boolean isThree(int n) {
        int c = 0, e = (int) Math.sqrt(n);
        for (int i = 1; i <= e; i++) {
            if (n % i == 0) {
                c += (n / i != i) ? 2 : 1;
            }
        }
        return c == 3;
    }

    public static void main(String[] args) {
        assert !new Solution().isThree(14);

        assert !new Solution().isThree(2);
        assert new Solution().isThree(4);
    }

}
