package problem.p2096stepbystepdirectionsfromabinarytreenodetoanother;

import common.TreeNode;

/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 *
 * https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
 * You are also given an integer startValue representing the value of the start node s, and a different integer
 * destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such
 * path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter
 * indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 *
 * Return the step-by-step directions of the shortest path from node s to node t.
 */

public class Solution {

    private int startValue = 0, destValue = 0;
    private String startPaths = null, destPaths = null;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        this.startValue = startValue; this.destValue = destValue;
        dfs(root, new char[100_001], 0);

        int index = 0, n = Math.min(startPaths.length(), destPaths.length());
        for (; index < n; index++) {
            if (startPaths.charAt(index) != destPaths.charAt(index)) {
                break;
            }
        }

        String a = startPaths.substring(index), b = destPaths.substring(index);
        if (a.length() == 0) return b;
        return "U".repeat(a.length()) + b;
    }

    private void dfs(TreeNode node, char[] chars, int i) {
        if (node.val == startValue) startPaths = new String(chars, 0, i);
        else if (node.val == destValue) destPaths = new String(chars, 0, i);

        if (node.left != null) { chars[i] = 'L'; dfs(node.left, chars, i + 1); }
        if (node.right != null) { chars[i] = 'R'; dfs(node.right, chars, i + 1); }
    }

    public static void main(String[] args) {
        assert new Solution().getDirections(TreeNode.build(5,1,2,3,null,6,4), 3, 6).equals("UURL");
        assert new Solution().getDirections(TreeNode.build(2,1), 2, 1).equals("L");
        assert new Solution().getDirections(TreeNode.build(5,1,2,3,null,6,4), 5, 6).equals("RL");
        assert new Solution().getDirections(TreeNode.build(5,1,2,3,null,6,4), 3, 1).equals("U");
        assert new Solution().getDirections(TreeNode.build(5,1,2,3,null,6,4), 1, 3).equals("L");
    }

}
