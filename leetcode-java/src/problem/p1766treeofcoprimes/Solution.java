package problem.p1766treeofcoprimes;

import common.Checker;

import java.util.*;

/**
 * 1766. Tree of Coprimes
 *
 * https://leetcode.cn/problems/tree-of-coprimes/description/
 *
 * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes
 * numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and
 * the root of the tree is node 0.
 *
 * To represent this tree, you are given an integer array nums and a 2D array edges.
 * Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents
 * an edge between nodes uj and vj in the tree.
 *
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
 *
 * An ancestor of a node i is any other node on the shortest path from node i to the root.
 * A node is not considered an ancestor of itself.
 *
 * Return an array ans of size n, where ans[i] is the closest ancestor to node i such
 * that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.
 */

@SuppressWarnings({"unchecked", "DataFlowIssue"})
public class Solution {

    private final int INF = 51;
    private final boolean[][] coprime = new boolean[INF][INF];
    {
        for (int i = 1; i < INF; i++) {
            for (int j = 1; j < INF; j++) {
                coprime[i][j] = gcd(i, j) == 1;
            }
        }
    }

    private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    private int[] ans = null;
    private int[] nums = null;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        for (var edge : edges) {
            graph.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }

        this.nums = nums;
        ans = new int[nums.length];
        dfs(0, -1, 0);
        return ans;
    }

    private final Map<Integer, Set<Integer>> graph = new HashMap<>();

    private void dfs(int curr, int parent, int depth) {
        int val = nums[curr];
        ans[curr] = find(val);

        depthValues[depth][0] = curr;
        depthValues[depth][1] = val;
        stacks[val].push(depth);

        if (graph.containsKey(curr)) {
            for (var next : graph.get(curr)) {
                if (next != parent) dfs(next, curr, depth + 1);
            }
        }

        stacks[val].pop();
    }

    private final int[][] depthValues = new int[100_100][2];
    private final Deque<Integer>[] stacks = new Deque[INF];
    { Arrays.setAll(stacks, v -> new ArrayDeque<>()); }

    private int find(int value) {
        int nodeValue = -1, depth = -1;
        for (int i = 0; i < INF; i++) {
            if (!stacks[i].isEmpty() && coprime[i][value]) {
                if (stacks[i].peek() > depth) {
                    nodeValue = i;
                    depth = stacks[i].peek();
                }
            }
        }
        return nodeValue < 0 ? nodeValue : depthValues[depth][0];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getCoprimes(new int[]{2,3,3,2}, new int[][]{{0, 1}, {1,2}, {1,3}}), new int[]{-1,0,0,1});
    }

}
