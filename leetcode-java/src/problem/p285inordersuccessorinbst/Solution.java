package problem.p285inordersuccessorinbst;

import common.TreeNode;

/**
 * 285. Inorder Successor in BST
 *
 * https://leetcode.cn/problems/inorder-successor-in-bst/
 *
 * Given the root of a binary search tree and a node p in it, return the in-order
 * successor of that node in the BST. If the given node has no in-order successor
 * in the tree, return null.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 */

public class Solution {

    private TreeNode ans = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return ans;
    }

    private void inorder(TreeNode node, TreeNode p) {
        if (node == null || ans != null) return;

        inorder(node.left, p);
        if (node.val > p.val && ans == null) { ans = node; return; }
        inorder(node.right, p);
    }

    public static void main(String[] args) {
        assert new Solution().inorderSuccessor(TreeNode.build(2,1,3), TreeNode.build(1)).val == 2;
        assert new Solution().inorderSuccessor(TreeNode.build(5,3,6,2,4,null,null,1), TreeNode.build(6)) == null;
    }

}
