package problem.p2124checkifallasappearsbeforeallbs;

/**
 * 2124. Check if All A's Appears Before All B's
 *
 * https://leetcode-cn.com/problems/check-if-all-as-appears-before-all-bs/
 *
 * Given a string s consisting of only the characters 'a' and 'b',
 * return true if every 'a' appears before every 'b' in the string.
 *
 * Otherwise, return false.
 */

public class Solution {

    public boolean checkString(String s) {
        boolean b = false;
        for (var c : s.toCharArray()) {
            if (c == 'b') b = true;
            else if (b) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().checkString("aaabbb");
        assert !new Solution().checkString("abab");
        assert new Solution().checkString("bbb");
    }

}
