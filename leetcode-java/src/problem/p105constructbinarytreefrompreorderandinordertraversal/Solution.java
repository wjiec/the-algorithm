package problem.p105constructbinarytreefrompreorderandinordertraversal;

import common.TreeNode;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */

public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return doBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode doBuildTree(int[] pre, int[] in, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) return null;

        TreeNode node = new TreeNode(pre[preLeft]);
        int inMid = inLeft;
        for (; inMid < inRight; inMid++) if (in[inMid] == pre[preLeft]) break;

        int leftSize = inMid - inLeft;
        node.left = doBuildTree(pre, in, preLeft + 1, preLeft + leftSize, inLeft, inMid - 1);
        node.right = doBuildTree(pre, in, preLeft + leftSize + 1, preRight, inMid + 1, inRight);

        return node;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
    }

}
