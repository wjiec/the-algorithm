package weekly.w347.A;

/**
 * 2710. Remove Trailing Zeros From a String
 *
 * https://leetcode.cn/contest/weekly-contest-347/problems/remove-trailing-zeros-from-a-string/
 *
 * Given a positive integer num represented as a string, return the integer num without trailing zeros as a string.
 */

public class Solution {

    public String removeTrailingZeros(String num) {
        int r = num.length() - 1;
        while (r >= 0 && num.charAt(r) == '0') r--;
        return num.substring(0, r + 1);
    }

    public static void main(String[] args) {
    }

}
