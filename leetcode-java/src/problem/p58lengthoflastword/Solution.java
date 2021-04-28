package problem.p58lengthoflastword;

/**
 * 58. Length of Last Word
 *
 * https://leetcode-cn.com/problems/length-of-last-word/
 *
 * Given a string s consists of some words separated by spaces, return the length of the last word in the string.
 * If the last word does not exist, return 0.
 *
 * A word is a maximal substring consisting of non-space characters only.
 */

public class Solution {

    public int lengthOfLastWord(String s) {
        int rs = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == ' ') {
                if (rs != 0) {
                    return rs;
                }
            } else {
                ++rs;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLastWord("hello world") == 5;
        assert new Solution().lengthOfLastWord("hello world  ") == 5;
        assert new Solution().lengthOfLastWord(" ") == 0;
        assert new Solution().lengthOfLastWord("  ") == 0;
        assert new Solution().lengthOfLastWord("") == 0;
        assert new Solution().lengthOfLastWord("a") == 1;
    }

}
