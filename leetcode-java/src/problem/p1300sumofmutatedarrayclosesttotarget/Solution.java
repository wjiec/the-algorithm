package problem.p1300sumofmutatedarrayclosesttotarget;

import java.util.Arrays;

/**
 * 1300. Sum of Mutated Array Closest to Target
 *
 * https://leetcode.cn/problems/sum-of-mutated-array-closest-to-target/
 *
 * Given an integer array arr and a target value target, return the integer value such that
 * when we change all the integers larger than value in the given array to be equal to value, the
 * sum of the array gets as close as possible (in absolute difference) to target.
 *
 * In case of a tie, return the minimum such integer.
 *
 * Notice that the answer is not neccesarilly a number from arr.
 */

public class Solution {

    /**
     *         for (int i = 1; i <= r; ++i) {
     *             int index = Arrays.binarySearch(arr, i);
     *             if (index < 0) {
     *                 index = -index - 1;
     *             }
     *             int cur = prefix[index] + (n - index) * i;
     *             if (Math.abs(cur - target) < diff) {
     *                 ans = i;
     *                 diff = Math.abs(cur - target);
     *             }
     *         }
     *         return ans;
     */
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);

        int[] sum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++)
            sum[i] = sum[i - 1] + arr[i - 1];

        int ans = 0, diff = target, n = arr.length;
        for (int l = 1, r = arr[n - 1]; l <= r; l++) {
            int x = Arrays.binarySearch(arr, l);
            if (x < 0) x = -x - 1;
            int curr = sum[x] + (n - x) * l;
            if (Math.abs(curr - target) < diff) {
                ans = l; diff = Math.abs(curr - target);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findBestValue(new int[]{4,9,3}, 10) == 3;
        assert new Solution().findBestValue(new int[]{2,3,5}, 10) == 5;
        assert new Solution().findBestValue(new int[]{60864,25176,27249,21296,20204}, 56803) == 11361;
    }

}
