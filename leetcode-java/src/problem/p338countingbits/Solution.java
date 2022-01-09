package problem.p338countingbits;

import common.Checker;

/**
 * 338. Counting Bits
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 */

public class Solution {

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1, hi = 0; i <= n; i++) {
            if ((i & (i - 1)) == 0) hi = i;
            ans[i] = ans[i - hi] + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countBits(2), new int[]{0,1,1});
        assert Checker.check(new Solution().countBits(5), new int[]{0,1,1,2,1,2});
    }

}
