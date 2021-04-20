package problem.p3longestsubstringwithoutrepeatingcharacters;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 */

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstring("") == 0;
        assert new Solution().lengthOfLongestSubstring("a") == 1;
    }

}
