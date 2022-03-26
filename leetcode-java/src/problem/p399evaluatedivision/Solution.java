package problem.p399evaluatedivision;

import common.PrettyPrinter;
import common.TODO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 399. Evaluate Division
 *
 * https://leetcode-cn.com/problems/evaluate-division/
 *
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 *
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
 * where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result
 * in division by zero and that there is no contradiction.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        private final double[] weight;

        public UnionFind(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        public void union(int x, int y, double v) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;

            parent[rootX] = rootY;
            weight[rootX] = weight[y] * v / weight[x];
        }

        public int find(int i) {
            if (parent[i] != i) {
                int origin = parent[i];
                parent[i] = find(parent[i]);
                weight[i] *= weight[origin];
            }
            return parent[i];
        }

        public double isConnect(int x, int y) {
            if (find(x) == find(y)) return weight[x] / weight[y];
            return -1;
        }
    }

    @TODO(url = "https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/")
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UnionFind unionFind = new UnionFind(2 * equations.size());
        Map<String, Integer> map = new HashMap<>(2 * equations.size());
        for (int i = 0, id = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);

            if (!map.containsKey(a)) map.put(a, id++);
            if (!map.containsKey(b)) map.put(b, id++);

            unionFind.union(map.get(a), map.get(b), values[i]);
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < ans.length; i++) {
            Integer id1 = map.get(queries.get(i).get(0)), id2 = map.get(queries.get(i).get(1));
            if (id1 == null || id2 == null) ans[i] = -1;
            else ans[i] = unionFind.isConnect(id1, id2);
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().calcEquation(
            List.of(List.of("x1","x2"), List.of("x2","x3"), List.of("x3","x4"), List.of("x4","x5")),
            new double[]{3.0,4.0,5.0,6.0},
            List.of(List.of("x1","x5"), List.of("x5","x2"), List.of("x2","x4"), List.of("x2","x2"), List.of("x2","x9"), List.of("x9","x9"))
        ));

        PrettyPrinter.println(new Solution().calcEquation(
            List.of(List.of("a","b"), List.of("b","c")),
            new double[]{2.0,3.0},
            List.of(List.of("a","c"), List.of("b","a"), List.of("a","e"), List.of("a","a"), List.of("x","x"))
        ));

        PrettyPrinter.println(new Solution().calcEquation(
            List.of(List.of("a","b"), List.of("b","c"), List.of("bc","cd")),
            new double[]{1.5,2.5,5.0},
            List.of(List.of("a","c"), List.of("c","b"), List.of("bc","cd"), List.of("cd","bc"))
        ));

        PrettyPrinter.println(new Solution().calcEquation(
            List.of(List.of("a","b")),
            new double[]{0.5},
            List.of(List.of("a","b"), List.of("b","a"), List.of("a","c"), List.of("x","y"), List.of("x","x"))
        ));
    }

}
