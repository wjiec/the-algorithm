package problem.p2090kradiussubarrayaverages;

import common.Checker;

import java.util.Arrays;

/**
 * 2090. K Radius Subarray Averages
 *
 * https://leetcode.cn/problems/k-radius-subarray-averages/
 *
 * You are given a 0-indexed array nums of n integers, and an integer k.
 *
 * The k-radius average for a subarray of nums centered at some index i with
 * the radius k is the average of all elements in nums between the indices i - k
 * and i + k (inclusive). If there are less than k elements before or after the
 * index i, then the k-radius average is -1.
 *
 * Build and return an array avgs of length n where avgs[i] is the k-radius average
 * for the subarray centered at index i.
 *
 * The average of x elements is the sum of the x elements divided by x, using integer
 * division. The integer division truncates toward zero, which means losing its fractional
 * part.
 *
 * For example, the average of four elements 2, 3, 1, and 5 is
 * (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75, which truncates to 2.
 */

public class Solution {

    public int[] getAverages(int[] nums, int k) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);

        long sum = 0;
        int n = nums.length, len = 2 * k + 1;
        for (int l = 0, r = 0; r < n; r++) {
            sum += nums[r];
            if (r - l + 1 > len) sum -= nums[l++];
            if (r - l + 1 == len) ans[l + k] = (int) (sum / len);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getAverages(new int[]{7,4,3,9,1,8,5,2,6}, 3),
            new int[]{-1,-1,-1,5,4,4,-1,-1,-1});

        assert Checker.check(new Solution().getAverages(new int[]{100000}, 0),
            new int[]{100000});

        assert Checker.check(new Solution().getAverages(new int[]{8}, 100000),
            new int[]{-1});
    }

}
