package problem.p2509cyclelengthqueriesinatree;

import common.Checker;

import java.util.Arrays;

/**
 * 2509. Cycle Length Queries in a Tree
 *
 * https://leetcode.cn/problems/cycle-length-queries-in-a-tree/
 *
 * You are given an integer n. There is a complete binary tree with 2n - 1 nodes.
 * The root of that tree is the node with the value 1, and every node with a value
 * val in the range [1, 2n - 1 - 1] has two children where:
 *
 * The left node has the value 2 * val, and
 * The right node has the value 2 * val + 1.
 *
 * You are also given a 2D integer array queries of length m, where queries[i] = [ai, bi].
 * For each query, solve the following problem:
 *
 * Add an edge between the nodes with values ai and bi.
 * Find the length of the cycle in the graph.
 * Remove the added edge between nodes with values ai and bi.
 *
 * Note that:
 *
 * A cycle is a path that starts and ends at the same node, and each edge in the path is visited only once.
 * The length of a cycle is the number of edges visited in the cycle.
 * There could be multiple edges between two nodes in the tree after adding the edge of the query.
 *
 * Return an array answer of length m where answer[i] is the answer to the ith query.
 */

public class Solution {

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] ans = new int[queries.length];
        Arrays.fill(ans, 1);

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0], b = queries[i][1];
            while (a != b) {
                if (a > b) a /= 2;
                else b /= 2;
                ans[i]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().cycleLengthQueries(3, new int[][]{{5,3},{4,7},{2,3}}), new int[]{4,5,3});
        assert Checker.check(new Solution().cycleLengthQueries(2, new int[][]{{1,2}}), new int[]{2});
    }

}
