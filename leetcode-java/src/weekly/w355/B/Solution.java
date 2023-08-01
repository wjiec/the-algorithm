package weekly.w355.B;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2789. Largest Element in an Array after Merge Operations
 *
 * https://leetcode.cn/contest/weekly-contest-355/problems/largest-element-in-an-array-after-merge-operations/
 *
 * You are given a 0-indexed array nums consisting of positive integers.
 *
 * You can do the following operation on the array any number of times:
 *
 * Choose an integer i such that 0 <= i < nums.length - 1 and nums[i] <= nums[i + 1].
 * Replace the element nums[i + 1] with nums[i] + nums[i + 1] and delete the element nums[i] from the array.
 *
 * Return the value of the largest element that you can possibly obtain in the final array.
 */

public class Solution {

    public long maxArrayValue(int[] nums) {
        long ans = 0;
        Deque<Long> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            long curr = nums[i];
            if (!stack.isEmpty() && stack.peek() >= curr) {
                curr += stack.pop();
            }
            stack.push(curr);
            assert !stack.isEmpty();
            ans = Math.max(ans, stack.peek());
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
