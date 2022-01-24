package problem.p31nextpermutation;

import java.util.Arrays;

/**
 * 31. Next Permutation
 *
 * https://leetcode-cn.com/problems/next-permutation/
 *
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are considered permutations of arr:
 * [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their
 * lexicographical order, then the next permutation of that array is the permutation that follows it in
 * the sorted container. If such arrangement is not possible, the array must be rearranged as the
 * lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a
 * lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 */

public class Solution {

    public void nextPermutation(int[] nums) {
        int l = nums.length - 2;
        while (l >= 0 && nums[l] >= nums[l + 1]) l--;
        if (l >= 0) {
            int r = nums.length - 1;
            while (r >= 0 && nums[l] >= nums[r]) r--;
            swap(nums, l, r);
        }
        resort(nums, l + 1, nums.length - 1);
    }

    private void resort(int[] nums, int l, int r) {
        for (; l < r; l++, r--) {
            swap(nums, l, r);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] list1 = new int[]{1,2,3};
        new Solution().nextPermutation(list1);
        System.out.println(Arrays.toString(list1));

        int[] list2 = new int[]{3,2,1};
        new Solution().nextPermutation(list2);
        System.out.println(Arrays.toString(list2));

        int[] list3 = new int[]{1,1,5};
        new Solution().nextPermutation(list3);
        System.out.println(Arrays.toString(list3));

        int[] list4 = new int[]{1};
        new Solution().nextPermutation(list4);
        System.out.println(Arrays.toString(list4));

        int[] list5 = new int[]{1,2,3,2,1};
        new Solution().nextPermutation(list5);
        System.out.println(Arrays.toString(list5));

        int[] list6 = new int[]{1,3,2};
        new Solution().nextPermutation(list6);
        System.out.println(Arrays.toString(list6));
    }

}
