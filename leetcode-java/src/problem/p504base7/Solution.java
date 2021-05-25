package problem.p504base7;

/**
 * 504. Base 7
 *
 * https://leetcode-cn.com/problems/base-7/
 *
 * Given an integer num, return a string of its base 7 representation.
 */

public class Solution {

    public String convertToBase7(int num) {
        if (num == 0) return "0";
        if (num > 0) {
            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                sb.append((char) ('0' + (num % 7)));
                num /= 7;
            }
            return sb.reverse().toString();
        }
        return "-" + convertToBase7(-num);
    }

    public static void main(String[] args) {
        assert new Solution().convertToBase7(100).equals("202");
        assert new Solution().convertToBase7(7).equals("10");
        assert new Solution().convertToBase7(-7).equals("-10");
        assert new Solution().convertToBase7(-70).equals("-100");
    }

}
