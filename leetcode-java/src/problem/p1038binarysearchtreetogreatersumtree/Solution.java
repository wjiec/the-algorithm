package problem.p1038binarysearchtreetogreatersumtree;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 *
 * https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of
 * the original BST is changed to the original key plus the sum of all keys
 * greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

public class Solution {

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;

        int total = 0;
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            node = stack.pop();
            node.val = (total += node.val);
            node = node.left;
        }

        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().bstToGst(TreeNode.build(4,1,6,0,2,5,7,null,null,null,3,null,null,null,8)),
            TreeNode.build(30,36,21,36,35,26,15,null,null,null,33,null,null,null,8));

        assert Checker.check(new Solution().bstToGst(TreeNode.build(0,null,1)),
            TreeNode.build(1,null,1));
    }

}
