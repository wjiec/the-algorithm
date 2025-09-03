package weekly.bw162.D;

import common.Tag;

/**
 * Q4. Threshold Majority Queries
 *
 * https://leetcode.cn/contest/biweekly-contest-162/problems/threshold-majority-queries/
 *
 * You are given an integer array nums of length n and an array queries, where queries[i] = [li, ri, thresholdi].
 *
 * Return an array of integers ans where ans[i] is equal to the element in the
 * subarray nums[li...ri] that appears at least thresholdi times, selecting the element
 * with the highest frequency (choosing the smallest in case of a tie), or -1 if no such
 * element exists.
 */

public class Solution {

    // query = [l, r, threshold], 需要查询在 nums[l, r] 范围内出现频次 (需要大于 threshold) 最高且值最小的元素
    @Tag({"分块", "莫队"})
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        return null;
    }

    public static void main(String[] args) {
    }

}
