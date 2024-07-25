package problem.p2122recovertheoriginalarray;

import common.Checker;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 2122. Recover the Original Array
 *
 * https://leetcode.cn/problems/recover-the-original-array/
 *
 * Alice had a 0-indexed array arr consisting of n positive integers. She chose an arbitrary
 * positive integer k and created two new 0-indexed integer arrays lower and higher
 * in the following manner:
 *
 * lower[i] = arr[i] - k, for every index i where 0 <= i < n
 * higher[i] = arr[i] + k, for every index i where 0 <= i < n
 * Unfortunately, Alice lost all three arrays. However, she remembers the integers that were
 * present in the arrays lower and higher, but not the array each integer belonged to.
 *
 * Help Alice and recover the original array.
 *
 * Given an array nums consisting of 2n integers, where exactly n of the integers were present
 * in lower and the remaining in higher, return the original array arr. In case the answer is not
 * unique, return any valid array.
 *
 * Note: The test cases are generated such that there exists at least one valid array arr.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);

        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (var v : nums) m.merge(v, 1, Solution::sum);

        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[0];
            if (diff != 0 && diff % 2 == 0) {
                int[] curr = recoverArray(new TreeMap<>(m), n, diff / 2);
                if (curr != null) return curr;
            }
        }

        return null; // unreached
    }

    private int[] recoverArray(TreeMap<Integer, Integer> m, int n, int k) {
        int[] ans = new int[n / 2];
        for (int i = 0; !m.isEmpty(); i++) {
            int low = m.firstKey(), high = low + 2 * k;
            if (!m.containsKey(high)) return null;

            ans[i] = low + k;
            m.merge(low, -1, Solution::sum);
            m.merge(high, -1, Solution::sum);
        }

        return ans;
    }

    private static Integer sum(Integer a, Integer b) { return (a + b == 0) ? null : (a + b); }

    public static void main(String[] args) {
        assert Checker.check(new Solution().recoverArray(new int[]{1,1,3,3}), new int[]{2,2});
    }

}
