package problem.p1323maximum69number;

/**
 * 1323. Maximum 69 Number
 *
 * https://leetcode-cn.com/problems/maximum-69-number/
 *
 * Given a positive integer num consisting only of digits 6 and 9.
 *
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 */

public class Solution {

    public int maximum69Number(int num) {
        for (int base = 1000; base > 0; base /= 10) {
            int bit = (num / base) % 10;
            if (bit == 6) {
                return num + 3 * base;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        assert new Solution().maximum69Number(9669) == 9969;
        assert new Solution().maximum69Number(9996) == 9999;
        assert new Solution().maximum69Number(9999) == 9999;
        assert new Solution().maximum69Number(66) == 96;
    }

}
