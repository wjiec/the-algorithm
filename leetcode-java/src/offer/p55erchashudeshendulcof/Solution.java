package offer.p55erchashudeshendulcof;

import common.TreeNode;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 *
 * https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 *
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 */

public class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        assert new Solution().maxDepth(TreeNode.build(3,9,20,null,null,15,7)) == 3;
    }

}
