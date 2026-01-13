package weekly.bw168.D;

import java.util.*;

/**
 * Q4. Count Ways to Choose Coprime Integers from Rows
 *
 * https://leetcode.cn/contest/biweekly-contest-168/problems/count-ways-to-choose-coprime-integers-from-rows/
 *
 * You are given a m x n matrix mat of positive integers.
 *
 * Return an integer denoting the number of ways to choose exactly one integer
 * from each row of mat such that the greatest common divisor of all chosen integers is 1.
 *
 * Since the answer may be very large, return it modulo 1e9 + 7.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private static final int MAX_N = 151, MOD = 1_000_000_007;
    private static final boolean[] isPrime = new boolean[MAX_N];
    private static final List<Integer> primes = new ArrayList<>();
    static {
        Arrays.fill(isPrime, true); isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < MAX_N; i++) {
            if (!isPrime[i]) continue; primes.add(i);
            for (int j = i + i; j < MAX_N; j += i) isPrime[j] = false;
        }
    }

    private static final long[] primeFactor = new long[MAX_N];
    static {
        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            for (int j = 1; j < MAX_N; j++) {
                if (j % p == 0) primeFactor[j] |= 1L << i;
            }
        }
    }

    public int countCoprime(int[][] mat) {
        // 只有一个数的话, 其最大公约数是自身
        if (mat.length == 1) return (int) Arrays.stream(mat[0]).filter(v -> v == 1).count();
        // 要么选择质数, 要么选择的合数不存在重复的质因子
        return countCoprime(mat, 0, 0);
    }

    private final Map<Long, Integer>[] memo = new Map[MAX_N];
    { Arrays.setAll(memo, i -> new HashMap<>()); }

    private int countCoprime(int[][] mat, int i, long mask) {
        if (i >= mat.length) return 1;
        if (memo[i].containsKey(mask)) return memo[i].get(mask);

        int ans = 0;
        for (var j : mat[i]) {
            if (isPrime[j] || (mask & primeFactor[j]) == 0) {
                long nextMask = isPrime[j] ? mask : (mask | primeFactor[j]);
                ans = (ans + countCoprime(mat, i + 1, nextMask)) % MOD;
            }
        }

        memo[i].put(mask, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countCoprime(new int[][]{{1,2},{3,4}}) == 3;
        assert new Solution().countCoprime(new int[][]{{1},{2},{2}}) == 1;
        assert new Solution().countCoprime(new int[][]{{150}}) == 0;
    }

}
