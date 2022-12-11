package ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // 获取数组的所有子集
    public static List<List<Integer>> combinations(int[] array) {
        List<List<Integer>> ans = new ArrayList<>();

        ans.add(new ArrayList<>());
        for (var v : array) {
            for (int i = 0, l = ans.size(); i < l; i++) {
                List<Integer> curr = new ArrayList<>(ans.get(i));
                curr.add(v);
                ans.add(curr);
            }
        }
        return ans;
    }

    // 在一个排好序的数组中查找指定的值所在的下标, 如果找不到则返回 -1
    // 要求数组中数字按照顺序递增, 如果数组中存在重复值则随机返回一个下标
    public static int search(int[] array, int target) {
        int l = 0, r = array.length;
        if (r == 0 || target < array[0] || target > array[r - 1]) return -1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 在一个排好序的数组中查找第一个大于或等于指定的值的下标, 如果找不到则返回 -1
    // 要求数组中数字按照顺序递增, 如果数组中存在重复值则随机返回一个下标
    public static int ceiling(int[] array, int target) {
        int l = 0, r = array.length;
        if (r == 0 || target > array[r - 1]) return -1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 在一个排好序的数组中查找第一个小于或等于指定的值的下标, 如果找不到则返回 -1
    // 要求数组中数字按照顺序递增, 如果数组中存在重复值则随机返回一个下标
    public static int floor(int[] array, int target) {
        int l = 0, r = array.length, ans = 0;
        if (r == 0 || target < array[0]) return -1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] > target) r = mid;
            else { ans = mid; l = mid + 1; }
        }
        return ans;
    }

    // 倒序数组(按数组顺序递减)中的方法
    public static class Reversed {
        // 在一个排好序的数组中查找指定的值所在的下标, 如果找不到则返回 -1
        // 要求数组中数字按照顺序递减, 如果数组中存在重复值则随机返回一个下标
        public static int search(int[] array, int target) {
            int l = 0, r = array.length;
            if (r == 0 || target > array[0] || target < array[r - 1]) return -1;

            while (l < r) {
                int mid = l + (r - l) / 2;
                if (array[mid] == target) return mid;
                if (array[mid] < target) r = mid;
                else l = mid + 1;
            }
            return l;
        }

        // 在一个排好序的数组中查找第一个大于或等于指定的值的下标, 如果找不到则返回 -1
        // 要求数组中数字按照顺序递减, 如果数组中存在重复值则随机返回一个下标
        public static int ceiling(int[] array, int target) {
            int l = 0, r = array.length, ans = 0;
            if (r == 0 || target > array[0]) return -1;

            while (l < r) {
                int mid = l + (r - l) / 2;
                if (array[mid] < target) r = mid;
                else { ans = mid; l = mid + 1; }
            }
            return ans;
        }

        // 在一个排好序的数组中查找第一个小于或等于指定的值的下标, 如果找不到则返回 -1
        // 要求数组中数字按照顺序递减, 如果数组中存在重复值则随机返回一个下标
        public static int floor(int[] array, int target) {
            int l = 0, r = array.length, ans = 0;
            if (r == 0 || target < array[r - 1]) return -1;

            while (l < r) {
                int mid = l + (r - l) / 2;
                if (array[mid] > target) r = mid;
                else { ans = mid; l = mid + 1; }
            }
            return ans;
        }
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

    // 反转整个数组中的元素
    public static void reverse(int[] array) {
        reverse(array, 0, array.length);
    }

    // 反转整个数组中指定范围[l, r)的元素
    private static void reverse(int[] array, int l, int r) {
        for (int a = l, b = r - 1; a < r; a++, b--) {
            swap(array, a, b);
        }
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
        //                      0  1  2  3  4  5  6  7  8  9 10  11
        int[] array = new int[]{1, 1, 2, 3, 5, 5, 5, 7, 9, 9, 9, 10};
        System.out.println(floor(array, 2));

        //                          0  1  2  3  4  5  6  7  8  9  10
        int[] reversed = new int[]{10, 9, 8, 8, 7, 6, 5, 5, 3, 2, 1};
        System.out.println(Reversed.ceiling(reversed, 0));
    }

}
