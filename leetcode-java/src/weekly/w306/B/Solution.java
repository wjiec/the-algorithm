package weekly.w306.B;

/**
 * 6149. Node With Highest Edge Score
 *
 * https://leetcode.cn/contest/weekly-contest-306/problems/node-with-highest-edge-score/
 *
 * You are given a directed graph with n nodes labeled from 0 to n - 1, where
 * each node has exactly one outgoing edge.
 *
 * The graph is represented by a given 0-indexed integer array edges of length n, where
 * edges[i] indicates that there is a directed edge from node i to node edges[i].
 *
 * The edge score of a node i is defined as the sum of the labels of all the nodes that
 * have an edge pointing to i.
 *
 * Return the node with the highest edge score. If multiple nodes have the same
 * edge score, return the node with the smallest index.
 */

public class Solution {

    public int edgeScore(int[] edges) {
        long[] sum = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            sum[edges[i]] += i;
        }

        int ans = 0; long max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > max) {
                max = sum[i];
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
