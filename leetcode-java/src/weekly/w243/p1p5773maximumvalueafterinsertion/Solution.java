package weekly.w243.p1p5773maximumvalueafterinsertion;

/**
 * 5773. Maximum Value after Insertion
 *
 * https://leetcode-cn.com/contest/weekly-contest-243/problems/maximum-value-after-insertion/
 *
 * You are given a very large integer n, represented as a string, and an integer digit x.
 * The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 *
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n.
 * You cannot insert x to the left of the negative sign.
 *
 * For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n after the insertion.
 */

public class Solution {

    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder(n.length() + 1);
        if (n.charAt(0) == '-') {
            for (int i = 0, l = n.length(); i < l; i++) {
                char c = n.charAt(i);
                if (c - '0' > x && x != 0) {
                    sb.append((char) ('0' + x));
                    x = 0;
                }
                sb.append(c);
            }
        } else {
            for (int i = 0, l = n.length(); i < l; i++) {
                char c = n.charAt(i);
                if (c - '0' < x && x != 0) {
                    sb.append((char) ('0' + x));
                    x = 0;
                }
                sb.append(c);
            }
        }

        if (x != 0) {
            sb.append((char) ('0' + x));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().maxValue("99", 9).equals("999");
        assert new Solution().maxValue("-13", 2).equals("-123");
        assert new Solution().maxValue("-111111111", 2).equals("-1111111112");
    }

}
