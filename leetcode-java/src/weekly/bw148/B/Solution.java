package weekly.bw148.B;

import java.util.Arrays;

/**
 * 3424. Minimum Cost to Make Arrays Identical
 *
 * https://leetcode.cn/contest/biweekly-contest-148/problems/minimum-cost-to-make-arrays-identical/
 *
 * You are given two integer arrays arr and brr of length n, and an integer k. You can perform the
 * following operations on arr any number of times:
 *
 * Split arr into any number of contiguous subarrays and rearrange these subarrays in any order.
 * This operation has a fixed cost of k.
 *
 * Choose any element in arr and add or subtract a positive integer x to it. The cost of this operation is x.
 *
 * Return the minimum total cost to make arr equal to brr.
 */

public class Solution {

    public long minCost(int[] arr, int[] brr, long k) {
        if (Arrays.equals(arr, brr)) return 0;

        // 尝试只通过操作二是否能完成
        long op2 = 0;
        for (int i = 0; i < arr.length; i++) {
            op2 += Math.abs(arr[i] - brr[i]);
        }

        // 尝试只通过操作一是否能完成
        Arrays.sort(arr);
        Arrays.sort(brr);
        if (Arrays.equals(arr, brr)) return Math.min(k, op2);

        // 否则必须通过操作一加操作二一起完成
        long otherwise = k;
        for (int i = 0; i < arr.length; i++) {
            otherwise += Math.abs(arr[i] - brr[i]);
        }

        return Math.min(op2, otherwise);
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[]{9}, new int[]{-9}, 29) == 18;
    }

}
