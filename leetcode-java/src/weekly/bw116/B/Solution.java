package weekly.bw116.B;

/**
 * 100104. Minimum Number of Changes to Make Binary String Beautiful
 *
 * https://leetcode.cn/contest/biweekly-contest-116/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
 *
 * You are given a 0-indexed binary string s having an even length.
 *
 * A string is beautiful if it's possible to partition it into one or more substrings such that:
 *
 * Each substring has an even length.
 * Each substring contains only 1's or only 0's.
 * You can change any character in s to 0 or 1.
 *
 * Return the minimum number of changes required to make the string s beautiful.
 */

public class Solution {

    public int minChanges(String s) {
        int ans = 0, n = s.length();
        for (int i = 0; i < n; i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
