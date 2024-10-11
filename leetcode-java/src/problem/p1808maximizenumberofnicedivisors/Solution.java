package problem.p1808maximizenumberofnicedivisors;

import static ability.Ability.Math.pow;

/**
 * 1808. Maximize Number of Nice Divisors
 *
 * https://leetcode.cn/problems/maximize-number-of-nice-divisors/
 *
 * You are given a positive integer primeFactors. You are asked to construct a
 * positive integer n that satisfies the following conditions:
 *
 * The number of prime factors of n (not necessarily distinct) is at most primeFactors.
 *
 * The number of nice divisors of n is maximized. Note that a divisor of n is nice
 * if it is divisible by every prime factor of n. For example, if n = 12, then its
 * prime factors are [2,2,3], then 6 and 12 are nice divisors, while 3 and 4 are not.
 *
 * Return the number of nice divisors of n. Since that number
 * can be too large, return it modulo 109 + 7.
 *
 * Note that a prime number is a natural number greater than 1 that is not a
 * product of two smaller natural numbers. The prime factors of a number n is
 * a list of prime numbers such that their product equals n.
 */

public class Solution {

    // 由至多 primeFactors 个质因子组成的数的因子可以被所有的质因子整除
    //  - 所以我们选择的质因子的种类越多，那么越难被整除
    //
    // 有质数：p1, p2, p3, ..., pn
    // 那么我们可以使得 n = p1 ^ c1 + p2 ^ c2 + ... + pn ^ cn
    //  - 为了满足好因子（因子可以被所选的质数整除）要求
    //      - 我们可以选择从 p1 中选出 [1, c1] 个, 从 p2 中选出 [1, c2] 个, ...
    //  - 那么好因子的数目 ans = c1 * c2 * ... cn 要最大
    //      => 等价于求 c1 + c2 + ... + cn = primeFactors 同时最大的乘积
    //      => https://leetcode.cn/problems/integer-break/
    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors < 3) return primeFactors;

        long ans = 0; final int MOD = 1_000_000_007;
        switch (primeFactors % 3) {
            case 0 -> ans = pow(3, primeFactors / 3, MOD);
            case 1 -> ans = pow(3, primeFactors / 3 - 1, MOD) * 4;
            case 2 -> ans = pow(3, primeFactors / 3, MOD) * 2;
        }

        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().maxNiceDivisors(1) == 1;
        assert new Solution().maxNiceDivisors(2) == 2;
        assert new Solution().maxNiceDivisors(18) == 729;
    }

}
