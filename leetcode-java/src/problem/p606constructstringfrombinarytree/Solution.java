package problem.p606constructstringfrombinarytree;

import common.TreeNode;

/**
 * 606. Construct String from Binary Tree
 *
 * https://leetcode-cn.com/problems/construct-string-from-binary-tree/
 *
 * Given the root of a binary tree, construct a string consists of parenthesis
 * and integers from a binary tree with the preorder traversing way, and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping
 * relationship between the string and the original binary tree.
 */

public class Solution {

    public String tree2str(TreeNode root) {
        if (root == null) return "";
        if (root.left == null && root.right == null) return root.val + "";
        if (root.right == null) return root.val + "(" + tree2str(root.left) + ")";
        return root.val + "(" + tree2str(root.left) + ")(" + tree2str(root.right) + ")";
    }

    public static void main(String[] args) {
        assert new Solution().tree2str(TreeNode.build(1,2,3,4)).equals("1(2(4))(3)");
        assert new Solution().tree2str(TreeNode.build(1,2,3,null,4)).equals("1(2()(4))(3)");
    }

}
