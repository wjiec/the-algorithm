package problem.p889constructbinarytreefrompreorderandpostordertraversal;

import common.TreeNode;

/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 *
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 *
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of
 * a binary tree of distinct values and postorder is the postorder traversal of the same tree,
 * reconstruct and return the binary tree.
 *
 * If there exist multiple answers, you can return any of them.
 */

public class Solution {

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return construct(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode construct(int[] pre, int a, int b, int[] post, int c, int d) {
        if (a > b) return null;

        TreeNode node = new TreeNode(pre[a]);
        if (a == b) return node;

        int idx = c, left = pre[a + 1];
        while (post[idx] != left) idx++;

        node.left = construct(pre, a + 1, a + 1 + idx - c, post, c, idx);
        node.right = construct(pre, a + 1 + idx - c + 1, b, post, idx + 1, d - 1);

        return node;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().constructFromPrePost(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1}));
        System.out.println(new Solution().constructFromPrePost(new int[]{1}, new int[]{1}));
    }

}
