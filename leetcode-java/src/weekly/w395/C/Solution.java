package weekly.w395.C;

/**
 * 3133. Minimum Array End
 *
 * https://leetcode.cn/contest/weekly-contest-395/problems/minimum-array-end/
 *
 * You are given two integers n and x. You have to construct an array of positive integers
 * nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and
 * the result of the bitwise AND operation between all elements of nums is x.
 *
 * Return the minimum possible value of nums[n - 1].
 */

public class Solution {

    public long minEnd(int n, int x) {
        long[] bits = new long[60];
        for (int i = 0, j = 0; i < 60; i++) {
            if ((x & (1L << i)) == 0) {
                bits[j++] = 1L << i;
            }
        }

        long ans = x; n--;
        for (int i = 0; i < 30; i++) {
            if ((n & (1 << i)) != 0) {
                ans += bits[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minEnd(3, 1) == 5;
        assert new Solution().minEnd(3, 4) == 6;
        assert new Solution().minEnd(2, 7) == 15;
    }

}
