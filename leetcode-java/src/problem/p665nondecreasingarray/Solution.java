package problem.p665nondecreasingarray;

/**
 * 665. Non-decreasing Array
 *
 * Given an array nums with n integers, your task is to check if
 * it could become non-decreasing by modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1]
 * holds for every i (0-based) such that (0 <= i <= n - 2).
 */

public class Solution {

    public boolean checkPossibility(int[] nums) {
        int n = nums.length, chance = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (chance == 0) return false;

                chance--;
                if (i != 0 && nums[i + 1] < nums[i - 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().checkPossibility(new int[]{3,4,2,3});
        assert new Solution().checkPossibility(new int[]{4,2,3});
        assert !new Solution().checkPossibility(new int[]{4,2,1});
    }

}
