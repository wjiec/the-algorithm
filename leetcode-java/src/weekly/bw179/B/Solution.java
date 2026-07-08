package weekly.bw179.B;

import static ability.Ability.Math.pow;

/**
 * Q2. Direction Assignments with Exactly K Visible People
 *
 * https://leetcode.cn/contest/biweekly-contest-179/problems/direction-assignments-with-exactly-k-visible-people/
 *
 * You are given three integers n, pos, and k.
 *
 * There are n people standing in a line indexed from 0 to n - 1. Each person independently chooses a direction:
 *
 * 'L': visible only to people on their right
 * 'R': visible only to people on their left
 * A person at index pos sees others as follows:
 * A person i < pos is visible if and only if they choose 'L'.
 * A person i > pos is visible if and only if they choose 'R'.
 * Return the number of possible direction assignments such that the person at index pos sees exactly k people.
 *
 * Since the answer may be large, return it modulo 1e9 + 7.
 */

public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 100_000;
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

    public int countVisiblePeople(int n, int pos, int k) {
        // 左侧的人数是 lc = pos 人, 右侧的人数是 rc = n - pos 人
        //  - 左侧需要从 lc 个人里选出 k' 个人选择 L
        //  - 右侧需要从 rc 个人里选出 k - k' 个人选择 R
        //
        // 同时自己可以选择 L / R, 也就是 2 倍
        //  - 所以答案就是 sum(comb(lc, k') * comb(rc, k - k')) * 2
        long ans = 0;
        for (int lk = 0; lk <= k && lk <= pos; lk++) {
            long curr = comb(pos, lk) * comb(n - pos - 1, k - lk);
            ans += ((curr % MOD) * 2) % MOD;
        }
        return (int) (ans % MOD);
    }

    private long comb(int n, int c) {
        if (c > n) return 0;
        // 从 n 个人里面选出 k 个, 同时不关注顺序
        return (((fac[n] * inv[c]) % MOD) * inv[n - c]) % MOD;
    }

    public static void main(String[] args) {
        assert new Solution().countVisiblePeople(2, 1, 1) == 2;
        assert new Solution().countVisiblePeople(2, 0, 0) == 2;
        assert new Solution().countVisiblePeople(3, 1, 0) == 2;
        assert new Solution().countVisiblePeople(3, 2, 1) == 4;
        assert new Solution().countVisiblePeople(1, 0, 0) == 2;
    }

}
