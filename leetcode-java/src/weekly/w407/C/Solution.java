package weekly.w407.C;

/**
 * 3228. Maximum Number of Operations to Move Ones to the End
 *
 * https://leetcode.cn/contest/weekly-contest-407/problems/maximum-number-of-operations-to-move-ones-to-the-end/
 *
 * You are given a binary string s.
 *
 * You can perform the following operation on the string any number of times:
 *
 * Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
 * Move the character s[i] to the right until it reaches the end of the string or another '1'.
 * For example, for s = "010010", if we choose i = 1, the resulting string will be s = "000110".
 *
 * Return the maximum number of operations that you can perform.
 */

public class Solution {

    public int maxOperations(String s) {
        int n = s.length();
        int[] ones = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ones[i + 1] = ones[i] + s.charAt(i) - '0';
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i + 1 < n && s.charAt(i + 1) == '0') continue;
                ans += ones[i + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
