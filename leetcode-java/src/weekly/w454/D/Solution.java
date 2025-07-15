package weekly.w454.D;

import common.Checker;

import java.util.*;

/**
 * Q4. Find Weighted Median Node in Tree
 *
 * https://leetcode.cn/contest/weekly-contest-454/problems/find-weighted-median-node-in-tree/
 *
 * You are given an integer n and an undirected, weighted tree rooted at node 0 with n nodes
 * numbered from 0 to n - 1. This is represented by a 2D array edges of length n - 1, where
 * edges[i] = [ui, vi, wi] indicates an edge from node ui to vi with weight wi.
 *
 * The weighted median node is defined as the first node x on the path from ui to vi such that
 * the sum of edge weights from ui to x is greater than or equal to half of the total path weight.
 *
 * You are given a 2D integer array queries. For each queries[j] = [uj, vj], determine the
 * weighted median node along the path from uj to vj.
 *
 * Return an array ans, where ans[j] is the node index of the weighted median for queries[j].
 */

public class Solution {

    private Map<Integer, Integer>[] g = null;

    /** @noinspection unchecked*/
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        g = new Map[n]; Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].put(edge[1], edge[2]);
            g[edge[1]].put(edge[0], edge[2]);
        }

        // 找到每个查询中 u -> v 的中位节点 x, 也就是 sum(u -> x) >= sum(u -> v) / 2
        //  - 遍历的过程中使用快慢指针的形式来维护中间节点, 一次遍历
        //  - 对于反向的遍历节点
        //      - 如果恰好等于 sum(u -> v) / 2, 那么就是当前慢节点
        //      - 如果大于等于 sum(u -> v) / 2, 那么就是上一个节点
        int[] ans = new int[queries.length];
        Map<Integer, List<int[]>> start = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == queries[i][1]) {
                ans[i] = queries[i][0];
                continue;
            }

            start.computeIfAbsent(queries[i][0], k -> new ArrayList<>())
                .add(new int[]{i, queries[i][1]});
        }

        for (var kv : start.entrySet()) {
            path.clear(); seen.clear(); coll.clear();
            for (var v : kv.getValue()) {
                seen.add(v[1]);
            }

            dfs(kv.getKey(), -1, 0, 0);
            for (var v : kv.getValue()) {
                ans[v[0]] = coll.get(v[1]);
            }
        }

        return ans;
    }

    // [u, sum] 走到 u 的总和是 sum
    private final List<int[]> path = new ArrayList<>();
    private final Set<Integer> seen = new HashSet<>();
    private final Map<Integer, Integer> coll = new HashMap<>();

    private void dfs(int curr, int parent, int sum, int slow) {
        // 到达 curr 的时候, 中位节点在 slow
        if (!path.isEmpty()) {
            if (seen.remove(curr)) {
                coll.put(curr, path.get(slow)[0]);
                if (seen.isEmpty()) return;
            }
        }

        for (var next : g[curr].entrySet()) {
            if (next.getKey() == parent) continue;

            int newSum = sum + next.getValue();
            path.add(new int[]{next.getKey(), newSum});

            int newSlow = slow;
            // 根据新的总和移动到合适的位置
            while (path.get(newSlow)[1] * 2 < newSum) newSlow++;
            dfs(next.getKey(), curr, newSum, newSlow);

            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findMedian(4, new int[][]{{0,1,3},{1,2,3},{0,3,5}}, new int[][]{{0,0},{3,2}}), new int[]{0,1});
        assert Checker.check(new Solution().findMedian(2, new int[][]{{0,1,2}}, new int[][]{{1,1}}), new int[]{1});

        assert Checker.check(new Solution().findMedian(2, new int[][]{{0,1,7}}, new int[][]{{1,0},{0,1}}), new int[]{0,1});
        assert Checker.check(new Solution().findMedian(3, new int[][]{{0,1,2},{2,0,4}}, new int[][]{{0,1},{2,0},{1,2}}), new int[]{1,0,2});
        assert Checker.check(new Solution().findMedian(5, new int[][]{{0,1,2},{0,2,5},{1,3,1},{2,4,3}}, new int[][]{{3,4},{1,2}}), new int[]{2,2});
    }

}
