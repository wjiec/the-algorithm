package weekly.bw122.A;

/**
 * 3010. Divide an Array Into Subarrays With Minimum Cost I
 *
 * https://leetcode.cn/contest/biweekly-contest-122/problems/divide-an-array-into-subarrays-with-minimum-cost-i/
 *
 * You are given an array of integers nums of length n.
 *
 * The cost of an array is the value of its first element.
 *
 * For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
 *
 * You need to divide nums into 3 disjoint contiguous subarrays.
 *
 * Return the minimum possible sum of the cost of these subarrays.
 */

public class Solution {

    public int minimumCost(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ans = Math.min(ans, nums[0] + nums[i] + nums[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
