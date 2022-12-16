package offer2.p76;

/**
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 *
 * https://leetcode.cn/problems/xx4gT2/
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length, nums.length - k);
    }

    private int quickSelect(int[] array, int l, int r, int idx) {
        if (l < r) {
            int mid = partition(array, l, r);
            if (mid == idx) return array[idx];

            if (mid > idx) return quickSelect(array, l, mid, idx);
            return quickSelect(array, mid + 1, r, idx);
        }
        return array[idx];
    }

    private int partition(int[] array, int l, int r) {
        int mid = l + (r - l) / 2;
        swap(array, mid, r - 1);

        int pivot = array[r - 1], idx = l;
        for (int i = l; i < r; i++) {
            if (array[i] < pivot) {
                swap(array, i, idx++);
            }
        }
        swap(array, idx, r - 1);

        return idx;
    }

    private void swap(int[] array, int a, int b) {
        int stash = array[a];
        array[a] = array[b];
        array[b] = stash;
    }

    public static void main(String[] args) {
        assert new Solution().findKthLargest(new int[]{3,2,1,5,6,4}, 2) == 5;
        assert new Solution().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4) == 4;
    }

}
