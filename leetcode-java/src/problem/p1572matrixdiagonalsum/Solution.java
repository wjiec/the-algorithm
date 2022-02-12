package problem.p1572matrixdiagonalsum;

/**
 * 1572. Matrix Diagonal Sum
 *
 * https://leetcode-cn.com/problems/matrix-diagonal-sum/
 *
 * Given a square matrix mat, return the sum of the matrix diagonals.
 *
 * Only include the sum of all the elements on the primary diagonal and all the elements
 * on the secondary diagonal that are not part of the primary diagonal.
 */

public class Solution {

    public int diagonalSum(int[][] mat) {
        int ans = 0, l = mat.length, x = l - 1;
        for (int i = 0; i < mat.length; i++, x--) {
            ans += mat[i][i];
            if (x != i) ans += mat[i][x];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().diagonalSum(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }) == 25;

        assert new Solution().diagonalSum(new int[][]{
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
        }) == 8;

        assert new Solution().diagonalSum(new int[][]{
            {5},
        }) == 5;
    }

}
