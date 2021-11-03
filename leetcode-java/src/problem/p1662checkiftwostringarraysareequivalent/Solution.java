package problem.p1662checkiftwostringarraysareequivalent;

/**
 * 1662. Check If Two String Arrays are Equivalent
 *
 * https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/
 *
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string,
 * and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 */

public class Solution {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int x = 0, y = 0, a = 0, b = 0;
        while (x < word1.length && y < word2.length) {
            for (; a < word1[x].length() && b < word2[y].length(); a++, b++) {
                if (word1[x].charAt(a) != word2[y].charAt(b)) return false;
            }
            if (a == word1[x].length()) { x++; a = 0; }
            if (b == word2[y].length()) { y++; b = 0; }
        }
        return a == 0 && b == 0 && x == word1.length && y == word2.length;
    }

    public static void main(String[] args) {
        assert !new Solution().arrayStringsAreEqual(new String[]{"abc", "d", "defg"}, new String[]{"abcddef"});
        assert !new Solution().arrayStringsAreEqual(new String[]{"a"}, new String[]{"ab"});

        assert new Solution().arrayStringsAreEqual(new String[]{"ab", "c"}, new String[]{"a", "bc"});
        assert !new Solution().arrayStringsAreEqual(new String[]{"a", "cb"}, new String[]{"ab", "c"});
        assert new Solution().arrayStringsAreEqual(new String[]{"abc", "d", "defg"}, new String[]{"abcddefg"});
    }

}
