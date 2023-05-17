package problem.p2025maximumnumberofwaystopartitionanarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 2025. Maximum Number of Ways to Partition an Array
 *
 * https://leetcode.cn/problems/maximum-number-of-ways-to-partition-an-array/
 *
 * You are given a 0-indexed integer array nums of length n.
 * The number of ways to partition nums is the number of pivot indices that satisfy both conditions:
 *
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 *
 * You are also given an integer k. You can choose to change
 * the value of one element of nums to k, or to leave the array unchanged.
 *
 * Return the maximum possible number of ways to partition nums
 * to satisfy both conditions after changing at most one element.
 */

public class Solution {

    public int waysToPartition(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cnt.merge(sum += nums[i], 1, Integer::sum);
        }

        int ans = cnt.getOrDefault(sum / 2, 0);
    }

    public static void main(String[] args) {
        assert new Solution().waysToPartition(new int[]{2,-1,2}, 3) == 1;
        assert new Solution().waysToPartition(new int[]{0,0,0}, 1) == 2;
        assert new Solution().waysToPartition(new int[]{22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14}, -33) == 4s;
    }

}
