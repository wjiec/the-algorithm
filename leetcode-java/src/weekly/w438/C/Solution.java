package weekly.w438.C;

import ability.Benchmark;
import common.Tag;

import static ability.Ability.Math.pow;

/**
 * 3463. Check If Digits Are Equal in String After Operations II
 *
 * https://leetcode.cn/contest/weekly-contest-438/problems/check-if-digits-are-equal-in-string-after-operations-ii/
 *
 * You are given a string s consisting of digits. Perform the following
 * operation repeatedly until the string has exactly two digits:
 *
 * For each pair of consecutive digits in s, starting from the first digit, calculate a new digit
 * as the sum of the two digits modulo 10.
 *
 * Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
 *
 * Return true if the final two digits in s are the same; otherwise, return false.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    private static final int MOD = 10;
    private static final int MAX_N = 100_001;
    private static final int[] tri = new int[MAX_N];
    private static final long[] fac = new long[MAX_N];
    private static final int[] factor2 = new int[MAX_N];
    private static final int[] factor5 = new int[MAX_N];
    static {
        fac[0] = 1;
        for (int i = 1; i < MAX_N; i++) {
            int curr = i;
            factor2[i] = factor2[i - 1];
            factor5[i] = factor5[i - 1];
            for (; curr % 2 == 0; curr /= 2) factor2[i]++;
            for (; curr % 5 == 0; curr /= 5) factor5[i]++;

            // 计算排除 2 和 5 因子之后剩余的阶乘
            fac[i] = (fac[i - 1] * curr) % MOD;
            tri[i] = curr;
        }
    }

    private static final long[] inv = new long[MAX_N];
    static {
        // 欧拉定理求逆元
        inv[MAX_N - 1] = pow(fac[MAX_N - 1], 3, MOD);
        for (int i = MAX_N - 1; i > 0; i--) inv[i - 1] = (inv[i] * tri[i]) % MOD;
    }

    private long comb(int n, int k) {
        return fac[n] * inv[k] * inv[n - k] *
            pow(2, factor2[n] - factor2[k] - factor2[n - k], MOD) *
            pow(5, factor5[n] - factor5[k] - factor5[n - k], MOD);
    }

    @Tag({"欧拉定理", "合数取模"})
    public boolean hasSameDigits(String s) {
        // 旧字符串     a               b           c           d           e       f       g
        // 第一轮      ab              bc          cd          de          ef      fg
        // 第二轮      abbc            bccd        cdde        deef        effg
        // 第三轮      abbbcccd        bcccddde    cdddeeef    deeefffg
        // 第四轮      a4b6c4de        b4c6d4ef    c4d6e4fg
        // 第五轮      a5b10c10d5ef    b5c10d10e5fg    [杨辉三角]

        // 杨辉三角里第 n 行中的第 k 个数实际等于 C^n_k 的值(n, k 从 0 开始)
        int n = s.length(); long av = 0, bv = 0;
        for (int k = 0, a = 0, b = 1; k <= n - 2; k++, a++, b++) {
            long c = comb(n - 2, k) % 10;
            av = (av + c * (s.charAt(a) - '0')) % 10;
            bv = (bv + c * (s.charAt(b) - '0')) % 10;
        }

        return av == bv;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().hasSameDigits("059223162476909414787217368465718889720070329493800526721646241144650386182915621907231953557681242064182905239381861256480822308801745728716464165805416272563074544781706952551993233233441914762054761669477046604260289688651191958433480070003587023200105113105431268932582314103774297291977036873970402534522915576764583200175755147667814674754512504911569655037494222537756410610151191257150195557437349822009352297672631564482185262565187532385279714260044303857829021469873315780358707063556775718248201658447824815265573818347949428248619384219498719969693539037903024755075964220907437734386614616595870073534174014967983825396");
            assert new Solution().hasSameDigits("3902");
            assert !new Solution().hasSameDigits("34789");
            assert new Solution().hasSameDigits("321881");
        });
    }

}
