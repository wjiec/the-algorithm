package ability;

public class Array {

    // 整数前缀和
    public static class PrefixSum {
        // 存储前缀和结果, prefixSum[i] 表示 [0, i) 的区间和
        private final long[] prefixSum;

        // 根据数组计算前缀和结果
        public PrefixSum(int[] array) {
            prefixSum = new long[array.length + 1];
            for (int i = 1; i <= array.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + array[i - 1];
            }
        }

        // 重载长整数类型的数组
        public PrefixSum(long[] array) {
            prefixSum = new long[array.length + 1];
            for (int i = 1; i <= array.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + array[i - 1];
            }
        }

        // 求范围 [l, r] 的和(包含两端)
        public long range(int l, int r) {
            return prefixSum[r + 1] - prefixSum[l];
        }

        // 获取下标为 idx 位置的元素
        public long get(int idx) {
            return prefixSum[idx + 1] - prefixSum[idx];
        }
    }

}
