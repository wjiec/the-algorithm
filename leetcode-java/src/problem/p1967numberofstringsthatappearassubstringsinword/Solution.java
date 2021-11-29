package problem.p1967numberofstringsthatappearassubstringsinword;

/**
 * 1967. Number of Strings That Appear as Substrings in Word
 *
 * https://leetcode-cn.com/problems/number-of-strings-that-appear-as-substrings-in-word/
 *
 * Given an array of strings patterns and a string word, return the number of strings
 * in patterns that exist as a substring in word.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (var pattern : patterns) {
            if (word.contains(pattern))
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numOfStrings(new String[]{"a","abc","bc","d"}, "abc") == 3;
        assert new Solution().numOfStrings(new String[]{"a","b","c"}, "aaaaabbbbb") == 2;
        assert new Solution().numOfStrings(new String[]{"a","a","a"}, "ab") == 3;
    }

}
