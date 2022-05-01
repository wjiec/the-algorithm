package weekly.w291.p0removedigitfromnumbertomaximizeresult;

/**
 * 6047. Remove Digit From Number to Maximize Result
 *
 * https://leetcode-cn.com/contest/weekly-contest-291/problems/remove-digit-from-number-to-maximize-result/
 *
 * You are given a string number representing a positive integer and a character digit.
 *
 * Return the resulting string after removing exactly one occurrence of digit from number such that
 * the value of the resulting string in decimal form is maximized. The test cases are generated
 * such that digit occurs at least once in number.
 */

public class Solution {

    public String removeDigit(String number, char digit) {
        String ans = "";
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                String tmp = number.substring(0, i) + number.substring(i + 1);
                if (ans.length() == 0 || tmp.compareTo(ans) > 0) {
                    ans = tmp;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
