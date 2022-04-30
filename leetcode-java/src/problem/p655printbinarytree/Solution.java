package problem.p655printbinarytree;

import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 655. Print Binary Tree
 *
 * https://leetcode-cn.com/problems/print-binary-tree/
 *
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that
 * represents a formatted layout of the tree. The formatted layout matrix
 * should be constructed using the following rules:
 *
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 *
 * The number of columns n should be equal to 2height+1 - 1.
 *
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 *
 * For each node that has been placed in the matrix at position res[r][c], place its left child at
 * res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 *
 * Continue this process until all the nodes in the tree have been placed.
 *
 * Any empty cells should contain the empty string "".
 *
 * Return the constructed matrix res.
 */

public class Solution {

    private int treeHeight = 0;

    public List<List<String>> printTree(TreeNode root) {
        dfs(root, 1);

        String[][] strings = new String[treeHeight][(1 << treeHeight) - 1];
        padding(root, strings, 0, 0, strings[0].length);

        List<List<String>> ans = new ArrayList<>();
        for (var line : strings) {
            List<String> curr = new ArrayList<>();
            for (var s : line) curr.add(s == null ? "" : s);
            ans.add(curr);
        }
        return ans;
    }

    private void padding(TreeNode node, String[][] strings, int h, int l, int r) {
        if (node == null) return;

        int mid = l + (r - l) / 2;
        strings[h][mid] = String.valueOf(node.val);

        padding(node.left, strings, h + 1, l, mid);
        padding(node.right, strings, h + 1, mid + 1, r);
    }

    private void dfs(TreeNode node, int height) {
        if (height > treeHeight) treeHeight = height;

        if (node.left != null) dfs(node.left, height + 1);
        if (node.right != null) dfs(node.right, height + 1);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().printTree(TreeNode.build(1,2)));
        PrettyPrinter.println(new Solution().printTree(TreeNode.build(1,2,3,null,4)));
    }

}
