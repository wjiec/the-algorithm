package problem.p1016binarystringwithsubstringsrepresenting1ton;

/**
 * 1016. Binary String With Substrings Representing 1 To N
 *
 * https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/
 *
 * Given a binary string s and a positive integer n, return true if the binary representation of
 * all the integers in the range [1, n] are substrings of s, or false otherwise.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public boolean queryString(String s, int n) {
        if (n >= 2048) return false;
        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().queryString("1", 1);

        assert new Solution().queryString("0110", 3);
        assert !new Solution().queryString("0110", 4);
    }

}
