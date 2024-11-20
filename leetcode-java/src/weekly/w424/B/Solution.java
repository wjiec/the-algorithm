package weekly.w424.B;

/**
 * 3355. Zero Array Transformation I
 *
 * https://leetcode.cn/contest/weekly-contest-424/problems/zero-array-transformation-i/
 *
 * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
 *
 * For each queries[i]:
 *
 * Select a subset of indices within the range [li, ri] in nums.
 * Decrement the values at the selected indices by 1.
 * A Zero Array is an array where all elements are equal to 0.
 *
 * Return true if it is possible to transform nums into a Zero Array after processing
 * all the queries sequentially, otherwise return false.
 *
 * A subset of an array is a selection of elements (possibly none) of the array.
 */

public class Solution {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 在 queries[i][0] 到 queries[i][1] 中选择任意子集减少1
        int[] diff = new int[nums.length + 1];
        for (var query : queries) {
            diff[query[0]]++;
            diff[query[1] + 1]--;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) diff[i] += diff[i - 1];
            if (diff[i] < nums[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
    }

}
