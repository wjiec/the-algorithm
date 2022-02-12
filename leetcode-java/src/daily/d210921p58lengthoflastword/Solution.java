package daily.d210921p58lengthoflastword;

/**
 * 58. Length of Last Word
 *
 * https://leetcode-cn.com/problems/length-of-last-word/
 *
 * Given a string s consisting of some words separated by some number of spaces,
 * return the length of the last word in the string.
 *
 * A word is a maximal substring consisting of non-space characters only.
 */

public class Solution {

    public int lengthOfLastWord(String s) {
        int ans = 0, l = s.length();
        for (int i = l - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && ans != 0) break;
            else if (s.charAt(i) != ' ') ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLastWord("Hello World") == 5;
        assert new Solution().lengthOfLastWord("   fly me   to   the moon  ") == 4;
        assert new Solution().lengthOfLastWord("luffy is still joyboy") == 6;
    }

}
