package weekly.w420.C;

import ability.Array;

/**
 * 3326. Minimum Division Operations to Make Array Non Decreasing
 *
 * https://leetcode.cn/contest/weekly-contest-420/problems/minimum-division-operations-to-make-array-non-decreasing/
 *
 * You are given an integer array nums.
 *
 * Any positive divisor of a natural number x that is strictly less than x is called a proper divisor of x.
 *
 * For example, 2 is a proper divisor of 4, while 6 is not a proper divisor of 6.
 *
 * You are allowed to perform an operation any number of times on nums, where in each operation
 * you select any one element from nums and divide it by its greatest proper divisor.
 *
 * Return the minimum number of operations required to make the array non-decreasing.
 *
 * If it is not possible to make the array non-decreasing using any number of operations, return -1.
 */

public class Solution {

    // 首先判断这里面所有的质数是否都是非递减的, 因为质数我们无法操作
    // 其次判断这里面所有的合数是否可以操作为比前一个质数更大或相等
    public int minOperations(int[] nums) {
        int max = 0, n = nums.length;
        for (var v : nums) max = Math.max(max, v);

        // 不要直接找某个值的质因数, 而是通过质因数去推所有可能的数
        int[] minFactor = new int[max + 1];
        for (int p = 2; p <= max; p++) {
            if (minFactor[p] != 0) continue;
            for (int j = p; j <= max; j += p) {
                if (minFactor[j] == 0) minFactor[j] = p;
            }
        }

        int ans = 0;
        // 由于要满足非递减, 所以从后往前推所有可能的值, 最后一个的值不需要改
        for (int i = n - 2; i >= 0; i--) {
            // 如果当前位比后一位大, 不满足非递减
            if (nums[i] > nums[i + 1]) {
                // 那么我们只能选择将当前值减小
                nums[i] = minFactor[nums[i]];
                // 如果减小之后还是比后一位大, 那么就直接返回-1
                if (nums[i] > nums[i + 1]) return -1;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5, 40, 10}) == -1;
        assert new Solution().minOperations(new int[]{31, 13}) == -1;
        assert new Solution().minOperations(new int[]{20, 10}) == 1;
        assert new Solution().minOperations(Array.make(new int[]{982081,991}, 50000)) == 50000;
        assert new Solution().minOperations(new int[]{982081,991,982081,991,982081}) == 2;
        assert new Solution().minOperations(new int[]{8, 6, 8}) == 1;
        assert new Solution().minOperations(new int[]{75, 117}) == 0;

        assert new Solution().minOperations(new int[]{7,7,6}) == -1;
        assert new Solution().minOperations(new int[]{25,7}) == 1;
        assert new Solution().minOperations(new int[]{1,1,1,1}) == 0;
    }

}
