package problem.p990satisfiabilityofequalityequations;

/**
 * 990. Satisfiability of Equality Equations
 *
 * https://leetcode.cn/problems/satisfiability-of-equality-equations/
 *
 * You are given an array of strings equations that represent relationships between variables where each string
 * equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".
 * Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 *
 * Return true if it is possible to assign integers to variable names so as to satisfy
 * all the given equations, or false otherwise.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent = new int[128];
        public UnionFind() { for (int i = 0; i < parent.length; i++) parent[i] = i; }
        public void union(int x, int y) { parent[find(x)] = find(y); }
        public int find(int v) {
            while (parent[v] != v) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind();
        for (var equation : equations) {
            if (equation.charAt(1) == '=') {
                uf.union(equation.charAt(0), equation.charAt(3));
            }
        }

        for (var equation : equations) {
            if (equation.charAt(1) == '!') {
                if (uf.find(equation.charAt(0)) == uf.find(equation.charAt(3))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().equationsPossible(new String[]{"a==b","b!=a"});
        assert new Solution().equationsPossible(new String[]{"b==a","a==b"});
        assert new Solution().equationsPossible(new String[]{"a!=b","a!=c", "b==c"});
    }

}
