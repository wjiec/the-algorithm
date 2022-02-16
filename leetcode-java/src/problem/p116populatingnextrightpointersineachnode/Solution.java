package problem.p116populatingnextrightpointersineachnode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 116. Populating Next Right Pointers in Each Node
 *
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 *
 * Populate each next pointer to point to its next right node. If there is no next right node,
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
        if (root != null) {
            Queue<Node> nodes = new ArrayDeque<>();
            for (nodes.add(root); !nodes.isEmpty(); ) {
                for (int i = 0, l = nodes.size(); i < l; i++) {
                    Node curr = nodes.remove();
                    if (i < l - 1) curr.next = nodes.peek();
                    if (curr.left != null) nodes.add(curr.left);
                    if (curr.right != null) nodes.add(curr.right);
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
