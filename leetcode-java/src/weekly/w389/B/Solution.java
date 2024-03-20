package weekly.w389.B;

/**
 * 3084. Count Substrings Starting and Ending with Given Character
 *
 * https://leetcode.cn/contest/weekly-contest-389/problems/count-substrings-starting-and-ending-with-given-character/
 *
 * You are given a string s and a character c.
 *
 * Return the total number of substrings of s that start and end with c.
 */

public class Solution {

    public long countSubstrings(String s, char c) {
        long ans = 0, curr = 0;
        for (var ch : s.toCharArray()) {
            if (ch == c) ans += ++curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
