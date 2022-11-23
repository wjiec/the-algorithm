package offer2.p118;

import ability.Ability;
import common.Checker;

/**
 * 剑指 Offer II 118. 多余的边
 *
 * https://leetcode.cn/problems/7LpjUW/
 *
 * 树可以看成是一个连通且 无环 的 无向 图。
 *
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且
 * 这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示
 * 图中在 ai 和 bi 之间存在一条边。
 *
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。
 */

public class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        Ability.UnionFind uf = new Ability.UnionFind(edges.length + 1);
        for (var edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) return edge;
            uf.union(edge[0], edge[1]);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}}), new int[]{2, 3});
        assert Checker.check(new Solution().findRedundantConnection(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}}), new int[]{1, 4});
    }

}
