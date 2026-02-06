package weekly.bw169.B;

/**
 * Q2. Count Subarrays With Majority Element I
 *
 * https://leetcode.cn/contest/biweekly-contest-169/problems/count-subarrays-with-majority-element-i/
 *
 * You are given an integer array nums and an integer target.
 *
 * Return the number of subarrays of nums in which target is the majority element.
 *
 * The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
 */

public class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == target ? 1 : -1);
        }

        int ans = 0;
        for (int r = 0; r < nums.length; r++) {
            // 以 r 为右边界, 需要找到所有 sum[r + 1] - sum[l] > 0 的位置的数量
            //  - 也就是找到所有 sum[l] < sum[r + 1] 的位置数
            for (int l = r; l >= 0; l--) {
                ans += sum[r + 1] - sum[l] > 0 ? 1 : 0;
            }
        }
        return ans;
    }

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        assert new Solution().countMajoritySubarrays(new int[]{1,2,2,3}, 2) == 5;
        assert new Solution().countMajoritySubarrays(new int[]{1,1,1,1}, 1) == 10;
        assert new Solution().countMajoritySubarrays(new int[]{1,2,3}, 4) == 0;
    }

}
