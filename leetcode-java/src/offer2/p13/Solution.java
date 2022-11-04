package offer2.p13;

/**
 * 剑指 Offer II 013. 二维子矩阵的和
 *
 * https://leetcode.cn/problems/O4NDxx/
 *
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上
 * 角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class NumMatrix {
        private final int[][] sum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = matrix[i - 1][j - 1]
                        + sum[i - 1][j] + sum[i][j - 1]
                        - sum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2 + 1][col2 + 1] + sum[row1][col1]
                - sum[row2 + 1][col1] - sum[row1][col2 + 1];
        }
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
            {3,0,1,4,2},
            {5,6,3,2,1},
            {1,2,0,1,5},
            {4,1,0,1,7},
            {1,0,3,0,5}
        });
        assert numMatrix.sumRegion(2, 1, 4, 3) == 8;
        assert numMatrix.sumRegion(1, 1, 2, 2) == 11;
        assert numMatrix.sumRegion(1, 2, 2, 4) == 12;
    }

}
