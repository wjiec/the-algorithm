package problem.p1764formarraybyconcatenatingsubarraysofanotherarray;

/**
 * 1764. Form Array by Concatenating Subarrays of Another Array
 *
 * https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array/
 *
 * You are given a 2D integer array groups of length n. You are also given an integer array nums.
 *
 * You are asked if you can choose n disjoint subarrays from the array nums such that the ith
 * subarray is equal to groups[i] (0-indexed), and if i > 0, the (i-1)th subarray appears before
 * the ith subarray in nums (i.e. the subarrays must be in the same order as groups).
 *
 * Return true if you can do this task, and false otherwise.
 *
 * Note that the subarrays are disjoint if and only if there is no index k such that nums[k]
 * belongs to more than one subarray. A subarray is a contiguous sequence of elements
 * within an array.
 */

public class Solution {

    public boolean canChoose(int[][] groups, int[] nums) {
        int idx = 0;
        for (int[] group : groups) {
            int next = kmp(nums, group, idx);
            if (next == -1) return false;
            idx = next + group.length;
        }
        return true;
    }

    private int kmp(int[] array, int[] target, int start) {
        int[] next = lps(target);
        for (int i = start, j = 0; i < array.length; ) {
            if (array[i] == target[j]) { i++; j++; }
            else {
                if (j != 0) j = next[j - 1];
                else i++;
            }

            if (j == target.length) return i - j;
        }
        return -1;
    }

    private int[] lps(int[] array) {
        int[] next = new int[array.length];
        for (int i = 1, j = 0; i < array.length; ) {
            if (array[i] == array[j]) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = j;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        assert new Solution().canChoose(new int[][]{{1,-1,-1},{3,-2,0}}, new int[]{1,-1,0,1,-1,-1,3,-2,0});
        assert !new Solution().canChoose(new int[][]{{10,-2},{1,2,3,4}}, new int[]{1,2,3,4,10,-2});
        assert !new Solution().canChoose(new int[][]{{1,2,3},{3,4}}, new int[]{7,7,1,2,3,4,7,7});
    }

}
