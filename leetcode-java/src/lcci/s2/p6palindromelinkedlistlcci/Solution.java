package lcci.s2.p6palindromelinkedlistlcci;

import common.ListNode;

import java.util.ArrayDeque;

/**
 * 面试题 02.06. 回文链表
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
 *
 * 编写一个函数，检查输入的链表是否是回文的。
 */

public class Solution {

    public boolean isPalindrome(ListNode head) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (var curr = head; curr != null; curr = curr.next) {
            deque.add(curr.val);
        }

        while (deque.size() >= 2) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isPalindrome(ListNode.build(1,2));
        assert new Solution().isPalindrome(ListNode.build(1,2,2,1));
    }

}
