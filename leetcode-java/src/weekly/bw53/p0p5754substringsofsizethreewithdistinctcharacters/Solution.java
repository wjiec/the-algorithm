package weekly.bw53.p0p5754substringsofsizethreewithdistinctcharacters;

/**
 * 5754. Substrings of Size Three with Distinct Characters
 *
 * https://leetcode-cn.com/problems/substrings-of-size-three-with-distinct-characters/
 *
 * A string is good if there are no repeated characters.
 *
 * Given a string s, return the number of good substrings of length three in s.
 *
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public int countGoodSubstrings(String s) {
        int n = s.length(), ans = 0;
        if (n < 3) return 0;

        char pp = s.charAt(0), p = s.charAt(1), c;
        for (int i = 2; i < n; i++) {
            c = s.charAt(i);
            if (pp != p && pp != c && p != c) {
                ans++;
            }
            pp = p; p = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodSubstrings("xyzzaz") == 1;
        assert new Solution().countGoodSubstrings("aababcabc") == 4;
    }

}
