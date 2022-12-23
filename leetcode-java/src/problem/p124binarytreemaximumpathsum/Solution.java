package problem.p124binarytreemaximumpathsum;

import common.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 *
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes
 * in the sequence has an edge connecting them.
 *
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */

public class Solution {

    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        ans = Math.max(ans, node.val);

        int l = dfs(node.left), r = dfs(node.right);
        ans = Math.max(ans, l + node.val);
        ans = Math.max(ans, r + node.val);
        ans = Math.max(ans, l + r + node.val);

        return Math.max(0, Math.max(l, r) + node.val);
    }

    public static void main(String[] args) {
        assert new Solution().maxPathSum(TreeNode.build(1,2,3)) == 6;
        assert new Solution().maxPathSum(TreeNode.build(-10,9,20,null,null,15,7)) == 42;
    }

}
