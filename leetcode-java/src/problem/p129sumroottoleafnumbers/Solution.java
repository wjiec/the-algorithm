package problem.p129sumroottoleafnumbers;

import common.TreeNode;

/**
 * 129. Sum Root to Leaf Numbers
 *
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *
 * Each root-to-leaf path in the tree represents a number.
 *
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 *
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A leaf node is a node with no children.
 */

public class Solution {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int v) {
        int sum = v * 10 + node.val;
        if (node.left == null && node.right == null) return sum;

        int left = 0, right = 0;
        if (node.left != null) left = dfs(node.left, sum);
        if (node.right != null) right = dfs(node.right, sum);
        return left + right;
    }

    public static void main(String[] args) {
        assert new Solution().sumNumbers(TreeNode.build(1,2,3)) == 25;
        assert new Solution().sumNumbers(TreeNode.build(4,9,0,5,1)) == 1026;
    }

}
