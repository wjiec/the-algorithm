package problem.p298binarytreelongestconsecutivesequence;

import common.TreeNode;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 *
 * https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence/
 *
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * A consecutive sequence path is a path where the values increase by one along the path.
 *
 * Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
 */

public class Solution {

    private int ans = 0;

    public int longestConsecutive(TreeNode root) {
        dfs(root, Integer.MIN_VALUE, 1);
        return ans;
    }

    private void dfs(TreeNode node, int prev, int count) {
        ans = Math.max(ans, count);
        if (node != null) {
            int curr = node.val == prev + 1 ? count + 1 : 1;
            dfs(node.left, node.val, curr);
            dfs(node.right, node.val, curr);
        }
    }

    public static void main(String[] args) {
        assert new Solution().longestConsecutive(TreeNode.build(1,null,3,2,4,null,null,null,5)) == 3;
        assert new Solution().longestConsecutive(TreeNode.build(2,null,3,2,null,1)) == 2;
    }

}
