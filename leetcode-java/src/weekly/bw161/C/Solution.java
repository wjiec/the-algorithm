package weekly.bw161.C;

import java.util.*;

/**
 * Q3. Network Recovery Pathways
 *
 * https://leetcode.cn/contest/biweekly-contest-161/problems/network-recovery-pathways/
 *
 * You are given a directed acyclic graph of n nodes numbered from 0 to n − 1.
 * This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi]
 * indicates a one‑way communication from node ui to node vi with a recovery cost of costi.
 *
 * Some nodes may be offline. You are given a boolean array online where online[i] = true
 * means node i is online. Nodes 0 and n − 1 are always online.
 *
 * A path from 0 to n − 1 is valid if:
 *
 * All intermediate nodes on the path are online.
 * The total recovery cost of all edges on the path does not exceed k.
 * For each valid path, define its score as the minimum edge‑cost along that path.
 *
 * Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths.
 * If no valid path exists, return -1.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int l = -1, r = Integer.MAX_VALUE - 2;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(edges, online, k, mid)) l = mid;
            else r = mid;
        }
        return l;
    }

    /** @noinspection unchecked*/
    private boolean check(int[][] edges, boolean[] online, long k, int min) {
        int n = online.length;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (online[u] && online[v] && w >= min && w <= k) g[u].add(new int[]{v, w});
        }

        // 检查是否有路径能够在不超过 k 的总代价下从 0 走到 n - 1
        //  - 直接使用最短路 Dijkstra 算法尝试是否能走到
        long[] seen = new long[n];
        Arrays.fill(seen, Long.MAX_VALUE); seen[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
        for (pq.add(new long[]{0, 0}); !pq.isEmpty(); ) {
            var curr = pq.remove(); int node = (int) curr[0];
            if (seen[node] < curr[1]) continue;

            seen[node] = curr[1];
            for (var next : g[node]) {
                if (seen[node] + next[1] < seen[next[0]]) {
                    pq.add(new long[]{next[0], seen[next[0]] = seen[node] + next[1]});
                }
            }
        }

        return seen[n - 1] <= k;
    }

    @SuppressWarnings("unchecked")
    private static class Optimization {
        public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
            int maxWeight = 0;
            List<int[]>[] g = new List[online.length];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                if (online[edge[0]] && online[edge[1]]) {
                    maxWeight = Math.max(maxWeight, edge[2]);
                    g[edge[0]].add(new int[]{edge[1], edge[2]});
                }
            }

            int l = -1, r = maxWeight + 1;
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (check(g, k, mid)) l = mid;
                else r = mid;
            }
            return l;
        }

        private final long[] memo = new long[50_001];

        private boolean check(List<int[]>[] g, long k, int min) {
            Arrays.fill(memo, -1);
            return dfs(g, 0, min) <= k;
        }

        private long dfs(List<int[]>[] g, int node, int min) {
            if (node == g.length - 1) return 0;
            if (memo[node] != -1) return memo[node];

            long ans = Long.MAX_VALUE / 2;
            for (var next : g[node]) {
                if (next[1] >= min) {
                    ans = Math.min(ans, dfs(g, next[0], min) + next[1]);
                }
            }
            return memo[node] = ans;
        }
    }

    @SuppressWarnings("unchecked")
    private static class TopologicalSort {
        public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
            int maxWeight = 0, n = online.length;
            int[] inDegree = new int[n];
            List<int[]>[] g = new List[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (online[u] && online[v]) {
                    g[u].add(new int[]{v, w}); inDegree[v]++;
                    // 我们要求的是最大化从 0 -> n-1 的最小边权, 也就是必须要从 0 出发
                    //  - 如果路径中 0 的一条出边是路径中的最大权值 mx, 那么我们要找的答案在 [0, mx] 范围内
                    //  - 如果路径中 0 的一条出边是路径中的最小权值 mi, 那么我们要找的答案就是 mi
                    //  - 如果路径中 0 的一条出边是路径中的中间值 mid, 那么我们要找的答案将会在 [0, mid) 范围内
                    if (u == 0) maxWeight = Math.max(maxWeight, w);
                }
            }

            Queue<Integer> q = new ArrayDeque<>();
            // 我们首先排除所有从 [1, n) 出发的路过的所有边
            for (int i = 1; i < n; i++) if (inDegree[i] == 0) q.add(i);
            while (!q.isEmpty()) {
                for (var next : g[q.remove()]) {
                    int v = next[0];
                    if (--inDegree[v] == 0 && v > 0) q.add(v);
                }
            }

            long[] dp = new long[n];
            int l = -1, r = maxWeight + 1;
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (check(g, k, inDegree.clone(), dp, mid)) l = mid;
                else r = mid;
            }
            return l;
        }

        private boolean check(List<int[]>[] g, long k, int[] inDegree, long[] dp, int min) {
            Arrays.fill(dp, Long.MAX_VALUE >> 1); dp[0] = 0;

            Queue<Integer> q = new ArrayDeque<>(); q.add(0);
            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == g.length - 1) return dp[u] <= k;
                for (var next : g[u]) {
                    int v = next[0], w = next[1];
                    if (w >= min) dp[v] = Math.min(dp[v], dp[u] + w);
                    if (--inDegree[v] == 0) q.add(v);
                }
            }
            // 无法到达 n - 1
            return false;
        }
    }

    public static void main(String[] args) {
        assert new TopologicalSort().findMaxPathScore(new int[][]{}, new boolean[]{true, true, true}, 10) == -1;
        assert new TopologicalSort().findMaxPathScore(new int[][]{{0,1,5},{1,3,10},{0,2,3},{2,3,4}}, new boolean[]{true, true, true, true}, 10) == 3;
        assert new TopologicalSort().findMaxPathScore(new int[][]{{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}}, new boolean[]{true, true, true, false, true}, 12) == 6;

        assert new Optimization().findMaxPathScore(new int[][]{}, new boolean[]{true, true, true}, 10) == -1;
        assert new Optimization().findMaxPathScore(new int[][]{{0,1,5},{1,3,10},{0,2,3},{2,3,4}}, new boolean[]{true, true, true, true}, 10) == 3;
        assert new Optimization().findMaxPathScore(new int[][]{{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}}, new boolean[]{true, true, true, false, true}, 12) == 6;

        assert new Solution().findMaxPathScore(new int[][]{}, new boolean[]{true, true, true}, 10) == -1;
        assert new Solution().findMaxPathScore(new int[][]{{0,1,5},{1,3,10},{0,2,3},{2,3,4}}, new boolean[]{true, true, true, true}, 10) == 3;
        assert new Solution().findMaxPathScore(new int[][]{{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}}, new boolean[]{true, true, true, false, true}, 12) == 6;
    }

}
