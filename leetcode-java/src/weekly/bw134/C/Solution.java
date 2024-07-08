package weekly.bw134.C;

import java.util.HashSet;
import java.util.Set;

/**
 * 3208. Alternating Groups II
 *
 * https://leetcode.cn/contest/biweekly-contest-134/problems/alternating-groups-ii/
 *
 * There is a circle of red and blue tiles. You are given an array of integers colors and an integer k.
 * The color of tile i is represented by colors[i]:
 *
 * colors[i] == 0 means that tile i is red.
 * colors[i] == 1 means that tile i is blue.
 * An alternating group is every k contiguous tiles in the circle with alternating colors (each tile
 * in the group except the first and last one has a different color from its left and right tiles).
 *
 * Return the number of alternating groups.
 *
 * Note that since colors represents a circle, the first and the last tiles are considered to be next
 * to each other.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent, count;
        public UnionFind(int n) {
            count = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; count[i] = 1;
            }
        }
        public int cnt(int v) { return count[find(v)]; }
        public int find(int v) { return parent[v] == v ? v : (parent[v] = find(parent[v])); }
        public void union(int a, int b) {
            int fa = find(a), fb = find(b);
            if (fa == fb) return;

            parent[fa] = parent[fb];
            count[fb] += count[fa];
        }
    }

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        boolean all = true;
        UnionFind uf = new UnionFind(n);
        for (int i = 1; i <= n; i++) {
            if ((colors[i % n] ^ colors[i - 1]) == 1) {
                uf.union(i % n, i - 1);
            } else all = false;
        }
        if (all) return colors.length;

        int ans = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int fa = uf.find(i);
            if (seen.add(fa)) {
                int c = uf.cnt(i);
                if (c >= k) ans += c - k + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfAlternatingGroups(new int[]{1,0,1,0,1,0}, 3) == 4;
        assert new Solution().numberOfAlternatingGroups(new int[]{1,0,1,0}, 3) == 4;
        assert new Solution().numberOfAlternatingGroups(new int[]{0,1,0,1,0}, 3) == 3;
        assert new Solution().numberOfAlternatingGroups(new int[]{0,1,0,0,1,0,1}, 6) == 2;
        assert new Solution().numberOfAlternatingGroups(new int[]{1,1,0,1}, 4) == 0;
    }

}
