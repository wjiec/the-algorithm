package problem.p1492thekthfactorofn;

/**
 * 1492. The kth Factor of n
 *
 * https://leetcode.cn/problems/the-kth-factor-of-n/
 *
 * You are given two positive integers n and k. A factor of an
 * integer n is defined as an integer i where n % i == 0.
 *
 * Consider a list of all factors of n sorted in ascending order, return
 * the kth factor in this list or return -1 if n has less than k factors.
 */

public class Solution {

    public int kthFactor(int n, int k) {
        int factor = 1;
        for (; factor * factor < n; factor++) {
            if (n % factor == 0 && --k == 0) {
                return factor;
            }
        }

        if (factor * factor != n) factor--;
        for (; factor >= 1; factor--) {
            if (n % factor == 0 && --k == 0) {
                return n / factor;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().kthFactor(12, 3) == 3;
        assert new Solution().kthFactor(7, 2) == 7;
        assert new Solution().kthFactor(4, 4) == -1;
    }

}
