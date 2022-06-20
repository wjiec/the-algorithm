package problem.p1026maximumdifferencebetweennodeandancestor;

import common.TreeNode;

/**
 * 1026. Maximum Difference Between Node and Ancestor
 *
 * https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/
 *
 * Given the root of a binary tree, find the maximum value v for which there exist
 * different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any
 * child of a is an ancestor of b.
 */

public class Solution {

    private int ans = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiff(root, root.val, root.val);
        return ans;
    }

    private void maxAncestorDiff(TreeNode node, int max, int min) {
        ans = Math.max(ans, Math.abs(max - node.val));
        ans = Math.max(ans, Math.abs(min - node.val));

        if (node.val > max) max = node.val;
        if (node.val < min) min = node.val;

        if (node.left != null) maxAncestorDiff(node.left, max, min);
        if (node.right != null) maxAncestorDiff(node.right, max, min);
    }

    public static void main(String[] args) {
        assert new Solution().maxAncestorDiff(TreeNode.build(8,3,10,1,6,null,14,null,null,4,7,13)) == 7;
        assert new Solution().maxAncestorDiff(TreeNode.build(1,null,2,null,0,3)) == 3;
    }

}
