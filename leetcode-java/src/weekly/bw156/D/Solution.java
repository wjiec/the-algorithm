package weekly.bw156.D;

/**
 * Q4. Subtree Inversion Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-156/problems/subtree-inversion-sum/
 *
 * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1.
 *
 * The tree is represented by a 2D integer array edges of length n - 1, where
 * edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
 *
 * You are also given an integer array nums of length n, where nums[i] represents
 * the value at node i, and an integer k.
 *
 * You may perform inversion operations on a subset of nodes subject to the following rules:
 *
 * Subtree Inversion Operation:
 *
 * When you invert a node, every value in the subtree rooted at that node is multiplied by -1.
 *
 * Distance Constraint on Inversions:
 *
 * You may only invert a node if it is "sufficiently far" from any other inverted node.
 *
 * Specifically, if you invert two nodes a and b such that one is an ancestor of the
 * other (i.e., if LCA(a, b) = a or LCA(a, b) = b), then the distance (the number of
 * edges on the unique path between them) must be at least k.
 *
 * Return the maximum possible sum of the tree's node values after applying inversion operations.
 */

public class Solution {

    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        return 1;
    }

    public static void main(String[] args) {
    }

}
