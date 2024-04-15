package weekly.bw128.A;

/**
 * 100270. Score of a String
 *
 * https://leetcode.cn/contest/biweekly-contest-128/problems/score-of-a-string/
 *
 * You are given a string s. The score of a string is defined as the sum of the absolute
 * difference between the ASCII values of adjacent characters.
 *
 * Return the score of s.
 */

public class Solution {

    public int scoreOfString(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            ans += Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
