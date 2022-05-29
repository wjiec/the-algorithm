package problem.p912sortanarray;

import common.Checker;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 912. Sort an Array
 *
 * https://leetcode.cn/problems/sort-an-array/
 *
 * Given an array of integers nums, sort the array in ascending order.
 */

public class Solution {

    public int[] sortArray(int[] nums) {
        qsort(nums, 0, nums.length - 1);
        return nums;
    }

    private void qsort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = randomPartition(nums, l, r);

            qsort(nums, l, mid - 1);
            qsort(nums, mid + 1, r);
        }
    }

    private int randomPartition(int[] nums, int l, int r) {
        int pivot = l + ThreadLocalRandom.current().nextInt(r - l + 1);
        swap(nums, pivot, r);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r], idx = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {
                swap(nums, j, idx++);
            }
        }
        swap(nums, idx, r);
        return idx;
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) return;

        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortArray(new int[]{5,2,3,1}), new int[]{1,2,3,5});
        assert Checker.check(new Solution().sortArray(new int[]{5,1,1,2,0,0}), new int[]{0,0,1,1,2,5});
    }

}
