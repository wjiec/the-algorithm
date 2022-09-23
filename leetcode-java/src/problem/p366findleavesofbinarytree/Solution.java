package problem.p366findleavesofbinarytree;

import common.Checker;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366. Find Leaves of Binary Tree
 *
 * https://leetcode.cn/problems/find-leaves-of-binary-tree/
 *
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 */

public class Solution {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        while (root != null) {
            List<Integer> curr = new ArrayList<>();
            root = recursive(root, curr);
            ans.add(curr);
        }
        return ans;
    }

    private TreeNode recursive(TreeNode node, List<Integer> ans) {
        if (node == null) return null;

        if (node.left == null && node.right == null) {
            ans.add(node.val);
            return null;
        }

        node.left = recursive(node.left, ans);
        node.right = recursive(node.right, ans);
        return node;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findLeaves(TreeNode.build(1,2,3)), List.of(List.of(2,3), List.of(1)));
    }

}
