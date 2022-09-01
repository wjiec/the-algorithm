package problem.p1722minimizehammingdistanceafterswapoperations;

import java.util.HashMap;
import java.util.Map;

/**
 * 1722. Minimize Hamming Distance After Swap Operations
 *
 * https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations/
 *
 * You are given two integer arrays, source and target, both of length n. You are also given an array
 * allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements
 * at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific
 * pair of indices multiple times and in any order.
 *
 * The Hamming distance of two arrays of the same length, source and target, is the number of positions
 * where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where
 * source[i] != target[i] (0-indexed).
 *
 * Return the minimum Hamming distance of source and target after performing any amount of swap
 * operations on array source.
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
            int fx = find(x), fy = find(y);
            parent[fx] = fy;
        }
        public int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int distance = 0, n = source.length;
        for (int i = 0; i < n; i++) {
            if (source[i] != target[i]) {
                distance++;
            }
        }
        if (allowedSwaps.length == 0) return distance;

        // index
        UnionFind uf = new UnionFind(n);
        for (var swap : allowedSwaps) uf.union(swap[0], swap[1]);

        // root => [values]
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(uf.find(i), v -> new HashMap<>())
                .merge(source[i], 1, Integer::sum);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> u = map.get(uf.find(i));
            Integer count = u.get(target[i]);
            if (count != null && count != 0) {
                u.put(target[i], count - 1);
                continue;
            }

            ans++;
        }
        return Math.min(distance, ans);
    }

    public static void main(String[] args) {
        assert new Solution().minimumHammingDistance(new int[]{
            89,43,23,35,73,21,22,97,5,11,81,67,89,93,19,74
        }, new int[]{
            68,43,21,46,41,21,26,5,14,71,4,30,52,2,47,74
        }, new int[][]{
            {12,2},{3,7},{9,15},{5,12},{6,11},{13,15},{4,1},
            {12,0},{9,3},{11,12},{4,11},{7,9},{7,2},{9,13},
            {15,12},{3,12},{12,8},{13,14},{11,2},{8,3},
            {14,10},{0,9},{12,9}
        }) == 12;

        assert new Solution().minimumHammingDistance(new int[]{1,2,3,4}, new int[]{2,1,4,5}, new int[][]{{0,1},{2,3}}) == 1;
        assert new Solution().minimumHammingDistance(new int[]{1,2,3,4}, new int[]{1,3,2,4}, new int[][]{}) == 2;
        assert new Solution().minimumHammingDistance(new int[]{5,1,2,4,3}, new int[]{1,5,4,2,3}, new int[][]{{0,4},{4,2},{1,3},{1,4}}) == 0;
    }

}
