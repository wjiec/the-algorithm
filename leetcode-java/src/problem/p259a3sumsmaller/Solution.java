package problem.p259a3sumsmaller;

import java.util.Arrays;

/**
 * 259. 3Sum Smaller
 *
 * https://leetcode-cn.com/problems/3sum-smaller/
 *
 * Given an array of n integers nums and an integer target,
 * find the number of index triplets i, j, k with 0 <= i < j < k < n that
 * satisfy the condition nums[i] + nums[j] + nums[k] < target.
 */

public class Solution {

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);
        int ans = 0, n = nums.length - 2;
        for (int i = 0; i < n; i++) {
            ans += count(nums, i + 1, target - nums[i]);
        }
        return ans;
    }

    private int count(int[] nums, int l, int target) {
        int cnt = 0;
        for (int r = nums.length - 1; l < r; ) {
            if (nums[l] + nums[r] < target) {
                cnt += r - l;
                l++;
            } else r--;
        }
        return cnt;
    }

    public static void main(String[] args) {
        assert new Solution().threeSumSmaller(new int[]{-2,0,1,3}, 2) == 2;
        assert new Solution().threeSumSmaller(new int[]{}, 0) == 0;
        assert new Solution().threeSumSmaller(null, 0) == 0;
        assert new Solution().threeSumSmaller(new int[]{0}, 0) == 0;
    }

}
