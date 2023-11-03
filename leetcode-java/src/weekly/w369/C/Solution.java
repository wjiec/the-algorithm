package weekly.w369.C;

/**
 * 2919. Minimum Increment Operations to Make Array Beautiful
 *
 * https://leetcode.cn/contest/weekly-contest-369/problems/minimum-increment-operations-to-make-array-beautiful/
 *
 * You are given a 0-indexed integer array nums having length n, and an integer k.
 *
 * You can perform the following increment operation any number of times (including zero):
 *
 * Choose an index i in the range [0, n - 1], and increase nums[i] by 1.
 * An array is considered beautiful if, for any subarray with a size of 3 or
 * more, its maximum element is greater than or equal to k.
 *
 * Return an integer denoting the minimum number of increment operations needed to make nums beautiful.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long minIncrementOperations(int[] nums, int k) {
        long f0 = 0, f1 = 0, f2 = 0;
        for (var v : nums) {
            long fx = f0 + Math.max(0, k - v); // 自己作为满足要求的值
            f0 = Math.min(fx, f1);
            f1 = Math.min(fx, f2);
            f2 = fx;
        }
        return f0;
    }

    public static void main(String[] args) {
        assert new Solution().minIncrementOperations(new int[]{2,3,0,0,2}, 4) == 3;
        assert new Solution().minIncrementOperations(new int[]{0,1,3,3}, 5) == 2;
        assert new Solution().minIncrementOperations(new int[]{1,1,2}, 1) == 0;
    }

}
