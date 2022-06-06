package problem.p959regionscutbyslashes;

/**
 * 959. Regions Cut By Slashes
 *
 * https://leetcode.cn/problems/regions-cut-by-slashes/
 *
 * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
 * These characters divide the square into contiguous regions.
 *
 * Given the grid grid represented as a string array, return the number of regions.
 *
 * Note that backslash characters are escaped, so a '\' is represented as '\\'.
 */

public class Solution {

    private static class UnionFind {
        private int count;
        private final int[] parent;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        private int find(int v) {
            while (parent[v] != v) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }
        public void union(int x, int y) {
            int xx = find(x), yy = find(y);
            if (xx == yy) return;

            parent[xx] = yy;
            count--;
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(4 * n * n);
        for (int i = 0; i < n; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                int index = 4 * (i * n + j);
                if (chars[j] == '/') {
                    uf.union(index, index + 3);
                    uf.union(index + 1, index + 2);
                } else if (chars[j] == '\\') {
                    uf.union(index, index + 1);
                    uf.union(index + 2, index + 3);
                } else {
                    uf.union(index, index + 1);
                    uf.union(index + 1, index + 2);
                    uf.union(index + 2, index + 3);
                }

                if (j + 1 < n) uf.union(index + 1, 4 * (i * n + j + 1) + 3);
                if (i + 1 < n) uf.union(index + 2, 4 * ((i + 1) * n + j));
            }
        }
        return uf.count;
    }

    public static void main(String[] args) {
        assert new Solution().regionsBySlashes(new String[]{
            " /",
            "/ "
        }) == 2;

        assert new Solution().regionsBySlashes(new String[]{
            " /",
            "  "
        }) == 1;

        assert new Solution().regionsBySlashes(new String[]{
            "/\\",
            "\\/"
        }) == 5;
    }

}
