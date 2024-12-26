package weekly.bw146.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ability.Ability.Math.pow;

public class Solution {

    private static final int MAX_N = 1001;
    private static final int MOD = 1_000_000_007;
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

    // 长度为 5 的子序列中, 中间一个数是这个子序列的唯一众数
    //  - 所有可能的情况如下:
    //      - 2 个众数, 并配合 3 个不一样的数, 中间必须为众数
    //      - 3 个众数, 并配合 2 个任意数, 中间必须为众数
    //      - 4 个众数, 并配合 1 个任意数, 中间必须为众数
    //      - 5 个众数
    public int subsequencesWithMiddleMode(int[] nums) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            m.computeIfAbsent(nums[i], k -> new HashSet<>()).add(i);
        }

        long ans = 0;
        for (var e : m.entrySet()) {
            int n = e.getValue().size();
            if (n >= 5) {
                // 从 n 个数里取 5 个数, 但是顺序不重要, 即求 C(n, 5)
                //    C(n, 5) = n! / (5! * (n - 5)!)
                //            = fac[n] * inv[5] * inv[n - 5]
                ans = (ans + ((fac[n] * inv[5]) % MOD) * inv[n - 5]) % MOD;
            }
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().subsequencesWithMiddleMode(new int[]{1,1,1,1,1,1,1,1}) == 56;
    }

}
