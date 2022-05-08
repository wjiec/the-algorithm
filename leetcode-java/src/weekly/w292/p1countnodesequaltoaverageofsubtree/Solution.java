package weekly.w292.p1countnodesequaltoaverageofsubtree;

import common.TreeNode;

/**
 * 6057. Count Nodes Equal to Average of Subtree
 *
 * https://leetcode-cn.com/contest/weekly-contest-292/problems/count-nodes-equal-to-average-of-subtree/
 *
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to
 * the average of the values in its subtree.
 *
 * Note:
 *
 * The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 * A subtree of root is a tree consisting of root and all of its descendants.
 */

public class Solution {

    private int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // [sum, count]
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        if (node.left == null && node.right == null) {
            ans++;
            return new int[]{node.val, 1};
        }

        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int[] curr = new int[]{l[0] + r[0] + node.val, l[1] + r[1] + 1};

        if (curr[0] / curr[1] == node.val) ans++;
        return new int[]{l[0] + r[0] + node.val, l[1] + r[1] + 1};
    }

    public static void main(String[] args) {
    }

}
