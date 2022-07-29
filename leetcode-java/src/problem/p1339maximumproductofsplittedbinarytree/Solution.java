package problem.p1339maximumproductofsplittedbinarytree;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1339. Maximum Product of Splitted Binary Tree
 *
 * https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree/
 *
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge
 * such that the product of the sums of the subtrees is maximized.
 *
 * Return the maximum product of the sums of the two subtrees. Since the answer
 * may be too large, return it modulo 109 + 7.
 *
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 */

public class Solution {

    public int maxProduct(TreeNode root) {
        sum = dfs(root);
        bfs(root);

        return (int) (ans % 1_000_000_007);
    }

    private long ans = 0;

    private void bfs(TreeNode node) {
        if (node.left != null) {
            long left = tree.get(node.left);
            ans = Math.max(ans, (sum - left) * left);
            bfs(node.left);
        }
        if (node.right != null) {
            long right = tree.get(node.right);
            ans = Math.max(ans, (sum - right) * right);
            bfs(node.right);
        }
    }

    private long sum = 0;
    private final Map<TreeNode, Long> tree = new HashMap<>();

    private long dfs(TreeNode node) {
        if (node == null) return 0;

        long left = dfs(node.left);
        long right = dfs(node.right);
        long curr = left + right + node.val;
        tree.put(node, curr);
        return curr;
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(TreeNode.build(1,2,3,4,5,6)) == 110;
        assert new Solution().maxProduct(TreeNode.build(1,null,2,3,4,null,null,5,6)) == 90;
    }

}
