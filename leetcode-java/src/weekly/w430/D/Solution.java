package weekly.w430.D;

import java.util.HashMap;
import java.util.Map;

import static ability.Ability.Math.pow;

public class Solution {

    private final int MOD = 1_000_000_007;

    // 构造长度为 n 的数组, 数组的值为 [1, m], 同时数组有 k 个相邻元素数值相同的元素对
    public int countGoodArrays(int n, int m, int k) {
        return (int) ((m * count(n - 1, m, k)) % MOD);
    }

    private final Map<Long, Long> memo = new HashMap<>();

    // isFirst 表示是否当前是否是第一个元素
    private long count(long n, long m, long k) {
        if (k >= n) return k == n ? 1 : 0; // 无法创建一个长度为 n 但是要求有 >= n 个元素对
        // 如果 k == 0, 那么我们每个相邻的元素都不能相同
        if (k == 0) return pow(m - 1, n, MOD);

        long key = (n << 32) | k;
        if (memo.containsKey(key)) return memo.get(key);

        // 可以选择与前面不同的值, 但是这样就无法创建一个相同的元素对了
        long ans = ((m - 1) * count(n - 1, m, k)) % MOD;
        // 选择与前面的值相同, 可以减少一个元素对, 那么当前值必须和前面的一样
        ans = (ans + count(n - 1, m, k - 1)) % MOD;

        memo.put(key, ans);
        return ans;
    }

    private static class Math {
        public int countGoodArrays(int n, int m, int k) {
            final int MOD = 1_000_000_007;

            long[] fac = new long[n]; fac[0] = 1;
            for (int i = 1; i < n; i++) fac[i] = (fac[i - 1] * i) % MOD;

            long[] inv = new long[n]; inv[n - 1] = pow(fac[n - 1], MOD - 2, MOD);
            for (int i = n - 1; i > 0; i--) inv[i - 1] = (inv[i] * i) % MOD;

            // 有 k 个相等的相邻元素对, 意味着在 n 个元素中有 n - k - 1 个不相同的元素对
            //  - 使用隔板法, 隔板的左右两边的元素对不相同, 则 n 个元素中可以放 n - 1 个隔板
            //  - 同时我们需要放其中的 n - k - 1 个隔板, 即计算 comb(n - 1, n - 1 - k) = comb(n - 1, k)
            long ans = (((fac[n - 1] * inv[k]) % MOD) * inv[(n - 1) - k]) % MOD;

            // 隔板分开的 k 组元素, 第一组元素有 m 种方式方案, 后续的组由于要和隔板之前的元素不一样
            // 则只有 m - 1 种方案, 即计算 m * (m - 1) ^ (n - k - 1)
            return (int) ((((ans * m) % MOD) * pow(m - 1, n - k - 1, MOD)) % MOD);
        }
    }

    public static void main(String[] args) {
        assert new Math().countGoodArrays(3, 3, 2) == 3;
        assert new Math().countGoodArrays(10000, 10000, 8888) == 686278068;
        assert new Math().countGoodArrays(4, 2, 2) == 6;
        assert new Math().countGoodArrays(3, 2, 1) == 4;
        assert new Math().countGoodArrays(5, 2, 0) == 2;

        assert new Solution().countGoodArrays(4, 2, 2) == 6;
        assert new Solution().countGoodArrays(3, 2, 1) == 4;
        assert new Solution().countGoodArrays(5, 2, 0) == 2;
    }

}
