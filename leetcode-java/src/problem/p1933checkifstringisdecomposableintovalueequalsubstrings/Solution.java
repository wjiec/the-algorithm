package problem.p1933checkifstringisdecomposableintovalueequalsubstrings;

/**
 * 1933. Check if String Is Decomposable Into Value-Equal Substrings
 *
 * https://leetcode-cn.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/
 *
 * A value-equal string is a string where all characters are the same.
 *
 * For example, "1111" and "33" are value-equal strings.
 * In contrast, "123" is not a value-equal string.
 * Given a digit string s, decompose the string into some number of consecutive value-equal substrings
 * where exactly one substring has a length of 2 and the remaining substrings have a length of 3.
 *
 * Return true if you can decompose s according to the above rules. Otherwise, return false.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public boolean isDecomposable(String s) {
        int l = 0, n = s.length(), c = 1;
        for (int r = 0; r < n; l = r) {
            while (r < n && s.charAt(r) == s.charAt(l)) r++;
            switch ((r - l) % 3) {
                case 1: return false;
                case 2: if (c-- == 0) return false; break;
            }
        }
        return c == 0;
    }

    public static void main(String[] args) {
        assert !new Solution().isDecomposable("000111000");
        assert new Solution().isDecomposable("00011111222");
        assert !new Solution().isDecomposable("01110002223300");
    }

}
