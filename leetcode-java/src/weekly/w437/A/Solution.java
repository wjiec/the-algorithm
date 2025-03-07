package weekly.w437.A;

/**
 * 3456. Find Special Substring of Length K
 *
 * https://leetcode.cn/contest/weekly-contest-437/problems/find-special-substring-of-length-k/
 *
 * You are given a string s and an integer k.
 *
 * Determine if there exists a substring of length exactly k in s that satisfies the following conditions:
 *
 * The substring consists of only one distinct character (e.g., "aaa" or "bbb").
 * If there is a character immediately before the substring, it must be different from the character in the substring.
 * If there is a character immediately after the substring, it must also be different from the character in the substring.
 *
 * Return true if such a substring exists. Otherwise, return false.
 */

public class Solution {

    public boolean hasSpecialSubstring(String s, int k) {
        int[] count = new int[128]; int n = s.length();
        for (int l = 0, r = 0; r < n; r++) {
            char curr = s.charAt(r);
            if (r >= k) count[s.charAt(l++)]--;
            if (++count[curr] == k && (r + 1 >= n || s.charAt(r + 1) != curr) && (l - 1 < 0 || s.charAt(l - 1) != curr)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().hasSpecialSubstring("h", 1);
    }

}
