package problem.p117populatingnextrightpointersineachnodeii;

/**
 * 117. Populating Next Right Pointers in Each Node II
 *
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * Given a binary tree, populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 */

public class Solution {

    private static class Node {
        public int val;
        public Node left, right, next;
        public Node() {}
        public Node(int v) { val = v; }
        public Node(int v, Node l, Node r) { val = v; left = l; right = r; }
        public Node(int v, Node l, Node r, Node n) { val = v; left = l; right = r; next = n; }
    }

    public Node connect(Node root) {
        for (Node head = root; head != null; ) {
            for (Node curr = head, prev = null; curr != null; curr = curr.next) {
                if (curr == head) head = null;
                if (head == null && curr.left != null) head = curr.left;
                if (head == null && curr.right != null) head = curr.right;

                if (curr.left != null) {
                    if (prev != null) prev.next = curr.left;
                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (prev != null) prev.next = curr.right;
                    prev = curr.right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1, new Node(2), new Node(3));
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(new Solution().connect(root));
    }

}
