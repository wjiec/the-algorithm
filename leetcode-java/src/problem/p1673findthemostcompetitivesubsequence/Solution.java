package problem.p1673findthemostcompetitivesubsequence;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1673. Find the Most Competitive Subsequence
 *
 * https://leetcode.cn/problems/find-the-most-competitive-subsequence/
 *
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 *
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 *
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first
 * position where a and b differ, subsequence a has a number less than the corresponding number in b.
 *
 * For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the
 * final number, and 4 is less than 5.
 */

public class Solution {

    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 构建单调栈，只要栈内的元素加上剩余的元素还够k个, 就可以推进去
            while (!stack.isEmpty() && stack.peek() > nums[i] && n - i + stack.size() > k) stack.pop();
            stack.push(nums[i]);
        }

        int[] ans = new int[k];
        while (stack.size() != k) stack.pop();
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().mostCompetitive(new int[]{3,5,2,6}, 2), new int[]{2,6});
        assert Checker.check(new Solution().mostCompetitive(new int[]{2,4,3,3,5,4,9,6}, 4), new int[]{2,3,3,4});
    }

}
