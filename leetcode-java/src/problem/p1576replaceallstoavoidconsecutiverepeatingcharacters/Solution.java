package problem.p1576replaceallstoavoidconsecutiverepeatingcharacters;

/**
 * 1576. Replace All ?'s to Avoid Consecutive Repeating Characters
 *
 * https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 *
 * Given a string s containing only lowercase English letters and the '?' character,
 * convert all the '?' characters into lowercase letters such that the final string
 * does not contain any consecutive repeating characters. You cannot modify the non '?' characters.
 *
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 *
 * Return the final string after all the conversions (possibly zero) have been made.
 * If there is more than one solution, return any of them. It can be shown
 * that an answer is always possible with the given constraints.
 */

public class Solution {

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                char left = i != 0 ? chars[i - 1] : '.';
                char right = i != chars.length - 1 ? chars[i + 1] : '.';
                if ('a' != left && 'a' != right) chars[i] = 'a';
                else if ('b' != left && 'b' != right) chars[i] = 'b';
                else chars[i] = 'c';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().modifyString("?zs").equals("azs");
        assert new Solution().modifyString("ubv?w").equals("ubvaw");
        assert new Solution().modifyString("j?qg??b").equals("jaqgacb");
        assert new Solution().modifyString("??yw?ipkj?").equals("abywaipkja");
    }

}
