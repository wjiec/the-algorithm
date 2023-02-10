package problem.p483smallestgoodbase;

import java.math.BigInteger;

/**
 * 483. Smallest Good Base
 *
 * https://leetcode.cn/problems/smallest-good-base/
 *
 * Given an integer n represented as a string, return the smallest good base of n.
 *
 * We call k >= 2 a good base of n, if all digits of n base k are 1's.
 */

public class Solution {

    public String smallestGoodBase(String n) {
        long val = Long.parseLong(n);
        for (int ones = 63; ones > 0; ones--) {
            long ans = check(ones, val);
            if (ans > 0) return String.valueOf(ans);
        }
        return "";
    }

    // 检查使用 ones 个 1 是否能构造出 n
    private long check(int ones, long n) {
        long l = 2, r = n;
        while (l < r) {
            long mid = l + (r - l) / 2;
            long curr = pow(mid, ones, n);
            if (curr == n) return mid;
            if (curr < n) l = mid + 1;
            else r = mid;
        }

        return -1;
    }

    // 计算 b^0 + b^1 + b^2 + b^l 是否大于等于 n
    private long pow(long b, int l, long n) {
        BigInteger curr = BigInteger.ONE;
        BigInteger val = BigInteger.valueOf(n);
        BigInteger base = BigInteger.valueOf(b);
        for (int i = 1; i < l; i++) {
            curr = curr.add(base);
            base = base.multiply(BigInteger.valueOf(b));
            if (curr.compareTo(val) > 0) break;
        }

        int cmp = curr.compareTo(val);
        if (cmp <= 0) return curr.longValue();
        return Long.MAX_VALUE;
    }

    public static void main(String[] args) {
        assert new Solution().smallestGoodBase("31").equals("2");
        assert new Solution().smallestGoodBase("2251799813685247").equals("2");

        assert new Solution().smallestGoodBase("7").equals("2");
        assert new Solution().smallestGoodBase("13").equals("3");
        assert new Solution().smallestGoodBase("4681").equals("8");
        assert new Solution().smallestGoodBase("1000000000000000000").equals("999999999999999999");
    }

}
