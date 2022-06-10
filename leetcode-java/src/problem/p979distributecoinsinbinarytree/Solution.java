package problem.p979distributecoinsinbinarytree;

import common.TreeNode;

/**
 * 979. Distribute Coins in Binary Tree
 *
 * https://leetcode.cn/problems/distribute-coins-in-binary-tree/
 *
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins.
 * There are n coins in total throughout the whole tree.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * A move may be from parent to child, or from child to parent.
 *
 * Return the minimum number of moves required to make every node have exactly one coin.
 */

public class Solution {

    private int ans = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    // requirements
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null)
            return node.val - 1;

        int left = dfs(node.left), right = dfs(node.right);
        ans += Math.abs(left) + Math.abs(right);
        return node.val + left + right - 1;
    }

    public static void main(String[] args) {
        assert new Solution().distributeCoins(TreeNode.build(3,0,0)) == 2;
        assert new Solution().distributeCoins(TreeNode.build(0,3,0)) == 3;
    }

}
