package problem.p100sametree;

import common.TreeNode;

/**
 * 100. Same Tree
 *
 * https://leetcode-cn.com/problems/same-tree/
 *
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */

public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        assert new Solution().isSameTree(TreeNode.build(1,2,3), TreeNode.build(1,2,3));
    }

}
