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

    public static void main(String[] args) {
        assert new Solution().findMaxPathScore(new int[][]{}, new boolean[]{true, true, true}, 10) == -1;
        assert new Solution().findMaxPathScore(new int[][]{{0,1,5},{1,3,10},{0,2,3},{2,3,4}}, new boolean[]{true, true, true, true}, 10) == 3;
        assert new Solution().findMaxPathScore(new int[][]{{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}}, new boolean[]{true, true, true, false, true}, 12) == 6;
    }

}
