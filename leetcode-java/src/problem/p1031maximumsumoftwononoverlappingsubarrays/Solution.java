package problem.p1031maximumsumoftwononoverlappingsubarrays;

/**
 * 1031. Maximum Sum of Two Non-Overlapping Subarrays
 *
 * https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/
 *
 * Given an integer array nums and two integers firstLen and secondLen, return the maximum
 * sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
 *
 * The array with length firstLen could occur before or after the array
 * with length secondLen, but they have to be non-overlapping.
 *
 * A subarray is a contiguous part of an array.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int lf = 0, rs = 0;
        for (int i = firstLen; i + secondLen - 1 < nums.length; i++) {
            lf = Math.max(lf, sum[i] - sum[i - firstLen]);
            rs = Math.max(rs, lf + sum[i + secondLen] - sum[i]);
        }

        int ls = 0, rf = 0;
        for (int i = secondLen; i + firstLen - 1 < nums.length; i++) {
            ls = Math.max(ls, sum[i] - sum[i - secondLen]);
            rf = Math.max(rf, ls + sum[i + firstLen] - sum[i]);
        }

        return Math.max(rs, rf);
    }

    public static void main(String[] args) {
        assert new Solution().maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2) == 20;
        assert new Solution().maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2) == 29;
        assert new Solution().maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4, 3) == 31;
    }

}
