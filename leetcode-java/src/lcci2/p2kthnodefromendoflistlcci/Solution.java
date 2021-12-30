package lcci2.p2kthnodefromendoflistlcci;

import common.ListNode;

/**
 * 面试题 02.02. 返回倒数第 k 个节点
 *
 * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
 *
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 */

public class Solution {

    public int kthToLast(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) fast = fast.next;
        for (; fast != null; fast = fast.next) slow = slow.next;
        return slow.val;
    }

    public static void main(String[] args) {
        assert new Solution().kthToLast(ListNode.build(1,2,3,4,5), 2) == 4;
    }

}
