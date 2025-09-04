package weekly.w461.A;

/**
 * Q1. Trionic Array I
 *
 * https://leetcode.cn/contest/weekly-contest-461/problems/trionic-array-i/
 *
 * You are given an integer array nums of length n.
 *
 * An array is trionic if there exist indices 0 < p < q < n − 1 such that:
 *
 * nums[0...p] is strictly increasing,
 * nums[p...q] is strictly decreasing,
 * nums[q...n − 1] is strictly increasing.
 *
 * Return true if nums is trionic, otherwise return false.
 */

public class Solution {

    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        for (int p = 1; p < n - 2; p++) {
            for (int q = p + 1; q < n - 1; q++) {
                if (increasing(nums, 0, p) && decreasing(nums, p, q) && increasing(nums, q, n - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean increasing(int[] nums, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] <= nums[i - 1]) return false;
        }
        return true;
    }

    private boolean decreasing(int[] nums, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] >= nums[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isTrionic(new int[]{1,3,5,4,2,6});
        assert !new Solution().isTrionic(new int[]{2,1,3});
    }

}
