package problem.p1056confusingnumber;

/**
 * 1056. Confusing Number
 *
 * https://leetcode-cn.com/problems/confusing-number/
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 * We can rotate digits of a number by 180 degrees to form new digits.
 *
 * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
 * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
 * Note that after rotating a number, we can ignore leading zeros.
 *
 * For example, after rotating 8000, we have 0008 which is considered as just 8.
 * Given an integer n, return true if it is a confusing number, or false otherwise.
 */

public class Solution {

    public boolean confusingNumber(int n) {
        int v = 0;
        for (int i = n; i != 0; i /= 10) {
            switch (i % 10) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                    return false;
                case 0:
                case 1:
                case 8:
                    v = v * 10 + (i % 10);
                    break;
                case 6:
                    v = v * 10 + 9;
                    break;
                case 9:
                    v = v * 10 + 6;
                    break;
            }
        }
        return v != n;
    }

    public static void main(String[] args) {
        assert !new Solution().confusingNumber(916);

        assert new Solution().confusingNumber(6);
        assert new Solution().confusingNumber(8000);
        assert new Solution().confusingNumber(89);
        assert !new Solution().confusingNumber(11);
        assert !new Solution().confusingNumber(25);
    }

}
