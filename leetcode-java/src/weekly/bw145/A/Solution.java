package weekly.bw145.A;

import java.util.TreeMap;

/**
 * 3375. Minimum Operations to Make Array Values Equal to K
 *
 * https://leetcode.cn/contest/biweekly-contest-145/problems/minimum-operations-to-make-array-values-equal-to-k/
 *
 * You are given an integer array nums and an integer k.
 *
 * An integer h is called valid if all values in the array that are strictly greater than h are identical.
 *
 * For example, if nums = [10, 8, 10, 8], a valid integer is h = 9
 * because all nums[i] > 9 are equal to 10, but 5 is not a valid integer.
 *
 * You are allowed to perform the following operation on nums:
 *
 * Select an integer h that is valid for the current values in nums.
 * For each index i where nums[i] > h, set nums[i] to h.
 * Return the minimum number of operations required to make every element in nums equal to k.
 *
 * If it is impossible to make all elements equal to k, return -1.
 */

public class Solution {

    public int minOperations(int[] nums, int k) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (var v : nums) m.merge(v, 1, Integer::sum);
        if (m.lowerKey(k) != null) return -1;

        return m.size() + (m.containsKey(k) ? -1 : 0);
    }

    public static void main(String[] args) {
    }

}
