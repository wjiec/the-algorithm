package weekly.bw148.D;

import static ability.Ability.Math.pow;

public class Solution {

    private static final int MAX_N = 100_001;
    private static final int MOD = 1_000_000_007;
    private static final long[] fac = new long[MAX_N];
    static {
        fac[0] = fac[1] = 1;
        for (int i = 2; i < MAX_N; i++) fac[i] = (fac[i - 1] * i) % MOD;
    }

    private static final long[] inv = new long[MAX_N];
    static {
        // 在 MOD 下的乘法逆元 inv_a = 1 / a = pow(a, MOD - 2, MOD)
        //  - 对于阶乘 a * b * c 的逆元是 1 / (a * b * c) = pow(a * b * c, MOD - 2, MOD)
        //      - 递推 a * b 的逆元是 1 / (a * b) = 1 / (a * b * c) * c
        inv[MAX_N - 1] = pow(fac[MAX_N - 1], MOD - 2, MOD);
        for (int i = MAX_N - 1; i > 0; i--) inv[i - 1] = (inv[i] * i) % MOD;
    }

    private int comb(int n, int c) {
        if (n < c) return 0;
        return (int) ((((fac[n] * inv[c]) % MOD) * inv[n - c]) % MOD);
    }

    public int distanceSum(int m, int n, int k) {
        // 枚举两个棋子的摆放方式, 他们的距离是 d, 则剩下的 k - 2 个棋子
        // 可以在 m * n - 2 个位置中任意摆放
        //
        // 在一行(m = 1)中使用两个棋子摆出距离为 d 的方案数为 n - d, 从 d = 1 枚举到 d = n - 1
        //  同时考虑其他棋子的摆放, 所有的距离之和则有:
        //
        //      {mn - 2 \choose k - 2} \cdot \sum_{d = 1}^{n - 1} d \cdot (n - d)
        //
        // 推广到 m 为任意值的情况(只考虑 |x1 - x2|), 则可以在 m 行中任意选两行放置, 则有:
        //
        //      m^2 \cdot {mn - 2 \choose k - 2} \cdot \sum_{d = 1}^{n - 1} d \cdot (n - d)
        //
        // 对应到 |y1 - y2| 下, 则有:
        //
        //      n^2 \cdot {mn - 2 \choose k - 2} \cdot \sum_{d = 1}^{m - 1} d \cdot (m - d)
        //
        // 合并 |x1 - x2| + |y1 - y2|, 所以答案为
        //
        //      {mn - 2 \choose k - 2} \cdot (m^2 \cdot \sum_{d = 1}^{n - 1} d \cdot (n - d) + n^2 \cdot \sum_{d = 1}^{m - 1} d \cdot (m - d))
        //
        long dx = 0, dy = 0;
        for (int d = 1; d < n; d++) dx = (dx + ((long) d * (n - d))) % MOD;
        for (int d = 1; d < m; d++) dy = (dy + ((long) d * (m - d))) % MOD;

        long ans = ((m * m * dx) + (n * n * dy)) % MOD;
        return (int) ((comb(m * n - 2, k - 2) * ans) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().distanceSum(1, 2, 2) == 1;
    }

}
