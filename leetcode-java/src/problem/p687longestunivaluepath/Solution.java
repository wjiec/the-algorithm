package problem.p687longestunivaluepath;

import common.TreeNode;

import java.util.Map;

/**
 * 687. Longest Univalue Path
 *
 * https://leetcode-cn.com/problems/longest-univalue-path/
 *
 * Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value.
 *
 * This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 */

public class Solution {

    private int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        longestPath(root);
        return ans;
    }

    private int longestPath(TreeNode node) {
        if (node == null) return 0;

        int l = longestPath(node.left);
        int r = longestPath(node.right);

        int ll = 0, lr = 0;
        if (node.left != null && node.left.val == node.val) ll += l + 1;
        if (node.right != null && node.right.val == node.val) lr += r + 1;

        ans = Math.max(ans, ll + lr);
        return Math.max(ll, lr);
    }

    public static void main(String[] args) {
        assert new Solution().longestUnivaluePath(TreeNode.build(5,4,5,1,1,5)) == 2;
        assert new Solution().longestUnivaluePath(TreeNode.build(1,4,5,4,4,5)) == 2;
    }

}
