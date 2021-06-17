package problem.p925ongpressedname;

/**
 * 925. Long Pressed Name
 *
 * https://leetcode-cn.com/problems/long-pressed-name/
 *
 * Your friend is typing his name into a keyboard. Sometimes, when typing a character c,
 * the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name,
 * with some characters (possibly none) being long pressed.
 */

public class Solution {

    public boolean isLongPressedName(String name, String typed) {
        int l0 = name.length(), l1 = typed.length();
        if (l1 < l0) return false;
        if (l1 == l0) return name.equals(typed);

        int i = 0, j = 0;
        while (j < l1) {
            if (i < l0 && name.charAt(i) == typed.charAt(j)) {
                i++; j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }

        return i == l0;
    }

    public static void main(String[] args) {
        assert !new Solution().isLongPressedName("dfuyalc", "fuuyallc");
        assert !new Solution().isLongPressedName("pyplrz", "ppyypllr");
        assert !new Solution().isLongPressedName("alex", "aaleexa");
        assert new Solution().isLongPressedName("alex", "aaleex");
        assert !new Solution().isLongPressedName("saeed", "ssaaedd");
        assert new Solution().isLongPressedName("leelee", "lleeelee");
        assert new Solution().isLongPressedName("laiden", "laiden");
    }

}
