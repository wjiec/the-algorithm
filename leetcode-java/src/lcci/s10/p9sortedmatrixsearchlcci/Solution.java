package lcci.s10.p9sortedmatrixsearchlcci;

/**
 * 面试题 10.09. 排序矩阵查找
 *
 * https://leetcode.cn/problems/sorted-matrix-search-lcci/
 *
 * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;

        int x = m - 1, y = 0;
        while (x >= 0 && y < n) {
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] > target) x--;
            else y++;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().searchMatrix(new int[][]{
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        }, 5);

        assert !new Solution().searchMatrix(new int[][]{
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        }, 20);
    }

}
