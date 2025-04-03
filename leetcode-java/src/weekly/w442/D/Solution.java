package weekly.w442.D;

import java.util.ArrayList;
import java.util.List;

/**
 * 3495. Minimum Operations to Make Array Elements Zero
 *
 * https://leetcode.cn/contest/weekly-contest-442/problems/minimum-operations-to-make-array-elements-zero/
 *
 * You are given a 2D array queries, where queries[i] is of the form [l, r]. Each queries[i]
 * defines an array of integers nums consisting of elements ranging from l to r, both inclusive.
 *
 * In one operation, you can:
 *
 * Select two integers a and b from the array.
 * Replace them with floor(a / 4) and floor(b / 4).
 *
 * Your task is to determine the minimum number of operations required to reduce all
 * elements of the array to zero for each query.
 *
 * Return the sum of the results for all queries.
 */

public class Solution {

    // 将每个 queries 表示的数组全变成 0, 每次可以将两个元素变成 floor(v / 4)
    public long minOperations(int[][] queries) {
        // 对于数字 v, 除以 2 和右移一位的效果相同, 除以 4 也就是右移两位
        //  - 所以可以直接计算每个数字的最高位位置除以 4 上取整的值就是需要操作的次数
        //  - 由于一次可以操作两个位置, 所以是数组内所有数字的操作次数除以 2 上取整
        // 也就是 sum(ceil(highest_bit_shift(nums[i]) / 2))
        //  - highest_bit_shift 的返回值 nums[i] 中最高位所在的位置
        //
        // 现在算法的瓶颈在枚举所有的 [l, r] 的值
        //  - 实际上如果 l 是一个 1 << n 且 n % 2 == 0 的情况, 从 [l, l << 2] 的值都是一样的
        //  - 然后每组长度都是 * 4, 也就是可以一批一批统计
        //
        // 每个组都由 2 位组成
        //  - 第一组是 < 100 = 4 (需要操作一次)
        //  - 第二组是 < 10000 = 16, 也就是 [4, 16) 需要操作两次
        //  - 第三组是 < 1000000 = 64, 也就是 [16, 64] 需要操作三次
        //  - ...
        List<Long> groups = new ArrayList<>(); groups.add(0L);
        for (long i = 4; i < (1L << 31); i *= 4) groups.add(i);

        long ans = 0;
        // 将每个查询按照 groups 进行切分, 找到每一段所属的组, 然后计算所需的操作次数
        //  - 第 i 组是 [ groups[i - 1], groups[i] ), 这里面的数都需要操作 i 次
        for (var query : queries) {
            long l = query[0], r = query[1], curr = 0; int gi = 0;
            // 找到在范围内的第一个组
            while (groups.get(gi) <= l) gi++;

            // 依次处理每个组的所需的操作次数
            for (; gi > 0 && l <= r; gi++) {
                long gl = groups.get(gi - 1), gr = groups.get(gi);
                // 这里我们取交集, 如果 r < gr, 则说明 r 也在范围内, 需要额外加 1 个
                curr += (Math.min(gr, r) - Math.max(gl, l) + (r < gr ? 1 : 0)) * gi;
                if (gr > r) break; l = gr;
            }

            ans += (curr / 2) + (curr & 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[][]{{1,2},{2,4}}) == 3;
        assert new Solution().minOperations(new int[][]{{2,6}}) == 4;
        assert new Solution().minOperations(new int[][]{{16777213,568435456}}) == 3986441882L;
    }

}
