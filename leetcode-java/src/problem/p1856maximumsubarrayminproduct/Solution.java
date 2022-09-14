package problem.p1856maximumsubarrayminproduct;

import common.Tag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1856. Maximum Subarray Min-Product
 *
 * https://leetcode.cn/problems/maximum-subarray-min-product/
 *
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the min-product should be maximized before performing the modulo operation.
 * Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 *
 * A subarray is a contiguous part of an array.
 */

public class Solution {

    @Tag({"下一个更大的元素", "下一个更小的元素"})
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();

        int[] next = new int[n]; Arrays.fill(next, n);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();
        int[] prev = new int[n]; Arrays.fill(prev, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }

        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = prev[i], r = next[i];
            ans = Math.max(ans, nums[i] * (sum[r] - sum[l + 1]));
        }
        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().maxSumMinProduct(new int[]{1,2,3,2}) == 14;
        assert new Solution().maxSumMinProduct(new int[]{2,3,3,1,2}) == 18;
        assert new Solution().maxSumMinProduct(new int[]{3,1,5,6,4,2}) == 60;
    }

}
