package weekly.w343.B;

/**
 * 2661. First Completely Painted Row or Column
 *
 * https://leetcode.cn/contest/weekly-contest-343/problems/first-completely-painted-row-or-column/
 *
 * You are given a 0-indexed integer array arr, and an m x n integer matrix mat.
 * arr and mat both contain all the integers in the range [1, m * n].
 *
 * Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
 *
 * Return the smallest index i at which either a row or a column will be completely painted in mat.
 */

public class Solution {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int[][] map = new int[arr.length + 1][2];

        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[mat[i][j]][0] = i;
                map[mat[i][j]][1] = j;
            }
        }

        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int[] curr = map[arr[i]];
            if (++rows[curr[0]] == n) return i;
            if (++cols[curr[1]] == m) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().firstCompleteIndex(new int[]{1,4,5,2,6,3}, new int[][]{
            {4,3,5},
            {1,2,6},
        }) == 1;
    }

}
