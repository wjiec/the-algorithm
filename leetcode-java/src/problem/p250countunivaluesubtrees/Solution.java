package problem.p250countunivaluesubtrees;

import common.TreeNode;

/**
 * 250. Count Univalue Subtrees
 *
 * https://leetcode-cn.com/problems/count-univalue-subtrees/
 *
 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 */

public class Solution {

    private int ans = 0;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private Integer dfs(TreeNode node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) {
            ans++;
            return node.val;
        }

        Integer l = dfs(node.left), r = dfs(node.right);
        if ((l == null || l == node.val) && (r == null || r == node.val)) {
            ans++;
            return node.val;
        }

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        assert new Solution().countUnivalSubtrees(TreeNode.build(
            2,-89,-89,-1,-70,-70,-1,null,84,null,-75,null,-75,84,null,57,67,81,null,null,81,67,57
        )) == 6;

        assert new Solution().countUnivalSubtrees(TreeNode.build(5,1,5,5,5,null,5)) == 4;
        assert new Solution().countUnivalSubtrees(TreeNode.build()) == 0;
        assert new Solution().countUnivalSubtrees(TreeNode.build(5,5,5,5,5,null,5)) == 6;
    }

}
