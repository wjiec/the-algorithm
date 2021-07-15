package daily.d210715p1846maximumelementafterdecreasingandrearranging;

import java.util.Arrays;

/**
 * 1846. Maximum Element After Decreasing and Rearranging
 *
 * https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/
 *
 * You are given an array of positive integers arr. Perform some operations (possibly none)
 * on arr so that it satisfies these conditions:
 *
 * The value of the first element in arr must be 1.
 * The absolute difference between any 2 adjacent elements must be less than or equal to
 * 1. In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed).
 * abs(x) is the absolute value of x.
 * There are 2 types of operations that you can perform any number of times:
 *
 * Decrease the value of any element of arr to a smaller positive integer.
 * Rearrange the elements of arr to be in any order.
 * Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
 */

public class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);

        arr[0] = 1;
        for (int i = 1, l = arr.length; i < l; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{2,2,1,2,1}) == 2;
        assert new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{100,1,1000}) == 3;
        assert new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{1,2,3,4,5}) == 5;
    }

}
