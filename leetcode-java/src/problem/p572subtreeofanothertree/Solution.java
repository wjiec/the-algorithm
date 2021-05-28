package problem.p572subtreeofanothertree;

import common.TreeNode;

/**
 * 572. Subtree of Another Tree
 *
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * Given the roots of two binary trees root and subRoot,
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree tree could also be considered as a subtree of itself.
 */

public class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        System.out.println(dfs(root));
        System.out.println(dfs(subRoot));
        return dfs(root).contains(dfs(subRoot));
    }

    public String dfs(TreeNode root) {
        if (root == null) return "-";
        if (root.left == null && root.right == null) return "=" + root.val + "|";

        return "." + root.val + "," + dfs(root.left) + dfs(root.right);
    }

    public static void main(String[] args) {
        assert !new Solution().isSubtree(TreeNode.build(12), TreeNode.build(2));
        assert !new Solution().isSubtree(TreeNode.build(1,2,3), TreeNode.build(1,2));
        assert !new Solution().isSubtree(TreeNode.build(3,4,5,1,null,2), TreeNode.build(3,1,2));
        assert new Solution().isSubtree(TreeNode.build(1,1), TreeNode.build(1));
        assert new Solution().isSubtree(TreeNode.build(3,4,5,1,2), TreeNode.build(4,1,2));
        assert !new Solution().isSubtree(TreeNode.build(3,4,5,1,2,null,null,null,null,0), TreeNode.build(4,1,2));
    }

}
