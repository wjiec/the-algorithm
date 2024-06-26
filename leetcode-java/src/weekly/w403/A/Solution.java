package weekly.w403.A;

import java.util.Arrays;

/**
 * 3194. Minimum Average of Smallest and Largest Elements
 *
 * https://leetcode.cn/contest/weekly-contest-403/problems/minimum-average-of-smallest-and-largest-elements/
 *
 * You have an array of floating point numbers averages which is initially empty.
 * You are given an array nums of n integers where n is even.
 *
 * You repeat the following procedure n / 2 times:
 *
 * Remove the smallest element, minElement, and the largest element maxElement, from nums.
 * Add (minElement + maxElement) / 2 to averages.
 * Return the minimum element in averages.
 */

public class Solution {

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        double ans = Double.MAX_VALUE;
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            ans = Math.min(ans, (double) (nums[l] + nums[r]) / 2.0);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
