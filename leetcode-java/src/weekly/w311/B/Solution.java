package weekly.w311.B;

/**
 * 6181. Length of the Longest Alphabetical Continuous Substring
 *
 * https://leetcode.cn/contest/weekly-contest-311/problems/length-of-the-longest-alphabetical-continuous-substring/
 *
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet.
 * In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the
 * longest alphabetical continuous substring.
 */

public class Solution {

    public int longestContinuousSubstring(String s) {
        int ans = 1, curr = 1;
        char prev = s.charAt(0);
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] - prev == 1) curr++;
            else curr = 1;
            ans = Math.max(ans, curr);
            prev = chars[i];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
