package weekly.w426.C;

import common.Checker;
import common.PrettyPrinter;

import java.util.*;

/** @noinspection DuplicatedCode*/
public class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        TreeMap<Integer, Integer> m2 = new TreeMap<>(findTarget(edges2));
        int prev = 0;
        for (var e : m2.entrySet()) m2.put(e.getKey(), prev += e.getValue());

        int[] ans = new int[edges1.length + 1];
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (var edge : edges1) {
            g.computeIfAbsent(edge[0], kk -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], kk -> new HashSet<>()).add(edge[0]);
        }

        memo.clear();
        dfs(g, 0, -1, ans, m2, k, 1);

        return ans;
    }

    private void dfs(Map<Integer, Set<Integer>> g, int node, int parent, int[] ans, TreeMap<Integer, Integer> acc, int k, int depth) {
        for (var next : g.get(node)) {
            if (next == parent) continue;

            TreeMap<Integer, Integer> sum = new TreeMap<>();
            var curr = findTarget(g, node, parent);
            var bottom = findTarget(g, next, node);
            // 从父节点过来的都需要加 depth 条边, 同时需要扣除当前节点多加的
            for (var e : bottom.entrySet()) curr.merge(e.getKey() + 1, -1, Integer::sum);
            for (var e : curr.entrySet()) sum.merge(e.getKey() + 1, e.getValue(), Integer::max);

            PrettyPrinter.println(next);
            PrettyPrinter.println(sum);

            int prev = 0;
            for (var e : sum.entrySet()) sum.put(e.getKey(), prev += e.getValue());

            ans[next] = sum.floorEntry(k).getValue() + acc.floorEntry(k - 1).getValue() + 1;
            dfs(g, next, node, ans, acc, k, depth + 1);
        }
    }

    // {k: v} 表示从某一个节点出发边数为 k 的最多节点数有 v 个
    private Map<Integer, Integer> findTarget(int[][] edges) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        Map<Integer, Integer> ans = new HashMap<>(findTarget(g, 0, -1));
        dfs(ans, g, 0, -1, 1);
        return ans;
    }

    private void dfs(Map<Integer, Integer> ans, Map<Integer, Set<Integer>> g, int node, int parent, int depth) {
        for (var next : g.get(node)) {
            if (next == parent) continue;

            var curr = findTarget(g, node, parent);
            var bottom = findTarget(g, next, node);
            // 从父节点过来的都需要加 depth 条边, 同时需要扣除当前节点多加的
            for (var e : bottom.entrySet()) curr.merge(e.getKey() + 1, -1, Integer::sum);
            for (var e : curr.entrySet()) ans.merge(e.getKey() + 1, e.getValue(), Integer::max);
            dfs(ans, g, next, node, depth + 1);
        }
    }

    private final Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

    // 找到当前子树的的多有边数
    private Map<Integer, Integer> findTarget(Map<Integer, Set<Integer>> g, int node, int parent) {
        if (memo.containsKey(node)) return new HashMap<>(memo.get(node));

        Map<Integer, Integer> ans = new HashMap<>(); ans.put(0, 1);
        for (var next : g.get(node)) {
            if (next == parent) continue;

            for (var e : findTarget(g, next, node).entrySet()) {
                ans.merge(e.getKey() + 1, e.getValue(), Integer::sum);
            }
        }

        memo.put(node, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxTargetNodes(new int[][]{{0,1},{0,2},{2,3},{2,4}}, new int[][]{{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}}, 2), new int[]{9,7,9,8,8});
        assert Checker.check(new Solution().maxTargetNodes(new int[][]{{0,1},{0,2},{0,3},{0,4}}, new int[][]{{0,1},{1,2},{2,3}}, 1), new int[]{6,3,3,3,3});
    }

}
