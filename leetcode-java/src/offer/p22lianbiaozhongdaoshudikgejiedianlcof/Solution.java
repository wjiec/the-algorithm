package offer.p22lianbiaozhongdaoshudikgejiedianlcof;

import common.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 *
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 */

public class Solution {

    private ListNode ans;

    public ListNode getKthFromEnd(ListNode head, int k) {
        backEach(head, k);
        return ans;
    }

    private int backEach(ListNode head, int k) {
        if (head.next != null) {
            int val = backEach(head.next, k) + 1;
            if (val == k) ans = head;
            return val;
        } else if (k == 1) ans = head;
        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().getKthFromEnd(ListNode.build(1,2,3,4,5,6), 3).val == 4;
        assert new Solution().getKthFromEnd(ListNode.build(1), 1).val == 1;
    }

}
