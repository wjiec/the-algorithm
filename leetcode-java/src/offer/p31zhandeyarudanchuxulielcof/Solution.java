package offer.p31zhandeyarudanchuxulielcof;

import java.util.Stack;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 *
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
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
        assert new Solution().validateStackSequences(new int[]{0,2,1}, new int[]{0,1,2});

        assert new Solution().validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
        assert !new Solution().validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
    }

}
