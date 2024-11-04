package weekly.w422.A;

/**
 * 3340. Check Balanced String
 *
 * https://leetcode.cn/contest/weekly-contest-422/problems/check-balanced-string/
 *
 * You are given a string num consisting of only digits. A string of digits is called balanced
 * if the sum of the digits at even indices is equal to the sum of digits at odd indices.
 *
 * Return true if num is balanced, otherwise return false.
 */

public class Solution {

    public boolean isBalanced(String num) {
        int ans = 0;
        for (int i = 0; i < num.length(); i++) {
            if (i % 2 == 0) ans += num.charAt(i) - '0';
            else ans -= num.charAt(i) - '0';
        }

        return ans == 0;
    }

    public static void main(String[] args) {
    }

}
