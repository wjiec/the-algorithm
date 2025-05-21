package weekly.w448.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 3538. Merge Operations for Minimum Travel Time
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/merge-operations-for-minimum-travel-time/
 *
 * You are given a straight road of length l km, an integer n, an integer k, and two integer
 * arrays, position and time, each of length n.
 *
 * The array position lists the positions (in km) of signs in strictly increasing
 * order (with position[0] = 0 and position[n - 1] = l).
 *
 * Each time[i] represents the time (in minutes) required to travel 1 km
 * between position[i] and position[i + 1].
 *
 * You must perform exactly k merge operations. In one merge, you can choose
 * any two adjacent signs at indices i and i + 1 (with i > 0 and i + 1 < n) and:
 *
 * Update the sign at index i + 1 so that its time becomes time[i] + time[i + 1].
 * Remove the sign at index i.
 *
 * Return the minimum total travel time (in minutes) to travel from 0 to l after exactly k merges.
 */

public class Solution {

    // 恰好合并 k 次, 使得总旅行时间最小
    public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
        // 每段的长度为 [a, b, c, d, e], 对应的时间为 [v, w, x, y, z]
        //  - 不合并情况下的总时间为 T = av + bw + cx + dy + ez
        // 如果合并 b 和 c, 总时间变为 av + (b+c) * w + d * (y + x) + ez
        //  - 分解之后 = av + bw + cw + dy + dx + ez
        //           = av + bw +    + dy + ez + cw + dx
        //           = T + cw + dx - cx
        //           = T + c * (w - x) + dx
        //
        // 此时每段的长度变为 [a, b+c, d, e], 对应的时间为 [v, w, x+y, z]
        //  - 如果接着合并 b+c 和 d, 总时间变为 av + (b+c+d) * w + e * (x+y+z)
        //      - 分解之后 = av + bw + cw + dw + ex + ey + ez
        //               = av + bw +                    + ez + cw + dw + ex + ey
        //               = T + cw + dw + ex + ey - cx - dy
        //               = T + c * (w - x) + d * (w - y) + ex + ey
        //
        // 此时每段的长度变为 [a, b+c+d, e], 对应的时间为 [v, w, x+y+z]
        //  - 如果接着合并 b+c+d 和 e, 总时间变为 av + (b+c+d+e) * w + 0 * (w + x + y + z)
        //      - 分解之后 = av + bw + cw + dw + ew
        //               = av + bw +                   + cw + dw + ew
        //               = T + cw + dw + ew - cx - dy - ez
        //               = T + c * (w - x) + d * (w - y) + e * (w - z)
        //
        // 分析可得, 如果合并长度为 (k1, k2 ..., kn) 和时间为 (t1, t2, ..., tn) 的段
        //  总时间变为 = T + ()

        // 先求出在不合并情况下的所有可能值
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            segments.add(new Segment(position[i + 1] - position[i], time[i]));
        }

        System.out.println(dfs(segments, 0, 0, 0, k));
        return dfs(segments, 0, 0, 0, k);
    }

    private record Segment (int len, int time) {
        public int sum(int at) { return len * (at + time); }
    }

    private int dfs(List<Segment> segments, int i, int pt, int at, int k) {
        if (i == segments.size()) return k == 0 ? 0 : Integer.MAX_VALUE / 2;
        var curr = segments.get(i);

        // 当前段选择不合并
        int ans = curr.sum(at) + dfs(segments, i + 1, curr.time, 0, k);
        // 也可以选择合并到前一段里
        if (k > 0) ans = Math.min(ans, curr.time * pt + dfs(segments, i + 1, pt, at + curr.time, k - 1));

        return ans;
    }

    public static void main(String[] args) {
        // 3 5, 5 8, 2 3
        // 8 5,      2, 11
        assert new Solution().minTravelTime(10, 4, 1, new int[]{0,3,8,10}, new int[]{5,8,3,6}) == 62;
    }

}
