package offer2.p116;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 116. 省份数量
 *
 * https://leetcode.cn/problems/bLyHh0/
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
 * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个
 * 城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }

        public int find(int v) {
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }

            return v;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        Set<Integer> province = new HashSet<>();
        for (int i = 0; i < isConnected.length; i++) {
            province.add(uf.find(i));
        }
        return province.size();
    }

    public static void main(String[] args) {
        assert new Solution().findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}) == 2;
        assert new Solution().findCircleNum(new int[][]{{1,0,0},{0,1,0},{0,0,1}}) == 3;
    }

}
