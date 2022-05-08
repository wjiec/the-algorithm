package weekly.w292.p0largest3samedigitnumberinstring;

/**
 * 6056. Largest 3-Same-Digit Number in String
 *
 * https://leetcode-cn.com/contest/weekly-contest-292/problems/largest-3-same-digit-number-in-string/
 *
 * You are given a string num representing a large integer. An integer is good if it meets the following conditions:
 *
 * It is a substring of num with length 3.
 * It consists of only one unique digit.
 * Return the maximum good integer as a string or an empty string "" if no such integer exists.
 *
 * Note:
 *
 * A substring is a contiguous sequence of characters within a string.
 * There may be leading zeroes in num or a good integer.
 */

public class Solution {

    public String largestGoodInteger(String num) {
        String ans = "", curr = "";
        for (int i = 1; i <= num.length() - 2; i++) {
            if (num.charAt(i) == num.charAt(i - 1) && num.charAt(i) == num.charAt(i + 1)) {
                curr = num.substring(i - 1, i + 2);
                if (ans.length() == 0 || curr.charAt(0) > ans.charAt(0)) ans = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestGoodInteger("222").equals("222");
    }

}
