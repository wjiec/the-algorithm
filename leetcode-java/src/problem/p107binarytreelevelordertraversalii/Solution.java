package problem.p107binarytreelevelordertraversalii;

import common.TreeNode;

import java.util.*;

/**
 * 107. Binary Tree Level Order Traversal II
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 */

public class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> nodes = new ArrayDeque<>();
        for (nodes.add(root); !nodes.isEmpty(); ) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0, l = nodes.size(); i < l; i++) {
                TreeNode node = nodes.remove(); list.add(node.val);
                if (node.left != null) nodes.add(node.left);
                if (node.right != null) nodes.add(node.right);
            }
            ans.add(list);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().levelOrderBottom(TreeNode.build(3,9,20,null,null,15,7)));
        System.out.println(new Solution().levelOrderBottom(TreeNode.build(1)));
        System.out.println(new Solution().levelOrderBottom(TreeNode.build()));
    }

}
