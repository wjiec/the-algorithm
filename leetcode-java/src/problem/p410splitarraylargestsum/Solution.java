package problem.p410splitarraylargestsum;

/**
 * 410. Split Array Largest Sum
 *
 * https://leetcode.cn/problems/split-array-largest-sum/
 *
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 *
 * Return the minimized largest sum of the split.
 *
 * A subarray is a contiguous part of the array.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int splitArray(int[] nums, int k) {
        int l = 1, r = 0, ans = 0;
        for (var v : nums) r += v;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(nums, k, mid)) {
                ans = mid; r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean check(int[] nums, int k, int max) {
        int sum = 0;
        for (int v : nums) {
            if (v > max) return false;
            if (sum + v > max) {
                sum = 0;
                if (--k < 0) return false;
            }
            sum += v;
        }
        return k > 0;
    }

    public static void main(String[] args) {
        assert new Solution().splitArray(new int[]{1,2,3,4,5}, 1) == 15;

        assert new Solution().splitArray(new int[]{7,2,5,10,8}, 2) == 18;
        assert new Solution().splitArray(new int[]{1,2,3,4,5}, 2) == 9;
        assert new Solution().splitArray(new int[]{1,4,4}, 3) == 4;
    }

}
