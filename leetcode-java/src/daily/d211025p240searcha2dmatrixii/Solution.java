package daily.d211025p240searcha2dmatrixii;

/**
 * 240. Search a 2D Matrix II
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
 *
 * The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int x = m - 1; x >= 0; x--) {
            if (matrix[x][0] <= target) {
                for (int y = 0; y < n; y++) {
                    if (matrix[x][y] == target) return true;
                    else if (matrix[x][y] > target) break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().searchMatrix(new int[][]{
            {1,4,7,11,15},
            {2,5,8,12,19},
            {3,6,9,16,22},
            {10,13,14,17,24},
            {18,21,23,26,30}
        }, 5);

        assert !new Solution().searchMatrix(new int[][]{
            {1,4,7,11,15},
            {2,5,8,12,19},
            {3,6,9,16,22},
            {10,13,14,17,24},
            {18,21,23,26,30}
        }, 20);
    }

}
