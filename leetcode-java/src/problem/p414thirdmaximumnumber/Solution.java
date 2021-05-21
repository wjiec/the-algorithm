package problem.p414thirdmaximumnumber;

import java.util.Arrays;

/**
 * 414. Third Maximum Number
 *
 * Given integer array nums, return the third maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 */

public class Solution {

    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 3) {
            return nums[nums.length - 1];
        }

        int cnt = 2, v = nums[nums.length - 1], idx = nums.length - 1;
        while (cnt > 0 && idx >= 0) {
            if (nums[idx] < v) {
                cnt--;
                v = nums[idx];
            }
            idx--;
        }
        return cnt == 0 ? v : nums[nums.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().thirdMax(new int[]{3,2,1}) == 1;
        assert new Solution().thirdMax(new int[]{1,2}) == 2;
        assert new Solution().thirdMax(new int[]{2, 2, 3, 1}) == 1;
        assert new Solution().thirdMax(new int[]{1,2,2,5,3,5}) == 2;
    }

}
