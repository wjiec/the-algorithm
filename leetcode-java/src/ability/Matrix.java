package ability;

@SuppressWarnings({"DuplicatedCode", "unused"})
public class Matrix {

    // 在某个位置的周围 8 个放心坐标的偏移
    private final int[][] dirs8 = new int[][]{
        {-1, -1}, {-1, +0}, {-1, +1},
        {+0, -1}, /* OO */  {+0, +1},
        {+1, -1}, {+1, +0}, {+1, +1}
    };

    // 在某个位置的周围 4 个方向坐标的偏移
    private final int[][] dirs4 = new int[][]{
                  {-1, +0},
        {+0, -1}, /* OO */ {+0, +1},
                  {+1, +0}
    };

    // 交换矩阵中的两个位置的值
    private static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int stash = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = stash;
    }

    // 旋转矩阵90度
    private void rotate(int[][] matrix) {
        flipHorizontal(matrix);
        flipPrincipalDiagonal(matrix);
    }

    // 水平翻转, 上下翻转
    private void flipHorizontal(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n; j++) {
                swap(matrix, i, j, m - i - 1, j);
            }
        }
    }

    // 垂直翻转, 左右翻转
    private void flipVertical(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                swap(matrix, i, l, i, r);
            }
        }
    }

    // 主对角线翻转, 以左上到右下的对角线为轴
    private void flipPrincipalDiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    // 副对角线翻转, 以左下到右上的对角线为轴
    private void flipCounterDiagonal(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, n - j - 1, n - i - 1);
            }
        }
    }

    // 二维矩阵的前缀和实现
    public static class PrefixSum {
        // 存储前缀和结果, prefixSum[i][j] 表示计算如下范围的结果
        //  (0,0)(inclusive) ~ (i,j)(exclusive)
        private final long[][] prefixSum;

        // 初始化二维前缀和数组
        public PrefixSum(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            prefixSum = new long[rows + 1][cols + 1];
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    prefixSum[i][j] = prefixSum[i - 1][j] // 上边
                        + prefixSum[i][j - 1] // 左边
                        - prefixSum[i - 1][j - 1] // 重复累加的部分
                        + matrix[i - 1][j - 1]; // 当前位置的值
                }
            }
        }

        // 重载长整数类型的矩阵
        public PrefixSum(long[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            prefixSum = new long[rows + 1][cols + 1];
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    prefixSum[i][j] = prefixSum[i - 1][j] // 上边
                        + prefixSum[i][j - 1] // 左边
                        - prefixSum[i - 1][j - 1] // 重复累加的部分
                        + matrix[i][j]; // 当前位置的值
                }
            }
        }

        // 求子矩阵的和(包含两端), 传入的参数为左上角和右下角的坐标
        public long range(int ltx, int lty, int rbx, int rby) {
            return prefixSum[rbx + 1][rby + 1] // 右下角子矩阵的和
                - prefixSum[ltx][rby + 1] // 上边
                - prefixSum[rbx + 1][lty] // 左边
                + prefixSum[ltx][lty]; // 多减的左上角矩阵
        }

    }

}
