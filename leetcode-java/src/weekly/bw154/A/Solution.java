package weekly.bw154.A;

/**
 * 3512. Minimum Operations to Make Array Sum Divisible by K
 *
 * https://leetcode.cn/contest/biweekly-contest-154/problems/minimum-operations-to-make-array-sum-divisible-by-k/
 *
 * You are given an integer array nums and an integer k. You can perform the following operation any number of times:
 *
 * Select an index i and replace nums[i] with nums[i] - 1.
 *
 * Return the minimum number of operations required to make the sum of the array divisible by k.
 */

public class Solution {

    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (var v : nums) sum += v;
        return sum % k;
    }

    public static void main(String[] args) {
    }

}
