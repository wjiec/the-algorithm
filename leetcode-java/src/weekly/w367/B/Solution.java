package weekly.w367.B;

/**
 * 2904. Shortest and Lexicographically Smallest Beautiful String
 *
 * https://leetcode.cn/contest/weekly-contest-367/problems/shortest-and-lexicographically-smallest-beautiful-string/
 *
 * You are given a binary string s and a positive integer k.
 *
 * A substring of s is beautiful if the number of 1's in it is exactly k.
 *
 * Let len be the length of the shortest beautiful substring.
 *
 * Return the lexicographically smallest beautiful substring of string s with length equal to len.
 * If s doesn't contain a beautiful substring, return an empty string.
 *
 * A string a is lexicographically larger than a string b (of the same length) if in the first position
 * where a and b differ, a has a character strictly larger than the corresponding character in b.
 *
 * For example, "abcd" is lexicographically larger than "abcc" because the first position they
 * differ is at the fourth character, and d is greater than c.
 */

public class Solution {

    public String shortestBeautifulSubstring(String s, int k) {
        String ans = ""; int n = s.length();
        for (int l = 0, r = 0, c = 0; r < n; r++) {
            c += s.charAt(r) - '0';
            while (l < r && (c > k || s.charAt(l) == '0')) c -= s.charAt(l++) - '0';
            if (c == k) {
                String curr = s.substring(l, r + 1);
                if (ans.length() == 0 || curr.length() < ans.length()) ans = curr;
                if (curr.length() == ans.length() && curr.compareTo(ans) < 0) ans = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestBeautifulSubstring("100011001", 3).equals("11001");
        assert new Solution().shortestBeautifulSubstring("1011", 2).equals("11");
        assert new Solution().shortestBeautifulSubstring("000", 1).equals("");
    }

}
