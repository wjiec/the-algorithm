package weekly.w370.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 100118. Maximum Score After Applying Operations on a Tree
 *
 * https://leetcode.cn/contest/weekly-contest-370/problems/maximum-score-after-applying-operations-on-a-tree/
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0.
 *
 * You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the tree.
 *
 * You are also given a 0-indexed integer array values of length n, where values[i] is the value
 * associated with the ith node.
 *
 * You start with a score of 0. In one operation, you can:
 *
 * Pick any node i.
 * Add values[i] to your score.
 * Set values[i] to 0.
 * A tree is healthy if the sum of values on the path from the root to any leaf node is different than zero.
 *
 * Return the maximum score you can obtain after performing these operations on the tree
 * any number of times so that it remains healthy.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private final List<Integer>[] neighbor = new List[20001];
    { Arrays.setAll(neighbor, v -> new ArrayList<>()); }

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        for (var edge : edges) {
            neighbor[edge[0]].add(edge[1]);
            neighbor[edge[1]].add(edge[0]);
        }

        this.values = values;
        buildTree(0, -1);

        long sum = 0;
        for (var v : values) sum += v;
        return sum - dfs(0);
    }

    private int[] values = null;

    private long dfs(int curr) {
        if (tree[curr].isEmpty()) return values[curr];

        long sumOfChildren = 0;
        for (var next : tree[curr]) {
            sumOfChildren += dfs(next);
        }
        return Math.min(values[curr], sumOfChildren);
    }

    private final List<Integer>[] tree = new List[20001];
    { Arrays.setAll(tree, v -> new ArrayList<>()); }

    private void buildTree(int curr, int parent) {
        for (var next : neighbor[curr]) {
            if (next != parent) {
                tree[curr].add(next);
                buildTree(next, curr);
            }
        }
    }

    public static void main(String[] args) {
    }

}
