package problem.p1544makethestringgreat;

/**
 * 1544. Make The String Great
 *
 * https://leetcode-cn.com/problems/make-the-string-great/
 *
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 *
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them.
 *
 * You can keep doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 */

public class Solution {

    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (sb.length() != 0 && Math.abs(sb.charAt(sb.length() - 1) - c) == 32) sb.deleteCharAt(sb.length() - 1);
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().makeGood("leEeetcode").equals("leetcode");
        assert new Solution().makeGood("abBAcC").equals("");
    }

}
