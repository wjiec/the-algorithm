package problem.p1457pseudopalindromicpathsinabinarytree;

import common.TreeNode;

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 *
 * https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree/
 *
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said
 * to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 */

public class Solution {

    private int ans = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int state) {
        state = state ^ (1 << node.val);
        if (node.left == null && node.right == null) {
            if (state == 0 || (state & (state  -1)) == 0) {
                ans++;
            }
        }

        if (node.left != null) dfs(node.left, state);
        if (node.right != null) dfs(node.right, state);
    }

    public static void main(String[] args) {
        assert new Solution().pseudoPalindromicPaths(TreeNode.build(2,3,1,3,1,null,1)) == 2;
        assert new Solution().pseudoPalindromicPaths(TreeNode.build(2,1,1,1,3,null,null,null,null,null,1)) == 1;
        assert new Solution().pseudoPalindromicPaths(TreeNode.build(9)) == 1;
    }

}
