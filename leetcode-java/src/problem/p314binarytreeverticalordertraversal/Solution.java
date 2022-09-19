package problem.p314binarytreeverticalordertraversal;

import common.Checker;
import common.TreeNode;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 *
 * https://leetcode.cn/problems/binary-tree-vertical-order-traversal/
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */

public class Solution {

    private record Node(TreeNode node, int index) {}

    public List<List<Integer>> verticalOrder(TreeNode root) {
        Queue<Node> queue = new ArrayDeque<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        if (root != null) queue.add(new Node(root, 0));
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            map.computeIfAbsent(curr.index, v -> new ArrayList<>())
                .add(curr.node.val);

            if (curr.node.left != null) queue.add(new Node(curr.node.left, curr.index - 1));
            if (curr.node.right != null) queue.add(new Node(curr.node.right, curr.index + 1));
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().verticalOrder(TreeNode.build(3,9,8,4,0,1,7,null,null,null,2,5)), List.of(
            List.of(4),
            List.of(9, 5),
            List.of(3, 0, 1),
            List.of(8, 2),
            List.of(7)
        ));

        assert Checker.check(new Solution().verticalOrder(TreeNode.build(3,9,20,null,null,15,7)), List.of(
            List.of(9),
            List.of(3,15),
            List.of(20),
            List.of(7)
        ));
    }

}
