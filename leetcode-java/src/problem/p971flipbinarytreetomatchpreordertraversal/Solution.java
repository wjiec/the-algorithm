package problem.p971flipbinarytreetomatchpreordertraversal;

import common.Checker;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 971. Flip Binary Tree To Match Preorder Traversal
 *
 * https://leetcode.cn/problems/flip-binary-tree-to-match-preorder-traversal/
 *
 * You are given the root of a binary tree with n nodes, where each node is uniquely
 * assigned a value from 1 to n. You are also given a sequence of n values
 * voyage, which is the desired pre-order traversal of the binary tree.
 *
 * Any node in the binary tree can be flipped by swapping its left and right subtrees.
 * For example, flipping node 1 will have the following effect:
 *
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 *
 * Return a list of the values of all flipped nodes. You may return the answer in any order.
 * If it is impossible to flip the nodes in the tree to make the pre-order traversal
 * match voyage, return the list [-1].
 */

public class Solution {

    private int index = 0;
    private int[] voyage = null;
    private final List<Integer> flipped = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        dfs(root);
        if (flipped.contains(-1)) return List.of(-1);
        return flipped;
    }

    private void dfs(TreeNode node) {
        if (node != null) {
            if (node.val != voyage[index++]) {
                flipped.clear();
                flipped.add(-1);
                return;
            }

            if (index < voyage.length && node.left != null && node.left.val != voyage[index]) {
                flipped.add(node.val);
                dfs(node.right);
                dfs(node.left);
            } else {
                dfs(node.left);
                dfs(node.right);
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().flipMatchVoyage(TreeNode.build(1,2), new int[]{2,1}), List.of(-1));
        assert Checker.anyOrder(new Solution().flipMatchVoyage(TreeNode.build(1,2,3), new int[]{1,3,2}), List.of(1));
        assert Checker.anyOrder(new Solution().flipMatchVoyage(TreeNode.build(1,2,3), new int[]{1,2,3}), List.of());
    }

}
