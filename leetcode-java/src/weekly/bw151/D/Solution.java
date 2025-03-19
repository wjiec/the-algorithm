package weekly.bw151.D;

import common.Checker;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 3470. Permutations IV
 *
 * https://leetcode.cn/contest/biweekly-contest-151/problems/permutations-iv/
 *
 * Given two integers, n and k, an alternating permutation is a permutation of the
 * first n positive integers such that no two adjacent elements are both odd or both even.
 *
 * Return the k-th alternating permutation sorted in lexicographical order.
 *
 * If there are fewer than k valid alternating permutations, return an empty list.
 */

@SuppressWarnings({"SequencedCollectionMethodCanBeUsed", "unchecked"})
public class Solution {

    public int[] permute(int n, long k) {
        // 对于从 [1, n] 我们一共有 ceil(n / 2) 个奇数, floor(n / 2) 个偶数
        //  - 偶数位置与奇数位置的数字可以任意选择一个顺序排列
        //  - 所以总的排列数为 c_n = ceil(n / 2)! * floor(n / 2)!
        //      - n = 0     =>  0! * 0!
        //      - n = 1     =>  1! * 0!     =>  c_0 * 1
        //      - n = 2     =>  1! * 1!     =>  c_1 * 1
        //      - n = 3     =>  2! * 1!     =>  c_2 * 2
        //      - n = 4     =>  2! * 2!     =>  c_3 * 2
        //      - n = 5     =>  3! * 2!     =>  c_4 * 3
        //      - n = 6     =>  3! * 3!     =>  c_5 * 3
        //  - c_n = c_{n - 1} * {1, 1, 2, 2, 3, 3, ...}
        List<Long> c = new ArrayList<>(); c.add(1L);
        // 由于阶乘的增长速度非常快, 题目给的最大范围就是 10^15, 所以当超过这个数值
        // 之后就意味着肯定是从 1, 2, 3 开始排的, 只有最后的一部分需要进行排列
        for (long x = 1; c.get(c.size() - 1) < 1e15; x++) {
            c.add(c.get(c.size() - 1) * x);
            c.add(c.get(c.size() - 1) * x);
        }
        // 如果在总排列的数量是在范围内的, 则直接判断 k 是否超过总的数量
        //  - 如果 n 是奇数, 则第一位必须是奇数
        //  - 如果 n 是偶数, 则第一位可以奇数也可以是偶数, 这里计算的方案数需要乘以 2
        if (n < c.size() && k > c.get(n) * (2L - n % 2)) return new int[0];

        // 将 k 改成从 0 开始, 方便后续的计算
        k -= 1;

        // 统计所有未使用的数字, unused[0] 保存所有的偶数, unused[1] 保存所有的奇数
        List<Integer>[] unused = new List[]{new ArrayList<>(), new ArrayList<>()};
        for (int i = 1; i <= n; i++) unused[i % 2].add(i);

        // 对于从小到大的序列, 对于每一个确定的位
        //  - 后续的数字组合可以分为 unused[parity] * (2 - n % 2) 组
        //  - 每一组的大小是 c[i - 1]
        // 所以我们可以根据以上信息计算当前需要填入的数字(也就是第几组)

        int[] ans = new int[n];
        // 枚举当前需要填入的数字数, parity 表示当前所要使用的奇偶性
        for (int i = 0, parity = 1; i < n; i++, parity ^= 1) {
            // 如果 n 很大的话, 我们需要按顺序填入 1, 2, 3, 4
            long group = 0;
            // 如果剩余的数字的数量实在 10e15 范围之内的话, 则我们可以找到所需要使用的数字
            if (n - i - 1 < c.size()) {
                // 计算是第几组, 每一组的大小是 c[i - 1]
                group = k / c.get(n - i - 1);
                // 找到第几组之后, 我们需要更新我们在所选择的组中的序号
                k = k % c.get(n - i - 1);
                // 如果 n 是偶数的话, 第一个数即可以是奇数也可以是偶数, 需要特殊处理下
                //  - 如果我们计算出来是第 x 项, 实际应该是奇数或者偶数的第 floor(x / 2) 项
                //  - 我们从 1, 2, 3, ... 开始枚举时, 偶数项时在 x 为奇数的时候出现
                if (n % 2 == 0 && i == 0) {
                    parity = (int) ((group % 2) ^ 1); // 翻转奇偶性
                    group /= 2;
                }
            }

            // 找到当前所使用的数字
            ans[i] = unused[parity].remove((int) group);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().permute(4, 6), new int[]{3, 4, 1, 2});
        assert Checker.check(new Solution().permute(3, 2), new int[]{3, 2, 1});
        assert Checker.check(new Solution().permute(2, 3), new int[0]);
    }

}
