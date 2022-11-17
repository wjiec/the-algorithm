package ability;

import java.util.Arrays;

@SuppressWarnings("DuplicatedCode")
public class Array {

    // 初始化一个数组并填充默认值为 fill
    public static int[] make(int len, int fill) {
        int[] array = new int[len];
        Arrays.fill(array, fill);
        return array;
    }

    // 初始化一个数组并填充默认值为 fill
    public static int[] make(int len, int fill, int ...init) {
        int[] array = make(len, fill);
        System.arraycopy(init, 0, array, 0, Math.min(init.length, len));
        return array;
    }

    // 获取数组中的最大值
    public static int max(int[] array) {
        int max = 0;
        for (var v : array) if (v > max) max = v;
        return max;
    }

    // 获取长整形数组中的最大值
    public static long max(long[] array) {
        long max = 0;
        for (var v : array) if (v > max) max = v;
        return max;
    }

    // 从满足 array[0] <= array[1] <= array[n-1] <= array[n] 的数组
    // 中找到第一个大于等于 target 的下标
    //
    // 从满足 array[0] >= array[1] >= array[n-1] >= array[n] 的数组
    // 中找到第一个小于等于 target 的下标
    public static int lower(int[] array, int target) {
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 从满足 array[0] <= array[1] <= array[n-1] <= array[n] 的数组
    // 中找到第一个大于 target 的下标
    //
    // 从满足 array[0] >= array[1] >= array[n-1] >= array[n] 的数组
    // 中找到第一个小于 target 的下标
    public static int upper(int[] array, int target) {
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 交换数组中的两个元素
    public static void swap(int[] array, int a, int b) {
        int stash = array[a];
        array[a] = array[b];
        array[b] = stash;
    }

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

    public static void main(String[] args) {
        int[] list = new int[]{1, 1, 2, 3, 5, 5, 5, 7, 9, 9, 9, 10};
        System.out.println(lower(list, 2));
        System.out.println(lower(list, 4));
        System.out.println(lower(list, 5));
        System.out.println();

        System.out.println(lower(list, 0));
        System.out.println(lower(list, 1));
        System.out.println(lower(list, 11));
        System.out.println();

        System.out.println(upper(list, 2));
        System.out.println(upper(list, 4));
        System.out.println(upper(list, 5));
        System.out.println();

        System.out.println(upper(list, 0));
        System.out.println(upper(list, 1));
        System.out.println(upper(list, 11));
        System.out.println();
    }

}
