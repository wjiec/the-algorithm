package offer2.p51;

import common.TreeNode;

/**
 * 剑指 Offer II 051. 节点之和最大的路径
 *
 * https://leetcode.cn/problems/jC7MId
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 *
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 */

public class Solution {

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int ans = Integer.MIN_VALUE;

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        ans = Math.max(ans, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public static void main(String[] args) {
        assert new Solution().maxPathSum(TreeNode.build(2,1)) == 3;
        assert new Solution().maxPathSum(TreeNode.build(1,2)) == 3;
        assert new Solution().maxPathSum(TreeNode.build(2,-1)) == 2;
        assert new Solution().maxPathSum(TreeNode.build(-2,-1)) == -1;
        assert new Solution().maxPathSum(TreeNode.build(-1)) == -1;
        assert new Solution().maxPathSum(TreeNode.build(0)) == 0;
        assert new Solution().maxPathSum(TreeNode.build(1,2,3)) == 6;
        assert new Solution().maxPathSum(TreeNode.build(-10,9,20,null,null,15,7)) == 42;
    }

}
