package problem.p896monotonicarray;

/**
 * 896. Monotonic Array
 *
 * https://leetcode-cn.com/problems/monotonic-array/
 *
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
 * An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
 *
 * Return true if and only if the given array nums is monotonic.
 */

public class Solution {

    public boolean isMonotonic(int[] nums) {
        boolean increasing = false, decreasing = false;
        for (int i = 1, l = nums.length; i < l; i++) {
            increasing = increasing || nums[i] > nums[i - 1];
            decreasing = decreasing || nums[i] < nums[i - 1];
        }
        return (increasing != decreasing) || !increasing;
    }

    public static void main(String[] args) {
        assert !new Solution().isMonotonic(new int[]{2,2,2,1,4,5});
        assert !new Solution().isMonotonic(new int[]{11,11,9,4,3,3,3,1,-1,-1,3,3,3,5,5,5});
        assert new Solution().isMonotonic(new int[]{1,2,2,3});
        assert new Solution().isMonotonic(new int[]{6,5,4,4});
        assert !new Solution().isMonotonic(new int[]{1,3,2});
        assert new Solution().isMonotonic(new int[]{1,2,4,5});
        assert new Solution().isMonotonic(new int[]{1,1,1});
    }

}
