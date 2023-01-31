package weekly.w330.B;

/**
 * 2550. Count Collisions of Monkeys on a Polygon
 *
 * https://leetcode.cn/problems/count-collisions-of-monkeys-on-a-polygon/
 *
 * There is a regular convex polygon with n vertices. The vertices are labeled from 0 to n - 1
 * in a clockwise direction, and each vertex has exactly one monkey.
 *
 * Each monkey moves simultaneously to a neighboring vertex. A neighboring vertex for a vertex i can be:
 *
 * the vertex (i + 1) % n in the clockwise direction, or
 * the vertex (i - 1 + n) % n in the counter-clockwise direction.
 * A collision happens if at least two monkeys reside on the same vertex after the movement.
 *
 * Return the number of ways the monkeys can move so that at least one collision happens.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that each monkey can only move once.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int monkeyMove(int n) {
        return ((int) pow(2, n, 1_000_000_007) - 2 + 1_000_000_007) % 1_000_000_007;
    }

    // 快速幂算法, 求 (base ^ pow) % mod 的值
    public static long pow(long base, long pow, long mod) {
        long ans = 1;
        while (pow > 0) {
            if ((pow & 1L) != 0) {
                ans = (ans * base) % mod;
            }

            base = (base * base) % mod;
            pow >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().monkeyMove(500000003) == 1000000006;

        assert new Solution().monkeyMove(3) == 6;
        assert new Solution().monkeyMove(4) == 14;
    }

}
