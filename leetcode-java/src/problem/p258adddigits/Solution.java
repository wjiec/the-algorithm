package problem.p258adddigits;

/**
 * 258. Add Digits
 *
 * https://leetcode-cn.com/problems/add-digits/
 *
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 */

public class Solution {

    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            for (; num != 0; num /= 10) {
                sum += num % 10;
            }
            num = sum;
        }
        return num;
    }

    public int incredible(int num) {
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        assert new Solution().addDigits(38) == 2;
        assert new Solution().addDigits(0) == 0;

        assert new Solution().incredible(38) == 2;
        assert new Solution().incredible(0) == 0;
    }

}
