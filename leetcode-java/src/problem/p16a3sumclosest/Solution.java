package problem.p16a3sumclosest;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 *
 * https://leetcode-cn.com/problems/3sum-closest/
 *
 * Given an integer array nums of length n and an integer target,
 * find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 */

public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int ans = 0, min = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0, e = n - 2; i < e; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) return sum;

                if (Math.abs(target - sum) < min) {
                    ans = sum;
                    min = Math.abs(target - sum);
                }

                if (sum > target) r--;
                else l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().threeSumClosest(new int[]{-1,2,1,-4}, 1) == 2;
        assert new Solution().threeSumClosest(new int[]{0,0,0}, 1) == 0;
    }

}
