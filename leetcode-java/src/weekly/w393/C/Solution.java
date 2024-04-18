package weekly.w393.C;

/**
 * 3116. Kth Smallest Amount With Single Denomination Combination
 *
 * https://leetcode.cn/contest/weekly-contest-393/problems/kth-smallest-amount-with-single-denomination-combination/
 *
 * You are given an integer array coins representing coins of different denominations and an integer k.
 *
 * You have an infinite number of coins of each denomination.
 * However, you are not allowed to combine coins of different denominations.
 *
 * Return the kth smallest amount that can be made using these coins.
 */

public class Solution {

    public long findKthSmallest(int[] coins, int k) {
        long mi = Integer.MAX_VALUE;
        for (var coin : coins) mi = Math.min(mi, coin);

        long l = k - 1, r = mi * k;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (check(mid, coins, k)) r = mid;
            else l = mid;
        }
        return r;
    }

    private boolean check(long m, int[] coins, int k) {
        long cnt = 0; int mask = 1 << coins.length;
        next: for (int i = 1; i < mask; i++) {
            long x = 1;
            for (int j = 0; j < coins.length; j++) {
                if ((i & (1 << j)) != 0) {
                    x = lcm(x, coins[j]);
                    if (x > m) continue next;
                }
            }
            cnt += Integer.bitCount(i) % 2 == 1 ? m / x : -m / x;
        }
        return cnt >= k;
    }

    private long gcd(long a, long b) { return a % b == 0 ? b : gcd(b, a % b); }
    private long lcm(long a, long b) { return a * b / gcd(a, b); }

    public static void main(String[] args) {
    }

}
