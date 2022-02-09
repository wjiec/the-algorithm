package problem.p74searcha2dmatrix;

/**
 * 74. Search a 2D Matrix
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 *
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int a = m - 1, b = 0; a >= 0 && b < n; ) {
            if (matrix[a][b] == target) return true;
            if (matrix[a][b] < target) b++;
            else a--;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().searchMatrix(new int[][]{
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,60}
        }, 3);

        assert !new Solution().searchMatrix(new int[][]{
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,60}
        }, 13);
    }

}
