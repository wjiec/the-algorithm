package ability;

import common.ListNode;

public class LinkedList {

    // 翻转整个链表
    public static void reverse(ListNode head) {
        ListNode tail = new ListNode(Integer.MAX_VALUE);
        while (head != null) {
            ListNode next = head.next;
            head.next = tail;
            tail = head;
            head = next;
        }
    }

}
