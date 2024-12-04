package weekly.w426.C;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** @noinspection DuplicatedCode*/
public class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        return new int[0];
    }

    // {u: {n: c}} 表示从 u 出发边数为 n 的节点数有 c 个
    private Map<Integer, Map<Integer, Integer>> findTarget(int[][] edges) {
        memo.clear();
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        Map<Integer, Map<Integer, Integer>> ans = new HashMap<>();
        ans.put(0, findTarget(g, 0, -1));
        return null;
    }

    private final Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

    // 找到当前子树的 {u: {n: c}}
    private Map<Integer, Integer> findTarget(Map<Integer, Set<Integer>> g, int node, int parent) {
        Map<Integer, Integer> ans = new HashMap<>();
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
    }

}
