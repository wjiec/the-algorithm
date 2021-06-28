package offer.p4erweishuzuzhongdechazhaolcof;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) row--;
            else if (matrix[row][col] < target) col++;
            else return true;
        }
        return false;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        for (int i = 0, n = matrix[0].length; i < n; i++) {
            if (matrix[0][i] <= target) {
                for (var rows : matrix) {
                    if (rows[i] == target) return true;
                    else if (rows[i] > target) break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().findNumberIn2DArray(new int[][]{
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        }, 5);

        assert !new Solution().findNumberIn2DArray(new int[][]{
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        }, 20);
    }

}
