package problem.p103binarytreezigzaglevelordertraversal;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */

public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayDeque<TreeNode> nodes = new ArrayDeque<>();
        if (root != null) nodes.add(root);

        List<List<Integer>> ans = new ArrayList<>();
        while (!nodes.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            for (int i = 0, l = nodes.size(); i < l; i++) {
                TreeNode node = nodes.removeFirst();
                line.add(node.val);

                if (node.left != null) nodes.add(node.left);
                if (node.right != null) nodes.add(node.right);
            }
            if (ans.size() % 2 == 1) Collections.reverse(line);
            ans.add(line);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build(3,9,20,null,null,15,7)));
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build(1)));
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build()));
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build(1,2,3,4,5,6,7,8,9,0)));
    }

}
