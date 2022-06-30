package problem.p1080insufficientnodesinroottoleafpaths;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 1080. Insufficient Nodes in Root to Leaf Paths
 *
 * https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
 *
 * Given the root of a binary tree and an integer limit, delete all insufficient nodes
 * in the tree simultaneously, and return the root of the resulting binary tree.
 *
 * A node is insufficient if every root to leaf path intersecting this node has a sum
 * strictly less than limit.
 *
 * A leaf is a node with no children.
 */

public class Solution {

    private int limit = 0;
    private final Set<TreeNode> preserve = new HashSet<>();

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        this.limit = limit;
        dfs(root, new ArrayDeque<>(), 0);
        return removeDFS(root);
    }

    private void dfs(TreeNode root, Deque<TreeNode> paths, int sum) {
        paths.addLast(root);
        if (root.left == null && root.right == null) {
            if (sum + root.val >= limit) {
                preserve.addAll(paths);
            }
        } else {
            if (root.left != null) dfs(root.left, paths, sum + root.val);
            if (root.right != null) dfs(root.right, paths, sum + root.val);
        }
        paths.removeLast();
    }

    private TreeNode removeDFS(TreeNode node) {
        if (!preserve.contains(node)) return null;
        if (node.left != null) node.left = removeDFS(node.left);
        if (node.right != null) node.right = removeDFS(node.right);
        return node;
    }

    private static class Optimization {
        private record Node(TreeNode node, boolean preserve) {}
        public TreeNode sufficientSubset(TreeNode root, int limit) {
            return subset(root, limit).node;
        }

        private Node subset(TreeNode node, int limit) {
            if (node.left == null && node.right == null) {
                boolean preserve = node.val >= limit;
                return new Node(preserve ? node : null, preserve);
            }

            Node left = null, right = null;
            if (node.left != null) left = subset(node.left, limit - node.val);
            if (node.right != null) right = subset(node.right, limit - node.val);

            node.left = left == null ? null : left.node;
            node.right = right == null ? null : right.node;

            boolean preserve = (left == null ?
                right.preserve :
                    (right == null ?
                        left.preserve :
                            (left.preserve || right.preserve)));
            return new Node(preserve ? node : null, preserve);
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sufficientSubset(TreeNode.build(
            1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14
        ), 1), TreeNode.build(1,2,3,4,null,null,7,8,9,null,14));

        assert Checker.check(new Solution().sufficientSubset(TreeNode.build(
            5,4,8,11,null,17,4,7,1,null,null,5,3
        ), 22), TreeNode.build(5,4,8,11,null,17,4,7,null,null,null,5));

        assert Checker.check(new Solution().sufficientSubset(TreeNode.build(
            5,-6,-6
        ), 0), TreeNode.build());


        assert Checker.check(new Optimization().sufficientSubset(TreeNode.build(
            1,2,-3,-5,null,4,null
        ), -1), TreeNode.build(1,null,-3,4));

        assert Checker.check(new Optimization().sufficientSubset(TreeNode.build(
            1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14
        ), 1), TreeNode.build(1,2,3,4,null,null,7,8,9,null,14));

        assert Checker.check(new Optimization().sufficientSubset(TreeNode.build(
            5,4,8,11,null,17,4,7,1,null,null,5,3
        ), 22), TreeNode.build(5,4,8,11,null,17,4,7,null,null,null,5));

        assert Checker.check(new Optimization().sufficientSubset(TreeNode.build(
            5,-6,-6
        ), 0), TreeNode.build());
    }

}
