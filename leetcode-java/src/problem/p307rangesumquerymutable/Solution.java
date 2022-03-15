package problem.p307rangesumquerymutable;

import java.util.Arrays;

/**
 * 307. Range Sum Query - Mutable
 *
 * https://leetcode-cn.com/problems/range-sum-query-mutable/
 *
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between
 * indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 */

public class Solution {

    // @TODO
    private static class NumArray {
        private final int n;
        private final int[] tree;
        public NumArray(int[] nums) {
            n = nums.length;
            tree = new int[n * 2];
            System.arraycopy(nums, 0, tree, n, n);
            for (int i = n - 1; i > 0; --i) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
        }

        public void update(int index, int val) {
            index += n;
            tree[index] = val;
            while (index > 0) {
                int l = index, r = index;
                if (index % 2 == 0) r++;
                else l--;

                tree[index / 2] = tree[l] + tree[r];
                index /= 2;
            }
        }

        public int sumRange(int left, int right) {
            left += n; right += n;
            int ans = 0;
            while (left <= right) {
                if ((left % 2) == 1) ans += tree[left++];
                if ((right % 2) == 0) ans += tree[right--];

                left /= 2; right /= 2;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5}); // 1 4 9
        assert numArray.sumRange(0, 2) == 9;
        numArray.update(1, 2);
        assert numArray.sumRange(0, 2) == 8;
    }

}
