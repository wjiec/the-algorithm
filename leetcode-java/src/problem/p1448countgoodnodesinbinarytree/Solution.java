package problem.p1448countgoodnodesinbinarytree;

import common.TreeNode;

/**
 * 1448. Count Good Nodes in Binary Tree
 *
 * https://leetcode.cn/problems/count-good-nodes-in-binary-tree/
 *
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X
 * there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 */

public class Solution {

    private int ans = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(TreeNode node, int max) {
        if (node.val >= max) {
            ans++; max = node.val;
        }

        if (node.left != null) dfs(node.left, max);
        if (node.right != null) dfs(node.right, max);
    }

    public static void main(String[] args) {
        assert new Solution().goodNodes(TreeNode.build(3,1,4,3,null,1,5)) == 4;
        assert new Solution().goodNodes(TreeNode.build(3,3,null,4,2)) == 3;
        assert new Solution().goodNodes(TreeNode.build(1)) == 1;
    }

}
