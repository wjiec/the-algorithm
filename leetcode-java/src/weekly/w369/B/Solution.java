package weekly.w369.B;

/**
 * 2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
 *
 * https://leetcode.cn/contest/weekly-contest-369/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/
 *
 * You are given two arrays nums1 and nums2 consisting of positive integers.
 *
 * You have to replace all the 0's in both arrays with strictly positive
 * integers such that the sum of elements of both arrays becomes equal.
 *
 * Return the minimum equal sum you can obtain, or -1 if it is impossible.
 */

public class Solution {

    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0, cnt1 = 0, cnt2 = 0;
        for (var v : nums1) {
            sum1 += v;
            if (v == 0) cnt1++;
        }
        for (var v : nums2) {
            sum2 += v;
            if (v == 0) cnt2++;
        }

        if (cnt1 == 0 && ((sum1 < sum2) || (sum2 + cnt2 > sum1))) return -1;
        if (cnt2 == 0 && ((sum2 < sum1) || (sum1 + cnt1 > sum2))) return -1;
        return Math.max(sum1 + cnt1, sum2 + cnt2);
    }

    public static void main(String[] args) {
    }

}
