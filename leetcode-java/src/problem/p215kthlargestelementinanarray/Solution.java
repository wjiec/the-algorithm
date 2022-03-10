package problem.p215kthlargestelementinanarray;

import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 215. Kth Largest Element in an Array
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */

public class Solution {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int l, int r, int index) {
        int mid = randomPartition(nums, l, r);
        if (mid == index) return nums[index];
        return mid < index ? quickSelect(nums, mid + 1, r, index) : quickSelect(nums, l, mid - 1, index);
    }

    private int randomPartition(int[] nums, int l, int r) {
        swap(nums, ThreadLocalRandom.current().nextInt(r - l + 1) + l, r);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int mid = nums[r], idx = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= mid) {
                swap(nums, idx++, j);
            }
        }
        swap(nums, idx, r);
        return idx;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    public static void main(String[] args) {
        assert new Solution().findKthLargest(new int[]{3,2,1,5,6,4}, 2) == 5;
        assert new Solution().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4) == 4;
    }

}
