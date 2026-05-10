package weekly.w492.B;

/**
 * Q2. Find the Smallest Balanced Index
 *
 * https://leetcode.cn/contest/weekly-contest-492/problems/find-the-smallest-balanced-index/
 *
 * You are given an integer array nums.
 *
 * An index i is balanced if the sum of elements strictly to the left of i equals
 * the product of elements strictly to the right of i.
 *
 * If there are no elements to the left, the sum is considered as 0. Similarly, if
 * there are no elements to the right, the product is considered as 1.
 *
 * Return an integer denoting the smallest balanced index. If no balanced index exists, return -1.
 */

public class Solution {

    public int smallestBalancedIndex(int[] nums) {
        // 左侧元素的总和等于右侧元素的乘积
        long pre = 0; int n = nums.length;
        for (var v : nums) pre += v;

        long suf = 1; int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            pre -= nums[i];
            if (pre == suf) ans = i;
            if (suf > pre / nums[i]) break;
            suf *= nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().smallestBalancedIndex(new int[]{
            1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,
            1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,
            1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,
            1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,
            1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,359738368,
            1,536870913,262144,131072,
        }) == -1;
    }

}
