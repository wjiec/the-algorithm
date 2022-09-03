package ability;

@SuppressWarnings("DuplicatedCode")
public class Matrix {

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
