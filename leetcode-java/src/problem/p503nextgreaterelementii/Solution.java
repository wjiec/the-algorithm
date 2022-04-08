package problem.p503nextgreaterelementii;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503. Next Greater Element II
 *
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 *
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 *
 * If it doesn't exist, return -1 for this number.
 */

public class Solution {

    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 1) return new int[]{-1};

        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0, n = nums.length; i < 2 * n - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().nextGreaterElements(new int[]{1,2,1}), new int[]{2,-1,2});
        assert Checker.check(new Solution().nextGreaterElements(new int[]{1,2,3,4,3}), new int[]{2,3,4,-1,4});
    }

}
