package problem.p450deletenodeinabst;

import common.Checker;
import common.TreeNode;

/**
 * 450. Delete Node in a BST
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 */

public class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) root.right = deleteNode(root.right, key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) root = null;
            else if (root.right != null) {
                root.val = successor(root).val;
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root).val;
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private TreeNode successor(TreeNode node) {
        node = node.right;
        while (node != null && node.left != null) node = node.left;
        return node;
    }

    private TreeNode predecessor(TreeNode node) {
        node = node.left;
        while (node != null && node.right != null) node = node.right;
        return node;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().deleteNode(TreeNode.build(5,3,6,2,4,null,7), 3),
            TreeNode.build(5,4,6,2,null,null,7));

        assert Checker.check(new Solution().deleteNode(TreeNode.build(5,3,6,2,4,null,7), 0),
            TreeNode.build(5,3,6,2,4,null,7));

        assert Checker.check(new Solution().deleteNode(TreeNode.build(), 0),
            TreeNode.build());
    }

}
