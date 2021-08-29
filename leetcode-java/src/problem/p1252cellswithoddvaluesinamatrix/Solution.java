package problem.p1252cellswithoddvaluesinamatrix;

/**
 * 1252. Cells with Odd Values in a Matrix
 *
 * https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix/
 *
 * There is an m x n matrix that is initialized to all 0's.
 *
 * There is also a 2D array indices where each indices[i] = [ri, ci]
 * represents a 0-indexed location to perform some increment operations on the matrix.
 *
 * For each location indices[i], do both of the following:
 *
 * Increment all the cells on row ri.
 * Increment all the cells on column ci.
 *
 * Given m, n, and indices, return the number of odd-valued cells in the matrix
 * after applying the increment to all locations in indices.
 */

public class Solution {

    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m], col = new int[n];
        for (var index : indices) {
            row[index[0]]++;
            col[index[1]]++;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((row[i] + col[j]) % 2 == 1)
                    ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().oddCells(2, 3, new int[][]{{0,1}, {1,1}}) == 6;
        assert new Solution().oddCells(2, 2, new int[][]{{1,1}, {0,0}}) == 0;
    }

}
