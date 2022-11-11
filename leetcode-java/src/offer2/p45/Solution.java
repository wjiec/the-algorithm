package offer2.p45;

import common.TreeNode;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 *
 * https://leetcode.cn/problems/LwUNpT/
 *
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 */

public class Solution {

    private int deep = -1, ans = 0;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (depth > deep) { ans = node.val; deep = depth; }

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    public static void main(String[] args) {
        assert new Solution().findBottomLeftValue(TreeNode.build(2,1,3)) == 1;
        assert new Solution().findBottomLeftValue(TreeNode.build(1,2,3,4,null,5,6,null,null,7)) == 7;
    }

}
