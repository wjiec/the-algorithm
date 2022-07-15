package problem.p1208getequalsubstringswithinbudget;

/**
 * 1208. Get Equal Substrings Within Budget
 *
 * https://leetcode.cn/problems/get-equal-substrings-within-budget/
 *
 * You are given two strings s and t of the same length and an integer maxCost.
 *
 * You want to change s to t. Changing the ith character of s to ith character of t
 * costs |s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters).
 *
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding
 * substring of t with a cost less than or equal to maxCost. If there is no substring from s that can be
 * changed to its corresponding substring from t, return 0.
 */

public class Solution {

    public int equalSubstring(String s, String t, int maxCost) {
        char[] a = s.toCharArray(), b = t.toCharArray();

        int ans = 0, curr = 0, n = a.length;
        for (int l = 0, r = 0; r < n; r++) {
            curr += Math.abs(a[r] - b[r]);
            for (; l < r && curr > maxCost; l++) {
                curr -= Math.abs(a[l] - b[l]);
            }
            if (curr <= maxCost) ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().equalSubstring("abcd", "cdef", 1) == 0;

        assert new Solution().equalSubstring("abcd", "bcdf", 3) == 3;
        assert new Solution().equalSubstring("abcd", "cdef", 3) == 1;
        assert new Solution().equalSubstring("abcd", "acde", 0) == 1;
    }

}
