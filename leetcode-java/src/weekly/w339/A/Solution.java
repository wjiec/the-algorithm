package weekly.w339.A;

/**
 * 2609. Find the Longest Balanced Substring of a Binary String
 *
 * https://leetcode.cn/contest/weekly-contest-339/problems/find-the-longest-balanced-substring-of-a-binary-string/
 *
 * You are given a binary string s consisting only of zeroes and ones.
 *
 * A substring of s is considered balanced if all zeroes are before ones and the number of zeroes is equal to
 * the number of ones inside the substring. Notice that the empty substring is considered a balanced substring.
 *
 * Return the length of the longest balanced substring of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int findTheLongestBalancedSubstring(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 1; i < n; i++) {
            if (chars[i] == '1' && chars[i - 1] == '0') {
                int l = i - 1, r = i;
                while (l >= 0 && r < n && chars[l] == '0' && chars[r] == '1') {
                    ans = Math.max(ans, r - l + 1);
                    l--; r++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
