package problem.p1180countsubstringswithonlyonedistinctletter;

/**
 * 1180. Count Substrings with Only One Distinct Letter
 *
 * https://leetcode-cn.com/problems/count-substrings-with-only-one-distinct-letter/
 *
 * Given a string s, return the number of substrings that have only one distinct letter.
 */

public class Solution {

    public int countLetters(String s) {
        s += ".";
        int ans = 0, l = 0;
        for (int r = 1; r < s.length(); r++) {
            if (s.charAt(r) != s.charAt(l)) {
                ans += count(r - l);
                l = r;
            }
        }
        return ans;
    }

    private int count(int n) {
        return n * (1 + n) / 2;
    }

    public static void main(String[] args) {
        assert new Solution().countLetters("aaaba") == 8;
        assert new Solution().countLetters("aaaaaaaaaa") == 55;
    }

}
