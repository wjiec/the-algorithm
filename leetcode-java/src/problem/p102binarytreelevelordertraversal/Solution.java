package problem.p102binarytreelevelordertraversal;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 */

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return List.of();

        Queue<TreeNode> curr = new ArrayDeque<>(), next = new ArrayDeque<>();
        curr.add(root);

        List<List<Integer>> ans = new ArrayList<>();
        do {
            List<Integer> level = new ArrayList<>();
            while (!curr.isEmpty()) {
                var node = curr.remove();
                level.add(node.val);

                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }

            ans.add(level);
            curr = next;
            next = new ArrayDeque<>();
        } while (!curr.isEmpty());

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().levelOrder(TreeNode.build(3,9,20,null,null,15,7)));
    }

}
