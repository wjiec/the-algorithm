package weekly.bw55.p0p5780removeoneelementtomakethearraystrictlyincreasing;

/**
 * 5780. Remove One Element to Make the Array Strictly Increasing
 *
 * https://leetcode-cn.com/contest/biweekly-contest-55/problems/remove-one-element-to-make-the-array-strictly-increasing/
 *
 * Given a 0-indexed integer array nums, return true if it can be made strictly increasing
 * after removing exactly one element, or false otherwise.
 *
 * If the array is already strictly increasing, return true.
 *
 * The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).
 */

public class Solution {

    public boolean canBeIncreasing(int[] nums) {
        for (int i = 1, l = nums.length; i < l; i++) {
            if (nums[i] <= nums[i - 1]) {
                return isIncreasing(nums, i) || isIncreasing(nums, i - 1);
            }
        }
        return true;
    }

    private boolean isIncreasing(int[] nums, int skip) {
        for (int i = 1, l = nums.length; i < l; i++) {
            if (i == skip) continue;

            int prev = i - 1;
            if (prev == skip) prev -= 1;
            if (prev >= 0 && nums[i] <= nums[prev]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canBeIncreasing(new int[]{1,2,10,5,7});
        assert !new Solution().canBeIncreasing(new int[]{2,3,1,2});
        assert !new Solution().canBeIncreasing(new int[]{1,1,1});
        assert new Solution().canBeIncreasing(new int[]{1,2,3});
        assert new Solution().canBeIncreasing(new int[]{9,1,2,3});
        assert new Solution().canBeIncreasing(new int[]{105,924,32,968});
    }

}
