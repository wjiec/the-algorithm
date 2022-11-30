package problem.p240searcha2dmatrixii;

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

    /**
     * 选左上角，往右走和往下走都增大，不能选
     * 选右下角，往上走和往左走都减小，不能选
     * 选左下角，往右走增大，往上走减小，可选
     * 选右上角，往下走增大，往左走减小，可选
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) row--;
            else if (matrix[row][col] < target) col++;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().searchMatrix(new int[][]{
            {1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}
        }, 5);

        assert !new Solution().searchMatrix(new int[][]{
            {1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}
        }, 20);
    }

}
