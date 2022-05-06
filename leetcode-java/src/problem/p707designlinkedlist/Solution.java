package problem.p707designlinkedlist;

/**
 * 707. Design Linked List
 *
 * https://leetcode-cn.com/problems/design-linked-list/
 *
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
 * and next is a pointer/reference to the next node.
 *
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate
 * the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 *
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 *
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
 * the new node will be the first node of the linked list.
 *
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 *
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
 * If index equals the length of the linked list, the node will be appended to the end of the linked list.
 * If index is greater than the length, the node will not be inserted.
 *
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 */

public class Solution {

    private static class MyLinkedList {
        private static class Node {
            private int val;
            private Node prev, next;
            public Node(int val) { this.val = val; }
            public Node(int val, Node next) { this.val = val; this.next = next; }
            public Node(int val, Node prev, Node next) { this.val = val; this.prev = prev; this.next = next; }
        }

        private int size = 0;
        private Node head, tail;
        public MyLinkedList() { head = new Node(0); tail = new Node(0); head.next = tail; tail.prev = head; }

        public int get(int index) {
            if (size == 0 || index >= size) return -1;
            return getNode(index).val;
        }

        public void addAtHead(int val) {
            size++;
            head.val = val;
            head = new Node(0, head);
            head.next.prev = head;
        }

        public void addAtTail(int val) {
            size++;
            tail.val = val;
            tail = new Node(0, tail, null);
            tail.prev.next = tail;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) return;

            if (index <= 0) { addAtHead(val); return; }
            if (index == size) { addAtTail(val); return; }

            Node found = getNode(index);
            found.prev = new Node(val, found.prev, found);
            found.prev.prev.next = found.prev;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;

            Node found = getNode(index);
            Node prev = found.prev, next = found.next;
            prev.next = next;
            next.prev = prev;
            found.prev = found.next = null;
            size--;
        }

        private Node getNode(int index) {
            if (index <= size / 2) {
                Node curr = head;
                for (int i = 0; i <= index; i++) curr = curr.next;
                return curr;
            }

            Node curr = tail;
            for (int i = 0; i < size - index; i++) curr = curr.prev;
            return curr;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        assert myLinkedList.get(0) == 1;
        assert myLinkedList.get(1) == 3;
        myLinkedList.addAtIndex(1, 2);
        assert myLinkedList.get(0) == 1;
        assert myLinkedList.get(1) == 2;
        assert myLinkedList.get(2) == 3;
        myLinkedList.deleteAtIndex(1);
        assert myLinkedList.get(0) == 1;
        assert myLinkedList.get(1) == 3;
        myLinkedList.deleteAtIndex(1);
        assert myLinkedList.get(0) == 1;
        myLinkedList.deleteAtIndex(1); // do nothing
        myLinkedList.deleteAtIndex(0);
        assert myLinkedList.get(0) == -1;

        // ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead",
        // "addAtTail","get","addAtHead","addAtIndex","addAtHead"]
        // [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
        myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);  // 7
        myLinkedList.addAtHead(2);  // 2 7
        myLinkedList.addAtHead(1);  // 1 2 7
        myLinkedList.addAtIndex(3, 0);  // 1 2 7 0
        myLinkedList.deleteAtIndex(2);  // 1 2 0
        myLinkedList.addAtHead(6);  // 6 1 2 0
        myLinkedList.addAtTail(4);  // 6 1 2 0 4
        assert myLinkedList.get(4) == 4;
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);
    }

}
