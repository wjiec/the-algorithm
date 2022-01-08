package problem.p144binarytreepreordertraversal;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 144. Binary Tree Preorder Traversal
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 */

public class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            for (; root != null; root = root.left) {
                ans.add(root.val);
                stack.push(root);
            }

            root = stack.pop();
            root = root.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().preorderTraversal(TreeNode.build(1,null,2,3)).equals(List.of(1,2,3));
        assert new Solution().preorderTraversal(TreeNode.build()).equals(List.of());
        assert new Solution().preorderTraversal(TreeNode.build(1)).equals(List.of(1));
        assert new Solution().preorderTraversal(TreeNode.build(1,2)).equals(List.of(1,2));
    }

}
