package problem.p1202smalleststringwithswaps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1202. Smallest String With Swaps
 *
 * https://leetcode.cn/problems/smallest-string-with-swaps/
 *
 * You are given a string s, and an array of pairs of indices in the string pairs
 * where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 */

public class Solution {

    private static class UnionFind {
        private final int[] rank;
        private final int[] parent;
        public UnionFind(int n) {
            rank = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public void union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) return;

            if (rank[fx] <= rank[fy]) {
                parent[fx] = fy;
                if (rank[fx] == rank[fy]) rank[fy]++;
            } else parent[fy] = fx;
        }
        public int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) return s;

        UnionFind uf = new UnionFind(s.length());
        for (var pair : pairs) uf.union(pair.get(0), pair.get(1));

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(uf.find(i), v -> new PriorityQueue<>())
                .add(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int root = uf.find(i);
            sb.append(map.get(root).remove());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().smallestStringWithSwaps("dcab",
            List.of(List.of(0, 3), List.of(1, 2))
        ).equals("bacd");

        assert new Solution().smallestStringWithSwaps("dcab",
            List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2))
        ).equals("abcd");

        assert new Solution().smallestStringWithSwaps("cba",
            List.of(List.of(0, 1), List.of(1, 2))
        ).equals("abc");
    }

}
