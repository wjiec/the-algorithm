package ability;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SparseTable {

    private final int[][] table;

    public SparseTable(int[] nums) {
        int n = nums.length, logN = 32 - Integer.numberOfLeadingZeros(n);
        // table[i][j] 的含义是在 [i, i + 2 ^ j) 范围内的查询答案
        //  - 数组的大小是 n, 对于整段数组的查询中, 如果 i = 0, 则需要满足 2 ^ j >= n
        //  - 也就是 j >= log2(n), 也就是取 j = log2(n)
        table = new int[n][logN];
        // 对于 table[i][0] 控制的范围是 [i, i + 1), 实际上也就是位置 i 的值
        for (int i = 0; i < n; i++) table[i][0] = nums[i];
        // 接下来开始处理倍增, 每次叠加 1 倍的长度, 对于 table[i][j] 来说
        //  - 前一半可以从 table[i][j - 1] 处获得
        //  - 后一半的数据可以从 table[i + 2 ^ (j - 1)][j - 1] 处获得
        for (int j = 1; j < logN; j++) {
            // 此时我们需要计算的范围是 [i, i + 2 ^ j), 如果 i + 2 ^ j 已经
            // 超出了数组范围的话, 实际上就不需要计算了, 因为我们可以从 j - 1 纬度
            // 计算得到答案
            for (int i = 0; i + (1 << j) <= n; i++) {
                // 这里需要满足可重复贡献问题的求解, 也就是对于运算 opt, 满足 opt(x, x) == x
                //  - 例如对于 max(x, x) == x, min(x, x) == x, gcd(x, x) == x
                table[i][j] = Math.max(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // 计算范围 [l, r) 的查询答案
    public int query(int l, int r) {
        if (l >= r) return 0;
        // 按照一般的倍增流程, 如果我们从 l 开始, 不断递增 2 ^ j 次方, 询问的时间复杂度
        // 还是 O(logN), 并没有比线段树更优, 反而多了预处理的时间
        //
        // 对于可重复贡献问题, 我们可以将 [l, r) 分为两段
        //  - 第一段是 [l, l + 2 ^ j)
        //  - 第二段是 [r - 2 ^ j, r'), 注意这里的 r' >= r

        // 我们只需要一个半区间, 且半区间的大小 * 2 >= r - l 即可
        int j = 32 - Integer.numberOfLeadingZeros(r - l) - 1;
        return Math.max(table[l][j], table[r - (1 << j)][j]);
    }

    public static void main(String[] args) {
        Function<Integer, Boolean> fuzzing = (size) -> {
            int[] nums = new int[size];
            for (int i = 0; i < size; i++) {
                nums[i] = ThreadLocalRandom.current().nextInt(1 << 30);
            }

            SparseTable st = new SparseTable(nums);
            for (int i = 0; i < 100_000; i++) {
                int l = ThreadLocalRandom.current().nextInt(size);
                int r = l + 1 + ThreadLocalRandom.current().nextInt(size - l);

                int actual = nums[l];
                for (int j = l + 1; j < r; j++) actual = Math.max(actual, nums[j]);

                if (st.query(l, r) != actual) {
                    System.out.printf("Numbers: %s\nRange: [%d, %d]\nActual: %d\nAnswer: %d\n",
                        Arrays.toString(nums), l, r, actual, st.query(l, r));
                    return false;
                }
            }

            return true;
        };

        for (int i = 0; i < 100; i++) {
            assert fuzzing.apply(100 + ThreadLocalRandom.current().nextInt(1000));
        }
    }

}
