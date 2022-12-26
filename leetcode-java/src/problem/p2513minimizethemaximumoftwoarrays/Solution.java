package problem.p2513minimizethemaximumoftwoarrays;

/**
 * 2513. Minimize the Maximum of Two Arrays
 *
 * https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/
 *
 * We have two arrays arr1 and arr2 which are initially empty. You need to add positive
 * integers to them such that they satisfy all the following conditions:
 *
 * arr1 contains uniqueCnt1 distinct positive integers, each of which is not divisible by divisor1.
 * arr2 contains uniqueCnt2 distinct positive integers, each of which is not divisible by divisor2.
 * No integer is present in both arr1 and arr2.
 *
 * Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum possible maximum
 * integer that can be present in either array.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimizeSet(int d1, int d2, int c1, int c2) {
        long l = 1, r = (c1 + c2) * 2L;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (check(d1, d2, c1, c2, mid)) r = mid;
            else l = mid + 1;
        }
        return (int) l;
    }

    private boolean check(long d1, long d2, long c1, long c2, long v) {
        long x = lcm(d1, d2);
        long l1 = Math.max(0, c1 - v / d2 + v / x);
        long l2 = Math.max(0, c2 - v / d1 + v / x);
        return v - v / d1 - v / d2 + v / x >= l1 + l2;
    }

    // 最大公约数
    public static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }

    // 最小公倍数
    public static long lcm(long a, long b) { return a / gcd(a, b) * b; }

    public static void main(String[] args) {
        assert new Solution().minimizeSet(92761, 48337, 208563424, 9115778) == 217679202;
        assert new Solution().minimizeSet(5, 2, 2, 20) == 39;
        assert new Solution().minimizeSet(16, 14, 12, 8) == 20;
        assert new Solution().minimizeSet(38, 77, 121231, 3123123) == 3245463;

        assert new Solution().minimizeSet(2, 7, 1, 3) == 4;
        assert new Solution().minimizeSet(3, 5, 2, 1) == 3;
        assert new Solution().minimizeSet(2, 4, 8, 2) == 15;
    }

}
