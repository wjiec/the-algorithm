package problem.p1759countnumberofhomogenoussubstrings;

/**
 * 1759. Count Number of Homogenous Substrings
 *
 * https://leetcode.cn/problems/count-number-of-homogenous-substrings/
 *
 * Given a string s, return the number of homogenous substrings of s.
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 * A string is homogenous if all the characters of the string are the same.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int countHomogenous(String s) {
        int ans = 0, curr = 0; char prev = ' ';
        for (var c : s.toCharArray()) {
            curr = c == prev ? curr + 1 : 1;
            ans = (ans + curr) % 1_000_000_007;
            prev = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countHomogenous("abbcccaa") == 13;
        assert new Solution().countHomogenous("xy") == 2;
        assert new Solution().countHomogenous("zzzzz") == 15;
    }

}
