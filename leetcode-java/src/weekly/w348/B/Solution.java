package weekly.w348.B;

/**
 * 2717. Semi-Ordered Permutation
 *
 * https://leetcode.cn/contest/weekly-contest-348/problems/semi-ordered-permutation/
 *
 * You are given a 0-indexed permutation of n integers nums.
 *
 * A permutation is called semi-ordered if the first number equals 1 and the last number equals n.
 * You can perform the below operation as many times as you want until you make
 * nums a semi-ordered permutation:
 *
 * Pick two adjacent elements in nums, then swap them.
 * Return the minimum number of operations to make nums a semi-ordered permutation.
 *
 * A permutation is a sequence of integers from 1 to n of length n containing each number exactly once.
 */

public class Solution {

    public int semiOrderedPermutation(int[] nums) {
        int mi = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) mi = i;
        }

        int ans = 0;
        while (mi != 0) {
            ans++;
            swap(nums, mi, mi - 1);
            mi--;
        }

        int mx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n) mx = i;
        }

        while (mx != n - 1) {
            ans++;
            swap(nums, mx, mx + 1);
            mx++;
        }
        return ans;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
    }

}
