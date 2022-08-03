package problem.p1382balanceabinarysearchtree;

import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382. Balance a Binary Search Tree
 *
 * https://leetcode.cn/problems/balance-a-binary-search-tree/
 *
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values.
 * If there is more than one answer, return any of them.
 *
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 */

public class Solution {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        return build(sorted, 0, sorted.size());
    }

    private TreeNode build(List<Integer> sorted, int l, int r) {
        if (l >= r) return null;

        int mid = l + (r - l) / 2;
        TreeNode node = new TreeNode(sorted.get(mid));
        node.left = build(sorted, l, mid);
        node.right = build(sorted, mid + 1, r);

        return node;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node.left != null) inorder(node.left, list);
        list.add(node.val);
        if (node.right != null) inorder(node.right, list);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().balanceBST(TreeNode.build(1,null,2,null,3,null,4,null,null)));
        PrettyPrinter.println(new Solution().balanceBST(TreeNode.build(2,1,3)));
    }

}
