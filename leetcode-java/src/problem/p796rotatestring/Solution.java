package problem.p796rotatestring;

/**
 * 796. Rotate String
 *
 * https://leetcode-cn.com/problems/rotate-string/
 *
 * We are given two strings, s and goal.
 *
 * A shift on s consists of taking string s and moving the leftmost character to the rightmost position.
 * For example, if s = 'abcde', then it will be 'bcdea' after one shift on s.
 * Return true if and only if s can become goal after some number of shifts on s.
 */

public class Solution {

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        return (s + s).contains(goal);
    }

    public static void main(String[] args) {
        assert new Solution().rotateString("abcde", "cdeab");
        assert !new Solution().rotateString("abcde", "abced");
    }

}
