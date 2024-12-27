package weekly.bw146.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ability.Ability.Math.pow;

/**
 * 3395. Subsequences with a Unique Middle Mode I
 *
 * https://leetcode.cn/contest/biweekly-contest-146/problems/subsequences-with-a-unique-middle-mode-i/
 *
 * Given an integer array nums, find the number of subsequences of size 5 of nums with a unique middle mode.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * A mode of a sequence of numbers is defined as the element that appears the maximum number of times in the sequence.
 *
 * A sequence of numbers contains a unique mode if it has only one mode.
 *
 * A sequence of numbers seq of size 5 contains a unique middle mode if the middle element (seq[2]) is a unique mode.
 */

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

    private int comb(int n, int c) {
        if (n < c) return 0;
        return (int) ((((fac[n] * inv[c]) % MOD) * inv[n - c]) % MOD);
    }

    // 长度为 5 的子序列中, 中间一个数是这个子序列的唯一众数
    //  - 所有可能的情况如下:
    //      - 2 个众数, 并配合 3 个不一样的数, 中间必须为众数
    //      - 3 个众数, 并配合 2 个任意数, 中间必须为众数
    //      - 4 个众数, 并配合 1 个任意数, 中间必须为众数
    //      - 5 个众数
    public int subsequencesWithMiddleMode(int[] nums) {
        Set<Integer> all = new HashSet<>();
        for (var v : nums) all.add(v);

        // 枚举中间数为 x, 我们通过计算所有长度为 5 的子序列 - 不合法的子序列得到答案
        //  - 只有一个 x, 那么就是 * * x * * 的形式(* 表示所有不等于 x 的数)
        //  - 只有两个 x, 那么就是 * x x * * 或者 * * x x * 的形式, 这里的 3 个 * 所代表的数字
        //      - 不能是 a b c 三个不同数字的形式(这样 x 就是众数了)
        //      - 必须是 y y * (y 表示不等于 x 的数字, * 表示不等于 x 和 y 的数字)
        //      - 必须是 y y y (y 表示不等于 x 的数字)
        //  - 如果 x 的个数 >= 3 个, 那么都是合法情况, 不考虑
        Map<Integer, Integer> suf = new HashMap<>();
        Map<Integer, Integer> pre = new HashMap<>();
        for (var v : all) { pre.put(v, 0); suf.put(v, 0); }
        for (var v : nums) suf.merge(v, 1, Integer::sum);

        int n = nums.length;
        long ans = comb(n, 5);
        for (int i = 0; i < n - 2; i++) {
            suf.merge(nums[i], -1, Integer::sum);
            // 我们需要前面至少有 2 个数字
            if (i > 1) {
                int x = nums[i];
                int preX = pre.get(x);
                int sufX = suf.get(x);
                // 前面不等于 x 的数字数量
                int preNotX = i - preX;
                // 后面不等于 x 的数字数量
                int sufNotX = n - i - 1 - sufX;

                // 只有一个 x, 也就是 * * x * * 的形式
                ans = (ans - mul(comb(preNotX, 2), comb(sufNotX, 2)) + MOD) % MOD;
                // 只有两个 x, 也就是 * x x * * 或者 * * x x * 的形式
                //  - 首先枚举两个 y 在同一侧的情况, 另一侧的 * 可以是除了 x 之外的任何数 (包括 y)
                for (var e : pre.entrySet()) {
                    if (e.getKey() == x || e.getValue() < 2) continue;
                    // 前面有两个非 x 的数, 后面有一个 x 和一个非 x 的数
                    ans = (ans - mul(comb(e.getValue(), 2), sufX, sufNotX) + MOD) % MOD;
                }
                for (var e : suf.entrySet()) {
                    if (e.getKey() == x || e.getValue() < 2) continue;
                    // 后面有两个非 x 的数, 前面有一个 x 和一个非 x 的数
                    ans = (ans - mul(comb(e.getValue(), 2), preX, preNotX) + MOD) % MOD;
                }
                //  - 然后再枚举前后各有一个 y, 同时剩下的 * 既不是 x 也不是 y 的个数(如果是 y 的话, 就是三个 y 与上面的情况重复统计了)
                for (var y : all) {
                    if (y == x) continue;
                    int preY = pre.get(y);
                    int sufY = suf.get(y);
                    int preNotXY = i - preX - preY;
                    int sufNotXY = n - i - 1 - sufX - sufY;

                    // 左边空一个 * 的情况(* y x x y) = preY * preNotXY * sufX * sufY
                    ans = (ans - mul(preY, preNotXY, sufX, sufY) + MOD) % MOD;
                    // 右边空一个 * 的情况(y x x y *) = preY * preX * sufY * sufNotXY
                    ans = (ans - mul(preX, preY, sufY, sufNotXY) + MOD) % MOD;
                }
            }
            pre.merge(nums[i], 1, Integer::sum);
        }

        return (int) ans;
    }

    private int mul(long ...values) {
        long ans = 1;
        for (var v : values) ans = (ans * v) % MOD;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().subsequencesWithMiddleMode(new int[]{1,2,3,2,21,2,3,4,4,5,3,22,21,4,5,6,7,3,2,12,31231,231}) == 5489;

        assert new Solution().subsequencesWithMiddleMode(new int[]{1,2,2,3,3,4}) == 4;
        assert new Solution().subsequencesWithMiddleMode(new int[]{0,1,2,3,4,5,6,7,8}) == 0;
        assert new Solution().subsequencesWithMiddleMode(new int[]{1,1,1,1,1,1,1,1}) == 56;
    }

}
