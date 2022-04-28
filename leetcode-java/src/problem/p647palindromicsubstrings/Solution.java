package problem.p647palindromicsubstrings;

/**
 * 647. Palindromic Substrings
 *
 * https://leetcode-cn.com/problems/palindromic-substrings/
 *
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 */

public class Solution {

    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0, x = 2 * n - 1; i < x; i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l; ++r; ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSubstrings("abc") == 3;
        assert new Solution().countSubstrings("aaa") == 6;
        assert new Solution().countSubstrings("aaa") == 6;
    }

}
