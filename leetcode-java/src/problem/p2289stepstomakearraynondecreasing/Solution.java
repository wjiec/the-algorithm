package problem.p2289stepstomakearraynondecreasing;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2289. Steps to Make Array Non-decreasing
 *
 * https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
 *
 * You are given a 0-indexed integer array nums. In one step, remove all elements nums[i]
 * where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 *
 * Return the number of steps performed until nums becomes a non-decreasing array.
 */

public class Solution {

    public int totalSteps(int[] nums) {
        int ans = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (var v : nums) {
            int max = 0;
            while (!stack.isEmpty() && stack.peek()[0] <= v) {
                max = Math.max(max, stack.pop()[1]);
            }
            max = stack.isEmpty() ? 0 : max + 1;
            stack.push(new int[]{v, max});

            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().totalSteps(new int[]{5,3,4,4,7,3,6,11,8,5,11}) == 3;
        assert new Solution().totalSteps(new int[]{4,5,7,7,13}) == 0;
    }

}
