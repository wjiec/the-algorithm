package problem.p662maximumwidthofbinarytree;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 662. Maximum Width of Binary Tree
 *
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 *
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree
 * extending down to that level are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 */

public class Solution {

    private int ans = 0;
    private final Map<Integer, Integer> map = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth, int pos) {
        if (node == null) return;

        map.putIfAbsent(depth, pos);
        ans = Math.max(ans, pos - map.get(depth) + 1);
        dfs(node.left, depth + 1, 2 * pos);
        dfs(node.right, depth + 1, 2 * pos + 1);
    }

    public static void main(String[] args) {
        assert new Solution().widthOfBinaryTree(TreeNode.build(1,3,2,5,3,null,9)) == 4;
        assert new Solution().widthOfBinaryTree(TreeNode.build(1,3,2,5,null,null,9,6,null,7)) == 7;
        assert new Solution().widthOfBinaryTree(TreeNode.build(1,3,2,5)) == 2;
    }

}
