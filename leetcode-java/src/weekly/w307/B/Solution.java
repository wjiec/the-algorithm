package weekly.w307.B;

/**
 * 6166. Largest Palindromic Number
 *
 * https://leetcode.cn/contest/weekly-contest-307/problems/largest-palindromic-number/
 *
 * You are given a string num consisting of digits only.
 *
 * Return the largest palindromic integer (in the form of a string) that can be formed using digits
 * taken from num. It should not contain leading zeroes.
 *
 * Notes:
 *
 * You do not need to use all the digits of num, but you must use at least one digit.
 * The digits can be reordered.
 */

public class Solution {

    public String largestPalindromic(String num) {
        int[] nums = new int[10];
        for (var c : num.toCharArray()) {
            nums[c - '0']++;
        }

        StringBuilder prefix = new StringBuilder();
        StringBuilder suffix = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (nums[i] >= 2) {
                prefix.append((char) ('0' + i));
                suffix.append((char) ('0' + i));
                nums[i] -= 2;
            }
        }

        for (int i = 9; i >= 0; i--) {
            if (nums[i] != 0) {
                prefix.append((char) ('0' + i));
                break;
            }
        }

        int zero = 0;
        String ans = prefix + suffix.reverse().toString();
        while (zero < ans.length() && ans.charAt(zero) == '0') zero++;
        if (zero == ans.length()) return "0";
        if (zero == 0) return ans;

        return ans.substring(zero, ans.length() - zero);
    }

    public static void main(String[] args) {
        assert new Solution().largestPalindromic("444947137").equals("7449447");
        assert new Solution().largestPalindromic("00009").equals("9");
        assert new Solution().largestPalindromic("000").equals("0");
    }

}
