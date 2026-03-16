package weekly.bw170.B;

/**
 * Q2. Total Waviness of Numbers in Range I
 *
 * https://leetcode.cn/contest/biweekly-contest-170/problems/total-waviness-of-numbers-in-range-i/
 *
 * You are given two integers num1 and num2 representing an inclusive range [num1, num2].
 *
 * The waviness of a number is defined as the total count of its peaks and valleys:
 *
 * A digit is a peak if it is strictly greater than both of its immediate neighbors.
 * A digit is a valley if it is strictly less than both of its immediate neighbors.
 * The first and last digits of a number cannot be peaks or valleys.
 * Any number with fewer than 3 digits has a waviness of 0.
 *
 * Return the total sum of waviness for all numbers in the range [num1, num2].
 */

public class Solution {

    public int totalWaviness(int num1, int num2) {
        int ans = 0;
        for (int i = num1; i <= num2; i++) ans += waviness(String.valueOf(i).toCharArray());
        return ans;
    }

    private int waviness(char[] chars) {
        int ans = 0;
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] > chars[i - 1] && chars[i] > chars[i + 1]) ans++;
            else if (chars[i] < chars[i - 1] && chars[i] < chars[i + 1]) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
