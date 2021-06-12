package problem.p844backspacestringcompare;

/**
 * 844. Backspace String Compare
 *
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 */

public class Solution {

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1, r0 = 0, r1 = 0;
        for (; i >= 0 || j >= 0; i--, j--) {
            for (; i >= 0 && (s.charAt(i) == '#' || r0 > 0); i--) r0 += s.charAt(i) == '#' ? 1 : (r0 > 0 ? -1 : 0);
            for (; j >= 0 && (t.charAt(j) == '#' || r1 > 0); j--) r1 += t.charAt(j) == '#' ? 1 : (r1 > 0 ? -1 : 0);
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
        }
        return i == j;
    }

    public static void main(String[] args) {
        assert new Solution().backspaceCompare("nzp#o#g", "b#nzp#o#g");
        assert new Solution().backspaceCompare("ab#c", "ad#c");
        assert new Solution().backspaceCompare("ab##", "c#d#");
        assert new Solution().backspaceCompare("a##c", "#a#c");
        assert !new Solution().backspaceCompare("a#c", "b");
    }

}
