package problem.p1186maximumsubarraysumwithonedeletion;

/**
 * 1186. Maximum Subarray Sum with One Deletion
 *
 * https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/
 *
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with
 * at most one element deletion. In other words, you want to choose a subarray and optionally delete
 * one element from it so that there is still at least one element left and the sum of
 * the remaining elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 */

public class Solution {

    public int maximumSum(int[] arr) {
        int ans = arr[0], a = arr[0], b = 0;
        for (int i = 1; i < arr.length; i++) {
            // 考虑 b 的状态转移
            //  1. 从之前已经删除了一个元素的状态加上当前元素  b + arr[i]
            //  2. 从之前未删除元素的最大和里删除当前元素     a
            b = Math.max(b + arr[i], a); // 删除
            a = Math.max(a + arr[i], arr[i]); // 不删除
            ans = Math.max(ans, Math.max(a, b));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumSum(new int[]{1,-2,0,3}) == 4;
        assert new Solution().maximumSum(new int[]{1,-2,-2,3}) == 3;
        assert new Solution().maximumSum(new int[]{-1,-1,-1,-1}) == -1;
    }

}
