package problem.p236lowestcommonancestorofabinarytree;

import common.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 */

public class Solution {

    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;

        boolean isRoot = root.val == p.val || root.val == q.val;
        boolean isLeft = dfs(root.left, p, q);
        boolean isRight = dfs(root.right, p, q);
        if ((isLeft && isRight) || (isRoot && (isLeft || isRight))) ans = root;
        return isRoot || isLeft || isRight;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.build(3,5,1,6,2,0,8,null,null,7,4);
        assert tree != null;
        assert new Solution().lowestCommonAncestor(tree, tree.left, tree.right).val == 3;
        assert new Solution().lowestCommonAncestor(tree, tree.left, tree.left.right.right).val == 5;
        assert new Solution().lowestCommonAncestor(tree, tree, tree.right).val == 3;
    }

}
