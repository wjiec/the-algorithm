package problem.p367validperfectsquare;

/**
 * 367. Valid Perfect Square
 *
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 */

public class Solution {

    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        int l = 1, r = num / 2;
        while (l < r) {
            int mid = l + (r - l) / 2;
            double v = (double) num / mid;
            if (v == mid) {
                return true;
            } else if (v < mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l * l == num;
    }

    public static void main(String[] args) {
        assert new Solution().isPerfectSquare(1);
        assert new Solution().isPerfectSquare(4);
        assert new Solution().isPerfectSquare(9);
        assert new Solution().isPerfectSquare(16);
        assert new Solution().isPerfectSquare(25);
        assert new Solution().isPerfectSquare(36);
        assert !new Solution().isPerfectSquare(14);
        assert !new Solution().isPerfectSquare(681);
        assert new Solution().isPerfectSquare(808201);
    }

}
