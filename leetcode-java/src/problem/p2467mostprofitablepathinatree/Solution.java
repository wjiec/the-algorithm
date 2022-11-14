package problem.p2467mostprofitablepathinatree;

import java.util.*;

/**
 * 6240. Most Profitable Path in a Tree
 *
 * https://leetcode.cn/problems/most-profitable-path-in-a-tree/
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0.
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the tree.
 *
 * At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:
 *
 * the price needed to open the gate at node i, if amount[i] is negative, or,
 * the cash reward obtained on opening the gate at node i, otherwise.
 * The game goes on as follows:
 *
 * Initially, Alice is at node 0 and Bob is at node bob.
 * At every second, Alice and Bob each move to an adjacent node. Alice moves towards
 * some leaf node, while Bob moves towards node 0.
 *
 * For every node along their path, Alice and Bob either spend money to open the
 * gate at that node, or accept the reward. Note that:
 *
 * If the gate is already open, no price will be required, nor will there be any cash reward.
 *
 * If Alice and Bob reach the node simultaneously, they share the price/reward for opening
 * the gate there. In other words, if the price to open the gate is c, then both Alice and Bob
 * pay c / 2 each. Similarly, if the reward at the gate is c, both of them receive c / 2 each.
 *
 * If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving.
 * Note that these events are independent of each other.
 *
 * Return the maximum net income Alice can have if she travels towards the optimal leaf node.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        for (var edge : edges) {
            map.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }

        int[] bobWalks = new int[amount.length];
        Arrays.fill(bobWalks, -1); walk(bob, bobWalks);

        // [node, index, score]
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] aliceWalks = new boolean[amount.length];
        queue.add(new int[]{0, 0, amount[0]}); aliceWalks[0] = true;

        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int node = curr[0], idx = curr[1], score = curr[2];
            Set<Integer> neighbors = map.get(node);
            if (neighbors.size() == 1 && node != 0) {
                ans = Math.max(ans, score);
            }

            for (var next : neighbors) {
                if (!aliceWalks[next]) {
                    aliceWalks[next] = true;

                    if (bobWalks[next] == idx + 1) {
                        queue.add(new int[]{next, idx + 1, score + amount[next] / 2});
                    } else if (bobWalks[next] < idx + 1) {
                        queue.add(new int[]{next, idx + 1, score});
                    } else {
                        queue.add(new int[]{next, idx + 1, score + amount[next]});
                    }
                }
            }
        }

        return ans;
    }

    private void walk(int start, int[] visited) {
        // [start, index]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0}); visited[start] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int node = curr[0], index = curr[1];
            if (node == 0) break;

            for (var next : map.get(node)) {
                if (visited[next] == -1) {
                    visited[next] = index + 1;
                    queue.add(new int[]{next, index + 1});
                }
            }
        }

        Queue<Integer> paths = new ArrayDeque<>();
        for (int x = 0; visited[x] != 0; ) {
            paths.add(x);
            int idx = visited[x];
            for (var next : map.get(x)) {
                if (visited[next] == idx - 1) {
                    x = next; break;
                }
            }
        }

        paths.add(start);
        Arrays.fill(visited, Integer.MAX_VALUE);
        do visited[paths.remove()] = paths.size(); while (!paths.isEmpty());
    }

    public static void main(String[] args) {
        assert new Solution().mostProfitablePath(new int[][]{{0,1},{1,2},{1,3},{3,4}}, 3, new int[]{-2,4,2,-4,6}) == 6;
        assert new Solution().mostProfitablePath(new int[][]{{0,1}}, 1, new int[]{-7280,2350}) == -7280;
    }

}
