package problem.p946validatestacksequences;

import java.util.Stack;

/**
 * 946. Validate Stack Sequences
 *
 * https://leetcode-cn.com/problems/validate-stack-sequences/
 *
 * Given two sequences pushed and popped with distinct values,
 * return true if and only if this could have been the result of
 * a sequence of push and pop operations on an initially empty stack.
 */

public class Solution {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int idx = 0;
        Stack<Integer> stack = new Stack<>();
        for (var n : pushed) {
            stack.push(n);
            while (!stack.isEmpty() && stack.peek() == popped[idx]) {
                idx++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        assert new Solution().validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
        assert !new Solution().validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
    }

}
