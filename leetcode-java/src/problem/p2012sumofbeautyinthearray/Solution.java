package problem.p2012sumofbeautyinthearray;

/**
 * 2012. Sum of Beauty in the Array
 *
 * https://leetcode.cn/problems/sum-of-beauty-in-the-array/
 *
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2)
 * the beauty of nums[i] equals:
 *
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 *
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 */

public class Solution {

    public int sumOfBeauties(int[] nums) {
        int n = nums.length;

        int[] min = new int[n]; min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(nums[i], min[i + 1]);
        }

        int ans = 0;
        int[] max = new int[n]; max[0] = nums[0];
        for (int i = 1; i <= n - 2; i++) {
            max[i] = Math.max(nums[i], max[i - 1]);

            if (nums[i] > max[i - 1] && nums[i] < min[i + 1]) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfBeauties(new int[]{1,2,3}) == 2;
        assert new Solution().sumOfBeauties(new int[]{2,4,6,4}) == 1;
        assert new Solution().sumOfBeauties(new int[]{3,2,1}) == 0;
    }

}
