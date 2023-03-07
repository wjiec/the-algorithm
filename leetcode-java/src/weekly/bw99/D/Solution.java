package weekly.bw99.D;

import java.util.*;

/**
 * 2581. Count Number of Possible Root Nodes
 *
 * https://leetcode.cn/problems/count-number-of-possible-root-nodes/description/
 *
 * Alice has an undirected tree with n nodes labeled from 0 to n - 1. The tree is represented
 * as a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the tree.
 *
 * Alice wants Bob to find the root of the tree. She allows Bob to make several guesses
 * about her tree. In one guess, he does the following:
 *
 * Chooses two distinct integers u and v such that there exists an edge [u, v] in the tree.
 * He tells Alice that u is the parent of v in the tree.
 *
 * Bob's guesses are represented by a 2D integer array guesses where guesses[j] = [uj, vj]
 * indicates Bob guessed uj to be the parent of vj.
 *
 * Alice being lazy, does not reply to each of Bob's guesses, but just says that
 * at least k of his guesses are true.
 *
 * Given the 2D integer arrays edges, guesses and the integer k, return the number
 * of possible nodes that can be the root of Alice's tree.
 *
 * If there is no such tree, return 0.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private int k = 0, ans = 0;
    private List<Integer>[] graph;
    private final Set<Long> matches = new HashSet<>();

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        graph = new List[edges.length + 1];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (var edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        for (var guess : guesses) matches.add(hash(guess[0], guess[1]));

        dfs(0, -1);
        reroot(0, -1, -this.k);

        return ans;
    }

    private void dfs(int curr, int parent) {
        for (var next : graph[curr]) {
            if (next != parent) {
                if (matches.contains(hash(curr, next))) k--;
                dfs(next, curr);
            }
        }
    }

    private void reroot(int curr, int parent, int c) {
        if (c >= 0) ans++;
        for (var next : graph[curr]) {
            if (next != parent) {
                int x = c;
                if (matches.contains(hash(curr, next))) x--; // 原本对的
                if (matches.contains(hash(next, curr))) x++; // 原本错的
                reroot(next, curr, x);
            }
        }
    }

    private long hash(long parent, long children) { return (parent << 32) | children; }

    public static void main(String[] args) {
        assert new Solution().rootCount(new int[][]{
            {0,1},{2,0},{0,3},{4,2},{3,5},{6,0},{1,7},{2,8},{2,9},{4,10},{9,11},{3,12},{13,8},{14,9},{15,9},{10,16}
        }, new int[][]{{8,2},{12,3},{0,1},{16,10}}, 2) == 4;

        assert new Solution().rootCount(new int[][]{{0,1},{1,2},{1,3},{4,2}}, new int[][]{{1,3},{0,1},{1,0},{2,4}}, 3) == 3;
        assert new Solution().rootCount(new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{1,0},{3,4},{2,1},{3,2}}, 1) == 5;
    }

}
