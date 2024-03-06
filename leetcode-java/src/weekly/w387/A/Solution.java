package weekly.w387.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 3069. Distribute Elements Into Two Arrays I
 *
 * https://leetcode.cn/contest/weekly-contest-387/problems/distribute-elements-into-two-arrays-i/
 *
 * You are given a 1-indexed array of distinct integers nums of length n.
 *
 * You need to distribute all the elements of nums between two arrays arr1 and arr2 using n operations.
 * In the first operation, append nums[1] to arr1. In the second operation, append nums[2] to arr2.
 * Afterwards, in the ith operation:
 *
 * If the last element of arr1 is greater than the last element of arr2, append nums[i] to arr1.
 * Otherwise, append nums[i] to arr2.
 *
 * The array result is formed by concatenating the arrays arr1 and arr2.
 *
 * For example, if arr1 == [1,2,3] and arr2 == [4,5,6], then result = [1,2,3,4,5,6].
 *
 * Return the array result.
 */

public class Solution {

    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]); arr2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) arr1.add(nums[i]);
            else arr2.add(nums[i]);
        }

        int idx = 0;
        for (var v : arr1) nums[idx++] = v;
        for (var v : arr2) nums[idx++] = v;
        return nums;
    }

    public static void main(String[] args) {
    }

}
