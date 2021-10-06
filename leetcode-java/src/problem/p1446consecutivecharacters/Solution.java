package problem.p1446consecutivecharacters;

/**
 * 1446. Consecutive Characters
 *
 * https://leetcode-cn.com/problems/consecutive-characters/
 *
 * The power of the string is the maximum length of a non-empty substring
 * that contains only one unique character.
 *
 * Given a string s, return the power of s.
 */

public class Solution {

    public int maxPower(String s) {
        int ans = 0, curr = 0, prev = 0;
        for (var c : s.toCharArray()) {
            if (c == prev) curr++;
            else {
                ans = Math.max(ans, curr);
                prev = c;
                curr = 1;
            }
        }
        return Math.max(ans, curr);
    }

    public static void main(String[] args) {
        assert new Solution().maxPower("leetcode") == 2;
        assert new Solution().maxPower("abbcccddddeeeeedcba") == 5;
        assert new Solution().maxPower("triplepillooooow") == 5;
        assert new Solution().maxPower("hooraaaaaaaaaaay") == 11;
        assert new Solution().maxPower("tourist") == 1;
    }

}
