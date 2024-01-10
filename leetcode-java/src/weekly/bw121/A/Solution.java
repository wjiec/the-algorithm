package weekly.bw121.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-121/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/
 *
 * You are given a 0-indexed array of integers nums.
 *
 * A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1.
 *
 * In particular, the prefix consisting only of nums[0] is sequential.
 *
 * Return the smallest integer x missing from nums such that x is greater than or equal to
 * the sum of the longest sequential prefix.
 */

public class Solution {

    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (var v : nums) set.add(v);

        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) break;
            sum += nums[i];
        }

        while (set.contains(sum)) sum++;
        return sum;
    }

    public static void main(String[] args) {
    }

}
