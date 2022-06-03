package problem.p945minimumincrementtomakearrayunique;

import java.util.Arrays;

/**
 * 945. Minimum Increment to Make Array Unique
 *
 * https://leetcode.cn/problems/minimum-increment-to-make-array-unique/
 *
 * You are given an integer array nums. In one move, you can pick an index i
 * where 0 <= i < nums.length and increment nums[i] by 1.
 *
 * Return the minimum number of moves to make every value in nums unique.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */

public class Solution {

    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                ans += (nums[i - 1] + 1) - nums[i];
                nums[i] = nums[i - 1] + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minIncrementForUnique(new int[]{1,2,2}) == 1;
        assert new Solution().minIncrementForUnique(new int[]{3,2,1,2,1,7}) == 6;
    }

}
