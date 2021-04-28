package problem.p66plusone;

import java.util.Arrays;

/**
 * 66. Plus One
 *
 * https://leetcode-cn.com/problems/plus-one/
 *
 * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contains a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 */

public class Solution {

    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] > 9 && i != 0) {
                digits[i] = 0;
                digits[i - 1] += 1;
                continue;
            }
            break;
        }

        if (digits[0] == 10) {
            int[] rs = new int[digits.length + 1];
            rs[0] = 1;

            return rs;
        }

        return digits;
    }

    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1,2,3})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{4,3,2,1})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{0})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{9,9})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1,0,9,9})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1,9,9,9})));

        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{1,2,3})));
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{4,3,2,1})));
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{0})));
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{9,9})));
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{1,0,9,9})));
        System.out.println(Arrays.toString(new Solution().plusOne1(new int[]{1,9,9,9})));
    }

}
