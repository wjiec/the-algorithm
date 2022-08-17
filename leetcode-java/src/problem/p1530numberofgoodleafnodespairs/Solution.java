package problem.p1530numberofgoodleafnodespairs;

import common.TreeNode;

/**
 * 1530. Number of Good Leaf Nodes Pairs
 *
 * https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs/
 *
 * You are given the root of a binary tree and an integer distance.
 * A pair of two different leaf nodes of a binary tree is said to be good
 * if the length of the shortest path between them is less than or equal to distance.
 *
 * Return the number of good leaf node pairs in the tree.
 */

public class Solution {

    private int ans = 0;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, 0, distance);
        return ans;
    }

    private int[] dfs(TreeNode node, int depth, int distance) {
        int[] count = new int[1025];
        if (node == null) return count;

        if (node.left == null && node.right == null) {
            count[depth]++;
            return count;
        }

        int[] left = dfs(node.left, depth + 1, distance);
        int[] right = dfs(node.right, depth + 1, distance);
        for (int l = 1; l < distance; l++) {
            if (depth + l < count.length) {
                for (int r = 1; l + r <= distance; r++) {
                    if (depth + r < count.length) {
                        ans += left[depth + l] * right[depth + r];
                    }
                }
            }
        }

        for (int i = 0; i < count.length; i++) {
            count[i] += left[i] + right[i];
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(TreeNode.build(1,2,3,null,4), 3) == 1;
        assert new Solution().countPairs(TreeNode.build(1,2,3,4,5,6,7), 3) == 2;
        assert new Solution().countPairs(TreeNode.build(7,1,4,6,null,5,3,null,null,null,null,null,2), 3) == 1;
    }

}
