package daily.d221214p1697checkingexistenceofedgelengthlimitedpaths;

import ability.Ability;
import common.Checker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1697. Checking Existence of Edge Length Limited Paths
 *
 * https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths/
 *
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi]
 * denotes an edge between nodes ui and vi with distance disi.
 *
 * Note that there may be multiple edges between two nodes.
 *
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine
 * for each queries[j] whether there is a path between pj and qj such that each edge on the
 * path has a distance strictly less than limitj .
 *
 * Return a boolean array answer, where answer.length == queries.length and the jth value of
 * answer is true if there is a path for queries[j] is true, and false otherwise.
 */

public class Solution {

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // 所有的边按照距离从小到大进行排序
        Arrays.sort(edgeList, Comparator.comparingInt(v -> v[2]));

        // 对所有的查询根据 limit 进行从小到大排序
        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) index[i] = i;
        Arrays.sort(index, Comparator.comparingInt(i -> queries[i][2]));

        // 使用并查集统计当前连通图中的边的最大距离
        boolean[] ans = new boolean[queries.length];
        Ability.UnionFind uf = new Ability.UnionFind(n);
        for (int ii = 0, ei = 0; ii < queries.length; ii++) {
            int qi = index[ii];
            while (ei < edgeList.length && edgeList[ei][2] < queries[qi][2]) {
                uf.union(edgeList[ei][0], edgeList[ei][1]); ei++;
            }

            ans[qi] = uf.find(queries[qi][0]) == uf.find(queries[qi][1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().distanceLimitedPathsExist(3, new int[][]{
            {0,1,2},{1,2,4},{2,0,8},{1,0,16}
        }, new int[][]{{0,1,2},{0,2,5}}), new boolean[]{false, true});

        assert Checker.check(new Solution().distanceLimitedPathsExist(5, new int[][]{
            {0,1,10},{1,2,5},{2,3,9},{3,4,13}
        }, new int[][]{{0,4,14},{1,4,13}}), new boolean[]{true, false});
    }

}
