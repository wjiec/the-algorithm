package weekly.w246.p0p5788largestoddnumberinstring;

/**
 * 5788. Largest Odd Number in String
 *
 * You are given a string num, representing a large integer.
 * Return the largest-valued odd integer (as a string) that is a non-empty substring of num,
 * or an empty string "" if no odd integer exists.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if (isOdd(num.charAt(i))) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    private boolean isOdd(char c) {
        return c == '1' || c == '3' || c == '5' || c == '7' || c == '9';
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestOddNumber("52"));
        System.out.println(new Solution().largestOddNumber("4206"));
        System.out.println(new Solution().largestOddNumber("35427"));
    }

}
