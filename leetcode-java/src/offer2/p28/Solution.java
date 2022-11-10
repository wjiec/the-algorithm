package offer2.p28;

/**
 * 剑指 Offer II 028. 展平多级双向链表
 *
 * https://leetcode.cn/problems/Qv1Da2/
 *
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node(int v) { val = v; }
        public String toString() {
            String nextString = "null";
            if (next != null) nextString = next.toString();
            return "[val=" + val + ", next=" + nextString + "]";
        }
    }

    public Node flatten(Node head) {
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.child != null) {
                Node next = curr.next;
                curr.next = flatten(curr.child);
                curr.child = null;
                curr.next.prev = curr;
                while (curr.next != null) curr = curr.next;
                if (next != null) next.prev = curr;
                curr.next = next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        assert new Solution().flatten(null) == null;
        
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node1.next = node2;
        node2.prev = node1;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;

        node4.next = node5;
        node5.prev = node4;

        node5.next = node6;
        node6.prev = node5;

        node3.child = node7;
        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;

        node9.next = node10;
        node10.prev = node9;

        node8.child = node11;
        node11.next = node12;
        node12.prev = node11;

        assert new Solution().flatten(node1) != null;
    }

}
