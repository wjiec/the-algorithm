package problem.p709tolowercase;

/**
 * 709. To Lower Case
 *
 * https://leetcode-cn.com/problems/to-lower-case/
 *
 * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
 */

public class Solution {

    public String toLowerCase(String s) {
        char[] dst = new char[s.length()], src = s.toCharArray();
        for (int i = 0; i < src.length; i++) {
            if (src[i] >= 'A' && src[i] <= 'Z') {
                dst[i] = (char) (src[i] + 32);
            } else {
                dst[i] = src[i];
            }
        }
        return new String(dst);
    }

    public static void main(String[] args) {
        assert new Solution().toLowerCase("Hello").equals("hello");
        assert new Solution().toLowerCase("here").equals("here");
        assert new Solution().toLowerCase("LOVELY").equals("lovely");
        assert new Solution().toLowerCase("al&phaBET").equals("al&phabet");
    }

}
