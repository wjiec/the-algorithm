package weekly.bw96.B;

/**
 * 2541. Minimum Operations to Make Array Equal II
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii/
 *
 * You are given two integer arrays nums1 and nums2 of equal length n and an integer k.
 * You can perform the following operation on nums1:
 *
 * Choose two indexes i and j and increment nums1[i] by k and decrement nums1[j] by k.
 * In other words, nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
 *
 * nums1 is said to be equal to nums2 if for all indices i such that 0 <= i < n, nums1[i] == nums2[i].
 *
 * Return the minimum number of operations required to make nums1 equal to nums2.
 *
 * If it is impossible to make them equal, return -1.
 */

public class Solution {

    public long minOperations(int[] nums1, int[] nums2, int k) {
        long ans = 0, minus = 0;
        for (int i = 0; i < nums1.length; i++) {
            int diff = nums1[i] - nums2[i];
            if (diff != 0) {
                if (k == 0) return -1;
                int div = Math.abs(diff) / k;
                if (Math.abs(diff) % k != 0) return -1;
                if (diff > 0) { minus += div; ans += div; }
                else minus -= div;
            }
        }
        if (minus != 0) return -1;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{4,3,1,4}, new int[]{1,3,7,1}, 3) == 2;
        assert new Solution().minOperations(new int[]{3,8,5,2}, new int[]{2,4,1,6}, 1) == -1;
    }

}
