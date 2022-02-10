package daily.d210517p993cousinsinbinarytree;

import common.TreeNode;

/**
 * 993. Cousins in Binary Tree
 *
 * https://leetcode-cn.com/problems/cousins-in-binary-tree/
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */

public class Solution {

    private int xDepth = 0, yDepth = 0, xParent = 0, yParent = 0;

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, 0, 0);
        return xDepth == yDepth && xParent != yParent;
    }

    private void dfs(TreeNode root, int x, int y, int depth, int parent) {
        if (root.val == x) {
            xDepth = depth;
            xParent = parent;
        }
        if (root.val == y) {
            yDepth = depth;
            yParent = parent;
        }
        if (root.left != null) {
            dfs(root.left, x, y, depth + 1, root.val);
        }
        if (root.right != null) {
            dfs(root.right, x, y, depth + 1, root.val);
        }
    }

    public static void main(String[] args) {
        assert !new Solution().isCousins(TreeNode.build(1,2,3,4), 4, 3);
        assert new Solution().isCousins(TreeNode.build(1,2,3,null,4,null,5), 5, 4);
        assert !new Solution().isCousins(TreeNode.build(1,2,3,null,4), 2, 3);
    }

}
