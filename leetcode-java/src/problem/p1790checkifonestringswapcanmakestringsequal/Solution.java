package problem.p1790checkifonestringswapcanmakestringsequal;

/**
 * 1790. Check if One String Swap Can Make Strings Equal
 *
 * https://leetcode-cn.com/problems/check-if-one-string-swap-can-make-strings-equal/
 *
 * You are given two strings s1 and s2 of equal length. A string swap is an operation
 * where you choose two indices in a string (not necessarily different)
 * and swap the characters at these indices.
 *
 * Return true if it is possible to make both strings equal by performing
 * at most one string swap on exactly one of the strings.
 *
 * Otherwise, return false.
 */

public class Solution {

    public boolean areAlmostEqual(String s1, String s2) {
        int first = -1, second = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (first == -1) first = i;
                else if (second == -1) second = i;
                else return false;
            }
        }
        if (first == -1 || second == -1) return first == second;
        return s1.charAt(first) == s2.charAt(second) && s1.charAt(second) == s2.charAt(first);
    }

    public static void main(String[] args) {
        assert !new Solution().areAlmostEqual("aa", "ac");

        assert new Solution().areAlmostEqual("bank", "kanb");
        assert !new Solution().areAlmostEqual("attack", "defend");
        assert new Solution().areAlmostEqual("kelb", "kelb");
        assert !new Solution().areAlmostEqual("abcd", "dcba");
    }

}
