package weekly.w329.A;

/**
 * 2544. Alternating Digit Sum
 *
 * https://leetcode.cn/problems/alternating-digit-sum/
 *
 * You are given a positive integer n. Each digit of n has a sign according to the following rules:
 *
 * The most significant digit is assigned a positive sign.
 * Each other digit has an opposite sign to its adjacent digits.
 *
 * Return the sum of all digits with their corresponding sign.
 */

public class Solution {

    public int alternateDigitSum(int n) {
        char[] chars = String.valueOf(n).toCharArray();

        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            ans += (chars[i] - '0') * (i % 2 == 0 ? 1 : -1);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
