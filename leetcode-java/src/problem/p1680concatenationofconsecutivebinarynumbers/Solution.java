package problem.p1680concatenationofconsecutivebinarynumbers;

import common.TODO;

/**
 * 1680. Concatenation of Consecutive Binary Numbers
 *
 * https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers/
 *
 * Given an integer n, return the decimal value of the binary string formed by
 * concatenating the binary representations of 1 to n in order, modulo 109 + 7.
 */

public class Solution {

    @TODO
    public int concatenatedBinary(int n) {
        int ans = 0, shift = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) shift++;
            ans = (int) ((((long) ans << shift) + i) % 1_000_000_007);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().concatenatedBinary(1) == 1;
        assert new Solution().concatenatedBinary(3) == 27;
        assert new Solution().concatenatedBinary(12) == 505379714;
        assert new Solution().concatenatedBinary(888) == 236863384;
        assert new Solution().concatenatedBinary(12345) == 836722104;
        assert new Solution().concatenatedBinary(23456) == 665190327;
    }

}
