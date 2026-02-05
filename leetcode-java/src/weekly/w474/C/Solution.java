package weekly.w474.C;

/**
 * Q3. Minimum Time to Complete All Deliveries
 *
 * https://leetcode.cn/contest/weekly-contest-474/problems/minimum-time-to-complete-all-deliveries/
 *
 * You are given two integer arrays of size 2: d = [d1, d2] and r = [r1, r2].
 *
 * Two delivery drones are tasked with completing a specific number of deliveries.
 * Drone i must complete di deliveries.
 *
 * Each delivery takes exactly one hour and only one drone can make a delivery at any given hour.
 *
 * Additionally, both drones require recharging at specific intervals during which they cannot
 * make deliveries. Drone i must recharge every ri hours (i.e. at hours that are multiples of ri).
 *
 * Return an integer denoting the minimum total time (in hours) required to complete all deliveries.
 */

public class Solution {

    public long minimumTime(int[] d, int[] r) {
        if (r[0] == r[1]) {
            long D = d[0] + d[1], R = r[0];
            // 两个的充电时间一致, 无人机完成送货所需要的最小时间计算:
            //  - 无人机每 r 小时可以完成 r' = r - 1 次任务
            //  - 也就是一共需要 g = ceiling(d / r') 次
            //  - 最后一组不需要充电, 也就是只需要 last = d % r'
            //      - 如果最后一组正好就是 r' 次的话, 也是不需要充电的
            //      - 考虑最后一组可能为 r' 的话, 也就是 last = ((d - 1) % r') + 1
            // 一共就是 g - 1 组个 r 小时加上最后的 last 小时
            long r1 = R - 1, g = (D + r1 - 1) / r1, last = ((D - 1) % r1) + 1;
            return (g - 1) * R + last;
        }

        // 剩下考虑使用二分的方法计算, 时间越长肯定就越可以完成
        //  - 剩下问题变成在 t 时间可以完成所有任务么?
        //
        // 对于任意的无人机, 在 t 时间内充电时间有 b = t / r 个
        //  - 也就是对于两个无人机: b1 = t / r1, b2 = t / r2
        //  - 在 b1 和 b2 里有重复的位置是两个无人机都不能使用的: b3 = t / lcm(r1, r2)
        //  - 此时 t - b1 - b2 + b3 就是两个无人机都可以送货的时刻
        //  - 此时 b1 表示无人机 1 的充电时刻, 而 b1 - b3 表示无人机 2 可以在无人机 1 充电时送货
        //  - 此时 b2 表示无人机 2 的充电时刻, 而 b2 - b3 表示无人机 1 可以在无人机 2 充电时送货
        //
        // 总可利用时间是: t - b1 - b2 + b3 + (b1 - b3) + (b2 - b3)
        //  - 如果 r1 = 2 且 r2 = 4 的话, lcm(r1, r2) == 4, 那么 r1 就不能在 r2 充电的时候送货
        //  - 也就是 r2 % r1 != 0, 那么无人机 1 就可以在 b2 - b3 时刻进行充电
        //  - 也就是 r1 % r2 != 0, 那么无人机 2 就可以在 b1 - b3 时刻进行充电
        //
        // 优先把无人机安排在另一个无人机充电的时候送货, 剩下再安排在通用时间里
        //  - 剩余的无人机 1: d1 - (r2 % r1 != 0 ? b2 - b3 : 0), 且不能少于 0
        //  - 剩余的无人机 2: d2 - (r1 % r2 != 0 ? b1 - b3 : 0), 且不能少于 0
        //
        // 也就是需要满足 t - b1 - b2 + b3 >= d1 - (r2 % r1 != 0 ? b2 - b3 : 0) + d2 - (r1 % r2 != 0 ? b1 - b3 : 0)
        //
        // 在 r = 2 时, 消耗的时间最多, r 越大则充电时间越少
        long bl = (d[0] + d[1]) - 1, br = (d[0] + d[1]) * 2L - 1;
        while (bl + 1 < br) {
            long mid = bl + (br - bl) / 2;
            if (check(d[0], r[0], d[1], r[1], mid)) br = mid;
            else bl = mid;
        }
        return br;
    }

    private boolean check(long d1, long r1, long d2, long r2, long t) {
        long b1 = t / r1, b2 = t / r2, b3 = t / lcm(r1, r2);
        return t - b1 - b2 + b3 >= Math.max(d1 - (r2 % r1 != 0 ? b2 - b3 : 0), 0) + Math.max(d2 - (r1 % r2 != 0 ? b1 - b3 : 0), 0);
    }

    private long gcd(long a, long b) { return a % b == 0 ? b : gcd(b, a % b); }
    private long lcm(long a, long b) { return a / gcd(a, b) * b; }

    public static void main(String[] args) {
        assert new Solution().minimumTime(new int[]{2, 2}, new int[]{3, 4}) == 4;
        assert new Solution().minimumTime(new int[]{1, 3}, new int[]{2, 4}) == 5;

        assert new Solution().minimumTime(new int[]{3, 1}, new int[]{2, 3}) == 5;
        assert new Solution().minimumTime(new int[]{1, 3}, new int[]{2, 2}) == 7;
        assert new Solution().minimumTime(new int[]{2, 1}, new int[]{3, 4}) == 3;
    }

}
