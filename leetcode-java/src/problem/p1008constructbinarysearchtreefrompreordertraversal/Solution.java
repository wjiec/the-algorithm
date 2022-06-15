package problem.p1008constructbinarysearchtreefrompreordertraversal;

import common.Checker;
import common.TreeNode;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 *
 * https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * Given an array of integers preorder, which represents the preorder traversal of
 * a BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree
 * with the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left
 * has a value strictly less than Node.val, and any descendant of Node.right
 * has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then
 * traverses Node.left, then traverses Node.right.
 */

public class Solution {

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length);
    }

    private TreeNode bstFromPreorder(int[] preorder, int l, int r) {
        if (l == r) return null;
        TreeNode node = new TreeNode(preorder[l]);

        int idx = l + 1;
        while (idx < r && preorder[idx] < node.val) idx++;

        node.left = bstFromPreorder(preorder, l + 1, idx);
        if (idx < r) node.right = bstFromPreorder(preorder, idx, r);

        return node;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().bstFromPreorder(new int[]{8,5,1,7,10,12}), TreeNode.build(8,5,10,1,7,null,12));
        assert Checker.check(new Solution().bstFromPreorder(new int[]{1,3}), TreeNode.build(1,null,3));
    }

}
