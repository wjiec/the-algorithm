package lcci.s2.p8linkedlistcyclelcci;

import common.ListNode;

/**
 * 面试题 02.08. 环路检测
 *
 * https://leetcode.cn/problems/linked-list-cycle-lcci/
 *
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ans = head;
                while (ans != fast) {
                    ans = ans.next;
                    fast = fast.next;
                }
                return ans;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        assert new Solution().detectCycle(ListNode.build(1,2,3,4,5)) == null;
    }

}
