package weekly.bw104.D;

import java.util.Arrays;

/**
 * 6423. Power of Heroes
 *
 * https://leetcode.cn/contest/biweekly-contest-104/problems/power-of-heroes/
 *
 * You are given a 0-indexed integer array nums representing the strength of some heroes.
 * The power of a group of heroes is defined as follows:
 *
 * Let i0, i1, ... ,ik be the indices of the heroes in a group. Then, the power of this
 * group is max(nums[i0], nums[i1], ... ,nums[ik])2 * min(nums[i0], nums[i1], ... ,nums[ik]).
 *
 * Return the sum of the power of all non-empty groups of heroes possible. Since the sum
 * could be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);

        long ans = 0, MOD = 1_000_000_007, prev = 0;
        for (long curr : nums) {
            ans = (ans + ((curr * curr) % MOD) * (prev + curr)) % MOD;
            prev = (prev * 2 + curr) % MOD;
        }
        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
    }

}
