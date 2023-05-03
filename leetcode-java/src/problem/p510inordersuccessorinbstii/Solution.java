package problem.p510inordersuccessorinbstii;

/**
 * 510. Inorder Successor in BST II
 *
 * https://leetcode.cn/problems/inorder-successor-in-bst-ii/
 *
 * Given a node in a binary search tree, return the in-order successor of that node in the BST.
 * If that node has no in-order successor, return null.
 *
 * The successor of a node is the node with the smallest key greater than node.val.
 *
 * You will have direct access to the node but not to the root of the tree.
 * Each node will have a reference to its parent node.
 */

public class Solution {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            Node ans = node.right;
            while (ans.left != null) ans = ans.left;
            return ans;
        }

        while (node.parent != null && node.parent.right == node) node = node.parent;
        return node.parent;
    }

    public static void main(String[] args) {
    }

}
