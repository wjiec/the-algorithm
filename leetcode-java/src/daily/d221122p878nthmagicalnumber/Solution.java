package daily.d221122p878nthmagicalnumber;

/**
 * 878. Nth Magical Number
 *
 * https://leetcode.cn/problems/nth-magical-number/
 *
 * A positive integer is magical if it is divisible by either a or b.
 *
 * Given the three integers n, a, and b, return the nth magical number.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int nthMagicalNumber(int n, int a, int b) {
        int MOD = 1_000_000_007, ans = 0;
        long l = Math.min(a, b), r = Math.max(a, b) * (long) n;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (count(a, b, mid) >= n) {
                ans = (int) (mid % MOD);
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    public long count(long a, long b, long n) {
        long ac = n / a, bc = n / b, abc = n / lcm(a, b);
        return ac + bc - abc;
    }

    public long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
    private long lcm(long a, long b) { return a / gcd(a, b) * b; }

    public static void main(String[] args) {
        assert new Solution().nthMagicalNumber(1, 2, 3) == 2;
        assert new Solution().nthMagicalNumber(4, 2, 3) == 6;

        assert new Solution().nthMagicalNumber(100101011, 22, 55) == 835185191;
    }

}
