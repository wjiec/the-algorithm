package weekly.bw149.A;

/**
 * 3438. Find Valid Pair of Adjacent Digits in String
 *
 * https://leetcode.cn/contest/biweekly-contest-149/problems/find-valid-pair-of-adjacent-digits-in-string/
 *
 * You are given a string s consisting only of digits. A valid pair is defined as two adjacent digits in s such that:
 *
 * The first digit is not equal to the second.
 * Each digit in the pair appears in s exactly as many times as its numeric value.
 *
 * Return the first valid pair found in the string s when traversing from left to right.
 *
 * If no valid pair exists, return an empty string.
 */

public class Solution {

    public String findValidPair(String s) {
        int[] digits = new int[10];
        for (var c : s.toCharArray()) digits[c - '0']++;
        for (int i = 1; i < s.length(); i++) {
            int a = s.charAt(i - 1) - '0', b = s.charAt(i) - '0';
            if (a != b && digits[a] == a && digits[b] == b) {
                return String.valueOf(a) + b;
            }
        }
        return "";
    }

    public static void main(String[] args) {
    }

}
