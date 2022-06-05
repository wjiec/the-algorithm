package problem.p951flipequivalentbinarytrees;

import common.TreeNode;

/**
 * 951. Flip Equivalent Binary Trees
 *
 * https://leetcode.cn/problems/flip-equivalent-binary-trees/
 *
 * For a binary tree T, we can define a flip operation as follows: choose any node,
 * and swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X
 * equal to Y after some number of flip operations.
 *
 * Given the roots of two binary trees root1 and root2, return true if the two
 * trees are flip equivalent or false otherwise.
 */

public class Solution {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return root1 == null && root2 == null;
        if (root1.val != root2.val) return false;

        int c1 = score(root1), c2 = score(root2);
        if (c1 != c2) return false;

        if (c1 == 2) {
            if (root1.left.val == root2.left.val)
                return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
            return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        } else if (c1 == 1) {
            if (root1.left != null)
                return flipEquiv(root1.left, root2.left != null ? root2.left : root2.right);
            return flipEquiv(root1.right, root2.left != null ? root2.left : root2.right);
        }

        return true;
    }

    private int score(TreeNode node) { return (node.left == null ? 0 : 1) + (node.right == null ? 0 : 1); }

    public static void main(String[] args) {
        assert new Solution().flipEquiv(TreeNode.build(1,2,3,4,5,6,null,null,null,7,8),
            TreeNode.build(1,3,2,null,6,4,5,null,null,null,null,8,7));
        assert new Solution().flipEquiv(null, null);
        assert !new Solution().flipEquiv(null, TreeNode.build(1));
    }

}
