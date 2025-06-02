package weekly.w448.D;

import java.util.Arrays;

import static ability.Ability.Math.pow;

/**
 * Q4. Find Sum of Array Product of Magical Sequences
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/find-sum-of-array-product-of-magical-sequences/
 *
 * You are given two integers, m and k, and an integer array nums.
 *
 * A sequence of integers seq is called magical if:
 * seq has a size of m.
 * 0 <= seq[i] < nums.length
 * The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
 * The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).
 *
 * Return the sum of the array products for all valid magical sequences.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A set bit refers to a bit in the binary representation of a number that has a value of 1.
 */

public class Solution {

    private static final int MAX_N = 51;
    private final static int MOD = 1_000_000_007;
    private final long[] fac = new long[MAX_N];
    {
        fac[1] = 1;
        for (int i = 2; i < MAX_N; i++) fac[i] = (fac[i - 1] * i) % MOD;
    }

    private final long[] inv = new long[MAX_N];
    {
        // 在 MOD 下的乘法逆元 inv_a = 1 / a = pow(a, MOD - 2, MOD)
        //  - 对于阶乘 a * b * c 的逆元是 1 / (a * b * c) = pow(a * b * c, MOD - 2, MOD)
        //      - 递推 a * b 的逆元是 1 / (a * b) = 1 / (a * b * c) * c
        inv[MAX_N - 1] = pow(fac[MAX_N - 1], MOD - 2, MOD);
        for (int i = MAX_N - 1; i > 0; i--) inv[i - 1] = (inv[i] * i) % MOD;
    }

    public int magicalSum(int m, int k, int[] nums) {
        // 使用 [0, n) 的任意组合使得 1 << seq[0] | 1 << seq[1] | ... | 1 << seq[m - 1] 有 k 个置位
        //  - 返回所有符合条件的排列的数组乘积之和
        //
        // 也就是在 [0, n) 中选出 k 个数, 组成长度为 m 的数组 seq
        //  - 计算所有可能排列的 nums[seq[0]] * nums[seq[1]] * ... * ... nums[seq[m - 1]] 的和
        //
        // 对于单独某一个序列 seq, 总长度为 m, 有 k 个不同的数, 且选择 j = seq[i] 的数量为 c[j], 则数组乘积为:
        //  - \sum_{j=0}^{k-1} nums[j] ^ c[j]
        //
        // 由于每种排列方式都不一样, 所以需要计算组合数: m! / (c[0]! * c[1]! * ... * c[k-1]!)
        //  - 组合得到 m! / ((c[0]! * c[1]! * ... * c[k-1]!) * \sum_{j=0}^{k-1} nums[j] ^ c[j])
        //  - 分解得到 m! * \sum_{j=0}^{k-1} (nums[j] ^ c[j]) / c[j]!
        //
        // 首先预处理得到 (nums[j] ^ c[j]) / c[j]!, 其中 1/c[j]! 需要使用逆元计算
        long[][] nPow = new long[nums.length][m + 1]; // nPow[i][j] 表示 nums[i] ^ j 的值
        for (int i = 0; i < nums.length; i++) {
            nPow[i][0] = 1;
            for (int j = 0; j < m; j++) {
                nPow[i][j + 1] = (nPow[i][j] * nums[i]) % MOD;
            }
        }

        memo = new long[nums.length][m + 1][k + 1][m / 2 + 1];
        for (var a : memo) for (var b : a) for (var c : b) Arrays.fill(c, -1);

        // 同时所选择的 seq 数组需要恰好使得 2^seq[0] + 2^seq[1] + ... + 2^seq[m-1] 恰好有 k 个置位
        //  - 如果 seq[0] == seq[1], 则在二进制下实际上只有一个置位, 还可能与其他的 seq[j] 进行叠加使得置位数发生变化
        //
        // 使用动态规划进行求解, 重点在于状态的设计
        //  - 由于我们最后需要计算置位数, 所以当置位 2 ^ (n - 1) 时, 状态很难表达
        //      - 我们可以在计算 i 的状态时, 提前把 [0, i) 的所有置位情况计算出来(置位 i 只影响后续的所有位)
        long ans = dfs(nums, 0, m, k, 0, nPow);
        // 最后的结果需要乘以 m!
        return (int) ((ans * fac[m]) % MOD);
    }

    private long[][][][] memo = null;

    // 当前正在处理第 i 个索引, 剩下还需要填充 m 个数字, 当前剩余还需要有 k 个置位, 当前 >= 2^i 的置位情况为 s
    private long dfs(int[] nums, int i, int m, int k, int s, long[][] nPow) {
        if (i == nums.length) return m == 0 && Integer.bitCount(s) == k ? 1 : 0;
        if (memo[i][m][k][s] != -1) return memo[i][m][k][s];

        long ans = 0;
        // 枚举当前在数组中有 j 个数字 i
        for (int j = 0; j <= m; j++) {
            // 当前位对二进制数的贡献是 j * (2 << i), 但是我们计算 s 是需要 >> i 的, 所以实际贡献是 (s + j)
            //  - 需要检查 s + j 是否会造成当前位出现一个置位, 且我们还有空余的位置进行置位
            int set = (s + j) & 1;
            if (set <= k) {
                long curr = dfs(nums, i + 1, m - j, k - set, (s + j) >> 1, nPow);
                ans = (ans + ((curr * nPow[i][j]) % MOD) * inv[j]) % MOD;
            }
        }
        return memo[i][m][k][s] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().magicalSum(13, 8, new int[]{52900,36842,43727,57290,97561,94545,84642,68215,91601,76832,52673,94789,6123,70762,73080,11830,57262,93991,95078,95082,58420,62723}) == 120815395;

        assert new Solution().magicalSum(5, 5, new int[]{1,10,100,10000,1000000}) == 991600007;
        assert new Solution().magicalSum(2, 2, new int[]{5,4,3,2,1}) == 170;
        assert new Solution().magicalSum(1, 1, new int[]{28}) == 28;
    }

}
