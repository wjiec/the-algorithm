package problem.p2164sortevenandoddindicesindependently;

import common.Checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2164. Sort Even and Odd Indices Independently
 *
 * https://leetcode-cn.com/problems/sort-even-and-odd-indices-independently/
 *
 * You are given a 0-indexed integer array nums. Rearrange the values of nums according to the following rules:
 *
 * Sort the values at odd indices of nums in non-increasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [4,3,2,1] after.
 * The values at odd indices 1 and 3 are sorted in non-increasing order.
 *
 * Sort the values at even indices of nums in non-decreasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [2,1,4,3] after.
 * The values at even indices 0 and 2 are sorted in non-decreasing order.
 *
 * Return the array formed after rearranging the values of nums.
 */

public class Solution {

    public int[] sortEvenOdd(int[] nums) {
        List<Integer> even = new ArrayList<>(), odd = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) odd.add(nums[i]);
            else even.add(nums[i]);
        }

        Collections.sort(even);
        odd.sort((a, b) -> b - a);
        for (int i = 0, j = 0; i < nums.length; j++) {
            nums[i++] = even.get(j);
            if (j < odd.size()) nums[i++] = odd.get(j);
        }
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortEvenOdd(new int[]{4,1,2,3}), new int[]{2,3,4,1});
        assert Checker.check(new Solution().sortEvenOdd(new int[]{2,1}), new int[]{2,1});
        assert Checker.check(new Solution().sortEvenOdd(new int[]{4,1,2}), new int[]{2,1,4});
    }

}
