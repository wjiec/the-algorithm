package lcci2.p1removeduplicatenodelcci;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.01. 移除重复节点
 *
 * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
 *
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 */

public class Solution {

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return null;
        Set<Integer> set = new HashSet<>(); set.add(head.val);
        for (var node = head; node.next != null;) {
            if (set.contains(node.next.val)) {
                node.next = node.next.next;
            } else {
                set.add(node.next.val);
                node = node.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateNodes(ListNode.build(1, 2, 3, 3, 2, 1)));
        System.out.println(new Solution().removeDuplicateNodes(ListNode.build(1, 1, 1, 1, 2)));
    }

}
