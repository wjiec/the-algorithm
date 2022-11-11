package offer2.p49;

import common.TreeNode;

/**
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 *
 * https://leetcode.cn/problems/3Etpl5/
 *
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 *
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 */

public class Solution {

    private int ans = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int v) {
        if (node == null) return;

        int next = v * 10 + node.val;
        if (node.left == null && node.right == null) {
            ans += next; return;
        }

        dfs(node.left, next); dfs(node.right, next);
    }

    public static void main(String[] args) {
        assert new Solution().sumNumbers(TreeNode.build(1,2,3)) == 25;
        assert new Solution().sumNumbers(TreeNode.build(4,9,0,5,1)) == 1026;
    }

}
