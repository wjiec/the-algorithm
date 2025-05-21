package weekly.w448.A;

import java.util.Arrays;

/**
 * 3536. Maximum Product of Two Digits
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/maximum-product-of-two-digits/
 *
 * You are given a positive integer n.
 *
 * Return the maximum product of any two digits in n.
 *
 * Note: You may use the same digit twice if it appears more than once in n.
 */

public class Solution {

    public int maxProduct(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);

        return (chars[chars.length - 1] - '0') * (chars[chars.length - 2] - '0');
    }

    public static void main(String[] args) {
    }

}
