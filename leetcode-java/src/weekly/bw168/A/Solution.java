package weekly.bw168.A;

/**
 * Q1. Lexicographically Smallest String After Reverse
 *
 *ttps://leetcode.cn/contest/biweekly-contest-168/problems/lexicographically-smallest-string-after-reverse/
 *
 * You are given a string s of length n consisting of lowercase English letters.
 *
 * You must perform exactly one operation by choosing any integer k such that 1 <= k <= n and either:
 *
 * reverse the first k characters of s, or
 * reverse the last k characters of s.
 *
 * Return the lexicographically smallest string that can be obtained after exactly one such operation.
 */

public class Solution {

    public String lexSmallest(String s) {
        String ans = ""; int n = s.length();
        char[] chars = s.toCharArray();
        for (int k = 1; k <= n; k++) {
            String pre = reverse(chars, 0, k - 1);
            if (ans.isEmpty() || pre.compareTo(ans) < 0) ans = pre;
            String post = reverse(chars, n - k, n - 1);
            if (ans.isEmpty() || post.compareTo(ans) < 0) ans = post;
        }
        return ans;
    }

    private String reverse(char[] chars, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            char c = chars[i]; chars[i] = chars[j]; chars[j] = c;
        }

        String ans = new String(chars);
        for (int i = l, j = r; i < j; i++, j--) {
            char c = chars[i]; chars[i] = chars[j]; chars[j] = c;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
