package offer.p7zhongjianerchashulcof;

import common.TreeNode;

/**
 * 剑指 Offer 07. 重建二叉树
 *
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */

public class Solution {

    /**
     * 前序遍历：[root, LEFT, RIGHT]
     * 中序遍历：[LEFT, root, RIGHT]
     */
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
