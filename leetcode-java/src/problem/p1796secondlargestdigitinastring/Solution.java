package problem.p1796secondlargestdigitinastring;

/**
 * 1796. Second Largest Digit in a String
 *
 * https://leetcode-cn.com/problems/second-largest-digit-in-a-string/
 *
 * Given an alphanumeric string s, return the second largest numerical digit that appears in s,
 * or -1 if it does not exist.
 *
 * An alphanumeric string is a string consisting of lowercase English letters and digits.
 */

public class Solution {

    public int secondHighest(String s) {
        boolean[] map = new boolean[10];
        for (var c : s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                map[c - '0'] = true;
            }
        }

        boolean first = true;
        for (int i = 9; i >= 0; i--) {
            if (map[i]) {
                if (first) first = false;
                else return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().secondHighest("dfa12321afd") == 2;
        assert new Solution().secondHighest("abc1111") == -1;
    }

}
