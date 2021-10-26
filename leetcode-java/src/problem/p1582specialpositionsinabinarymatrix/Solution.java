package problem.p1582specialpositionsinabinarymatrix;

/**
 * 1582. Special Positions in a Binary Matrix
 *
 * https://leetcode-cn.com/problems/special-positions-in-a-binary-matrix/
 *
 * Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.
 *
 * A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0
 * (rows and columns are 0-indexed).
 */

public class Solution {

    public int numSpecial(int[][] mat) {
        int[] rows = new int[mat.length], cols = new int[mat[0].length];
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat[0].length; y++) {
                if (mat[x][y] == 1) {
                    rows[x]++;
                    cols[y]++;
                }
            }
        }

        int ans = 0;
        for (int x = 0; x < rows.length; x++) {
            if (rows[x] == 1) {
                for (int y = 0; y < cols.length; y++) {
                    if (cols[y] == 1 && mat[x][y] == 1) ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSpecial(new int[][]{
            {1,0,0},
            {0,0,1},
            {1,0,0}
        }) == 1;

        assert new Solution().numSpecial(new int[][]{
            {1,0,0},
            {0,1,0},
            {0,0,1}
        }) == 3;

        assert new Solution().numSpecial(new int[][]{
            {0,0,0,1},
            {1,0,0,0},
            {0,1,1,0},
            {0,0,0,0}
        }) == 2;

        assert new Solution().numSpecial(new int[][]{
            {0,0,0,0,0},
            {1,0,0,0,0},
            {0,1,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,1}
        }) == 3;
    }

}
