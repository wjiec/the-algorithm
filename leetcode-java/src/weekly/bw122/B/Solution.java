package weekly.bw122.B;

import java.util.Arrays;

/**
 * 3011. Find if Array Can Be Sorted
 *
 * https://leetcode.cn/contest/biweekly-contest-122/problems/find-if-array-can-be-sorted/
 *
 * You are given a 0-indexed array of positive integers nums.
 *
 * In one operation, you can swap any two adjacent elements if they have the same number of set bits.
 * You are allowed to do this operation any number of times (including zero).
 *
 * Return true if you can sort the array, else return false.
 */

public class Solution {

    public boolean canSortArray(int[] nums) {
        int[] bc = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            bc[i] = Integer.bitCount(nums[i]);
        }

        for (int p = 0, i = 1; i <= bc.length; i++) {
            if (i == bc.length || bc[i] != bc[p]) {
                sort(nums, p, i);
                p = i;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }

        return true;
    }

    private void sort(int[] nums, int l, int r) {
        if (l + 1 == r) return;

        int[] sorted = new int[r - l];
        System.arraycopy(nums, l, sorted, 0, sorted.length);

        Arrays.sort(sorted);
        System.arraycopy(sorted, 0, nums, l, sorted.length);
    }

    public static void main(String[] args) {
    }

}
