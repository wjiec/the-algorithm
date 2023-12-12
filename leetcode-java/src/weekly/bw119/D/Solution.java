package weekly.bw119.D;

import java.util.*;

/**
 * 2959. Number of Possible Sets of Closing Branches
 *
 * https://leetcode.cn/contest/biweekly-contest-119/problems/number-of-possible-sets-of-closing-branches/
 *
 * There is a company with n branches across the country, some of which are connected by roads.
 * Initially, all branches are reachable from each other by traveling some roads.
 *
 * The company has realized that they are spending an excessive amount of time traveling
 * between their branches. As a result, they have decided to close down some of these
 * branches (possibly none). However, they want to ensure that the remaining branches
 * have a distance of at most maxDistance from each other.
 *
 * The distance between two branches is the minimum total traveled length needed to
 * reach one branch from another.
 *
 * You are given integers n, maxDistance, and a 0-indexed 2D array roads,
 * where roads[i] = [ui, vi, wi] represents the undirected road between
 * branches ui and vi with length wi.
 *
 * Return the number of possible sets of closing branches, so that any branch
 * has a distance of at most maxDistance from any other.
 *
 * Note that, after closing a branch, the company will no longer have access
 * to any roads connected to it.
 *
 * Note that, multiple roads are allowed.
 */

public class Solution {

    private int n = 0, maxDistance = 0;
    private final Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        this.n = n; this.maxDistance = maxDistance;
        for (var road : roads) {
            map.computeIfAbsent(road[0], v -> new HashMap<>()).merge(road[1], road[2], Integer::min);
            map.computeIfAbsent(road[1], v -> new HashMap<>()).merge(road[0], road[2], Integer::min);
        }

        int ans = 0, mask = 1 << (n + 1) - 1;
        for (int c = 1; c <= n; c++) {
            for (int s = 1; s <= mask; s++) {
                if (Integer.bitCount(s) == c) {
                    if (check(s)) ans++;
                }
            }
        }
        return ans;
    }

    private boolean check(int state) {
        for (int a = 0; a < n; a++) {
            if (isClosed(state, a)) continue;
            for (int b = a + 1; b < n; b++) {
                if (isClosed(state, b)) continue;
                if (walk(a, b, state) > maxDistance) return false;
            }
        }
        return true;
    }

    private int walk(int a, int b, int state) {
        int[] visited = new int[n];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{a, 0});
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[a] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            if (map.containsKey(curr[0])) {
                for (var next : map.get(curr[0]).entrySet()) {
                    if (isClosed(state, next.getKey())) continue;

                    int nextValue = curr[1] + next.getValue();
                    if (visited[next.getKey()] <= nextValue) continue;

                    visited[next.getKey()] = nextValue;
                    queue.add(new int[]{next.getKey(), nextValue});
                }
            }
        }

        return visited[b];
    }

    private boolean isClosed(int state, int node) {
        return (state & (1 << node)) != 0;
    }

    public static void main(String[] args) {
    }

}
