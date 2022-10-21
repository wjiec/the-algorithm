package problem.p2295replaceelementsinanarray;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 2295. Replace Elements in an Array
 *
 * https://leetcode.cn/problems/replace-elements-in-an-array/
 *
 * You are given a 0-indexed array nums that consists of n distinct positive integers.
 * Apply m operations to this array, where in the ith operation you replace the number
 * operations[i][0] with operations[i][1].
 *
 * It is guaranteed that in the ith operation:
 *
 * operations[i][0] exists in nums.
 * operations[i][1] does not exist in nums.
 * Return the array obtained after applying all the operations.
 */

public class Solution {

    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }

        for (var operation : operations) {
            int a = operation[0], b = operation[1];

            int idx = index.remove(a);
            nums[idx] = b;
            index.put(b, idx);
        }

        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().arrayChange(new int[]{1,2,4,6}, new int[][]{{1,3},{4,7},{6,1}}), new int[]{3,2,7,1});
        assert Checker.check(new Solution().arrayChange(new int[]{1,2}, new int[][]{{1,3},{2,1},{3,2}}), new int[]{2,1});
    }

}
