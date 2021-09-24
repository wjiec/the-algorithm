package daily.d210924p430flattenamultileveldoublylinkedlist;

/**
 * 430. Flatten a Multilevel Doubly Linked List
 *
 * https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
 *
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 *
 * These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 *
 * You are given the head of the first level of the list.
 */

public class Solution {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        Node(int v) { val = v; }
    }

    public Node flatten(Node head) {
        return doFlatten(head)[0];
    }

    public Node[] doFlatten(Node head) {
        Node prev = null;
        for (var curr = head; curr != null; curr = curr.next) {
            if (curr.child != null) {
                var ends = doFlatten(curr.child);
                ends[0].prev = curr;
                ends[1].next = curr.next;
                if (curr.next != null) {
                    curr.next.prev = ends[1];
                }

                curr.next = ends[0];
                curr.child = null;
            }

            prev = curr;
        }
        return new Node[]{head, prev};
    }

    public static void main(String[] args) {
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
