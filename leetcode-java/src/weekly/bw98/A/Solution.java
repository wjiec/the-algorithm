package weekly.bw98.A;

/**
 * 6359. Maximum Difference by Remapping a Digit
 *
 * https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit/
 *
 * You are given an integer num. You know that Danny Mittal will sneakily
 * remap one of the 10 possible digits (0 to 9) to another digit.
 *
 * Return the difference between the maximum and minimum values Danny can make
 * by remapping exactly one digit in num.
 *
 * Notes:
 *
 * When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
 * Danny can remap a digit to itself, in which case num does not change.
 * Danny can remap different digits for obtaining minimum and maximum values respectively.
 * The resulting number after remapping can contain leading zeroes.
 * We mentioned "Danny Mittal" to congratulate him on being in the top 10 in Weekly Contest 326.
 */

public class Solution {

    public int minMaxDifference(int num) {
        int max = num, min = num;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                int curr = Integer.valueOf(String.valueOf(num).replace((char) ('0' + i), (char) ('0' + j)), 10);
                max = Math.max(max, curr);
                min = Math.min(min, curr);
            }
        }
        return max - min;
    }

    public static void main(String[] args) {
    }

}
