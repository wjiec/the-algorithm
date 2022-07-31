package weekly.w304.A;

/**
 * 6132. Make Array Zero by Subtracting Equal Amounts
 *
 * https://leetcode.cn/contest/weekly-contest-304/problems/make-array-zero-by-subtracting-equal-amounts/
 *
 * You are given a non-negative integer array nums. In one operation, you must:
 *
 * Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
 * Subtract x from every positive element in nums.
 * Return the minimum number of operations to make every element in nums equal to 0.
 */

public class Solution {

    public int minimumOperations(int[] nums) {
        int[] map = new int[101];
        for (var n : nums) map[n]++;

        int ans = 0;
        for (int i = 1; i < map.length; i++) {
            if (map[i] != 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
