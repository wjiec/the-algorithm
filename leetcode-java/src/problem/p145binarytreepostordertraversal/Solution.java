package problem.p145binarytreepostordertraversal;

import common.TreeNode;

import java.util.*;

/**
 * 145. Binary Tree Postorder Traversal
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 */

public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        for (TreeNode prev = null; root != null || !deque.isEmpty(); ) {
            for (; root != null; root = root.left) {
                deque.push(root);
            }

            root = deque.pop();
            if (root.right == null || root.right == prev) {
                ans.add(root.val);
                prev = root;
                root = null;
            } else {
                deque.push(root);
                root = root.right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().postorderTraversal(TreeNode.build(1,null,2,3)).equals(List.of(3,2,1));
        assert new Solution().postorderTraversal(TreeNode.build()).equals(List.of());
        assert new Solution().postorderTraversal(TreeNode.build(1)).equals(List.of(1));
    }

}
