package problem.p947moststonesremovedwithsameroworcolumn;

import java.util.HashMap;
import java.util.Map;

/**
 * 947. Most Stones Removed with Same Row or Column
 *
 * https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/
 *
 * On a 2D plane, we place n stones at some integer coordinate points.
 * Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or
 * the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents
 * the location of the ith stone, return the largest possible
 * number of stones that can be removed.
 */

public class Solution {

    private static class UnionFind {
        private int count = 0;
        private final Map<Integer, Integer> parent = new HashMap<>();
        public int find(int v) {
            if (!parent.containsKey(v)) {
                parent.put(v, v);
                count++;
            }

            if (v != parent.get(v)) {
                parent.put(v, find(parent.get(v)));
            }
            return parent.get(v);
        }
        public void union(int x, int y) {
            int xx = find(x), yy = find(y);
            if (xx == yy) return;

            parent.put(xx, yy);
            count--;
        }
    }

    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind();
        for (var stone : stones) {
            uf.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - uf.count;
    }

    public static void main(String[] args) {
        assert new Solution().removeStones(new int[][]{{0,1},{1,0}}) == 0;
        assert new Solution().removeStones(new int[][]{{0,1},{1,0},{1,1}}) == 2;

        assert new Solution().removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}) == 5;
        assert new Solution().removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}) == 3;
        assert new Solution().removeStones(new int[][]{{0,0}}) == 0;
    }

}
