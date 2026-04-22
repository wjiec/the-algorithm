package weekly.w484.B;

/**
 * Q2. Number of Centered Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-484/problems/number-of-centered-subarrays/
 *
 * You are given an integer array nums.
 *
 * A subarray of nums is called centered if the sum of its elements is equal to at
 * least one element within that same subarray.
 *
 * Return the number of centered subarrays of nums.
 */

public class Solution {

    public int centeredSubarrays(int[] nums) {
        // 对于一个子数组 [l, r), 他的和是 nums[l] + nums[l + 1] + ... + nums[r - 1] == nums[k]
        //  - 也就是 nums[l] + nums[l + 1] + ... + nums[r - 1] - nums[k] = 0
        //
        // 枚举 nums[k]?
        //  - 此时需要找到所有 sum[r] - sum[l] == nums[k] 的子数组数量
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int ans = 0;
        for (int l = 0; l < nums.length; l++) {
            for (int r = l; r < nums.length; r++) {
                int ss = sum[r + 1] - sum[l];
                for (int k = l; k <= r; k++) {
                    if (nums[k] == ss) {
                        ans++; break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //  0   0   4
        //  0-0 0-4
        //  0-0-0
        assert new Solution().centeredSubarrays(new int[]{0, 0, 4}) == 6;
    }

}
