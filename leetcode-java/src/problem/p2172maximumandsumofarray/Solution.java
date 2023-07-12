package problem.p2172maximumandsumofarray;

import java.util.Arrays;

/**
 * 2172. Maximum AND Sum of Array
 *
 * https://leetcode.cn/problems/maximum-and-sum-of-array/description/
 *
 * You are given an integer array nums of length n and an integer numSlots such that 2 * numSlots >= n.
 * There are numSlots slots numbered from 1 to numSlots.
 *
 * You have to place all n integers into the slots such that each slot contains at most two numbers.
 * The AND sum of a given placement is the sum of the bitwise AND of every number with its respective slot number.
 *
 * For example, the AND sum of placing the numbers [1, 3] into slot 1 and [4, 6] into
 * slot 2 is equal to (1 AND 1) + (3 AND 1) + (4 AND 2) + (6 AND 2) = 1 + 1 + 0 + 2 = 4.
 *
 * Return the maximum possible AND sum of nums given numSlots slots.
 */

public class Solution {

    public int maximumANDSum(int[] nums, int numSlots) {
        slots = numSlots;
        memo = new int[nums.length][1 << (numSlots * 2)];
        for (var row : memo) Arrays.fill(row, -1);
        return andSum(nums, 0, 0);
    }

    private int slots = 0;
    private int[][] memo = null;

    private int andSum(int[] nums, int i, int mask) {
        if (i == nums.length) return 0;
        if (memo[i][mask] == -1) {
            int ans = 0;
            for (int j = 0; j < slots; j++) {
                int x = 3 << (2 * j);
                switch (Integer.bitCount(mask & x)) {
                    case 0 -> {
                        ans = Math.max(ans, (nums[i] & (j + 1)) + andSum(nums, i + 1, mask | (1 << (2 * j))));
                    }
                    case 1 -> {
                        ans = Math.max(ans, (nums[i] & (j + 1)) + andSum(nums, i + 1, mask | x));
                    }
                }
            }
            memo[i][mask] = ans;
        }
        return memo[i][mask];
    }

    public static void main(String[] args) {
        assert new Solution().maximumANDSum(new int[]{1,2,3,4,5,6}, 3) == 9;
        assert new Solution().maximumANDSum(new int[]{1,3,10,4,7,1}, 9) == 24;
    }

}
