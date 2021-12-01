package daily.d211201p1446consecutivecharacters;

/**
 * 1446. Consecutive Characters
 *
 * https://leetcode-cn.com/problems/consecutive-characters/
 *
 * The power of the string is the maximum length of a non-empty substring that contains only one unique character.
 *
 * Given a string s, return the power of s.
 */

public class Solution {

    public int maxPower(String s) {
        int max = 0, curr = 1;
        for (int i = 1, l = s.length(); i < l; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                max = Math.max(max, curr);
                curr = 1;
            } else curr++;
        }
        return Math.max(max, curr);
    }

    public static void main(String[] args) {
        assert new Solution().maxPower("leetcode") == 2;
        assert new Solution().maxPower("abbcccddddeeeeedcba") == 5;
        assert new Solution().maxPower("triplepillooooow") == 5;
        assert new Solution().maxPower("hooraaaaaaaaaaay") == 11;
        assert new Solution().maxPower("tourist") == 1;
    }

}
