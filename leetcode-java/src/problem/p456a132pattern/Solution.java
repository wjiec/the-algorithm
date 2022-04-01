package problem.p456a132pattern;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 456. 132 Pattern
 *
 * https://leetcode-cn.com/problems/132-pattern/
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i],
 * nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 */

public class Solution {

    @TODO(url = "https://leetcode-cn.com/problems/132-pattern/solution/132mo-shi-by-leetcode-solution-ye89/")
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[nums.length - 1]); // j

        int k = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < k) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) k = stack.pop();
            if (nums[i] > k) stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().find132pattern(new int[]{1,2,3,4});
        assert new Solution().find132pattern(new int[]{3,1,4,2});
        assert new Solution().find132pattern(new int[]{-1,3,2,0});
    }

}
