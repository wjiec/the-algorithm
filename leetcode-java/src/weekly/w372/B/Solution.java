package weekly.w372.B;

/**
 * 2938. Separate Black and White Balls
 *
 * https://leetcode.cn/contest/weekly-contest-372/problems/separate-black-and-white-balls/
 *
 * There are n balls on a table, each ball has a color black or white.
 *
 * You are given a 0-indexed binary string s of length n, where 1 and 0 represent black and white balls, respectively.
 *
 * In each step, you can choose two adjacent balls and swap them.
 *
 * Return the minimum number of steps to group all the black balls to the right and all the white balls to the left.
 */

public class Solution {

    public long minimumSteps(String s) {
        long ans = 0, black = 0;
        for (var c : s.toCharArray()) {
            if (c == '0') ans += black;
            if (c == '1') black++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
