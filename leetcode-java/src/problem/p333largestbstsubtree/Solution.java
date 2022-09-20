package problem.p333largestbstsubtree;

import common.Tag;
import common.TreeNode;

/**
 * 333. Largest BST Subtree
 *
 * https://leetcode.cn/problems/largest-bst-subtree/
 *
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search
 * Tree (BST), where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 */

public class Solution {

    private TreeNode prev = null;

    @Tag({"二叉树中的二叉搜索树"})
    public int largestBSTSubtree(TreeNode root) {
        prev = null;
        if (root == null) return 0;
        if (valid(root)) return count(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    private boolean valid(TreeNode node) {
        if (node == null) return true;
        if (!valid(node.left)) return false;
        if (prev != null && prev.val >= node.val) return false;

        prev = node;
        return valid(node.right);
    }

    private int count(TreeNode node) {
        return node == null ? 0 : (count(node.left) + count(node.right) + 1);
    }

    public static void main(String[] args) {
        assert new Solution().largestBSTSubtree(TreeNode.build(5,4,null,null,2,null,1,null,3)) == 2;
        assert new Solution().largestBSTSubtree(TreeNode.build(2,3,null,1)) == 2;
        assert new Solution().largestBSTSubtree(TreeNode.build(1,null,2)) == 2;
        assert new Solution().largestBSTSubtree(TreeNode.build(1)) == 1;

        assert new Solution().largestBSTSubtree(TreeNode.build(10,5,15,1,8,null,7)) == 3;
        assert new Solution().largestBSTSubtree(TreeNode.build(4,2,7,2,3,5,null,2,null,null,null,null,null,1)) == 2;
    }

}
