package lcp.p74;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * LCP 74. 最强祝福力场
 *
 * https://leetcode.cn/problems/xepqZ5/
 *
 * 小扣在探索丛林的过程中，无意间发现了传说中“落寞的黄金之都”。而在这片建筑废墟的地带中，小扣使用探测仪
 * 监测到了存在某种带有「祝福」效果的力场。 经过不断的勘测记录，小扣将所有力场的分布都记录了下来。
 *
 * forceField[i] = [x,y,side] 表示第 i 片力场将覆盖以坐标 (x,y) 为中心，边长为 side 的正方形区域。
 *
 * 若任意一点的 力场强度 等于覆盖该点的力场数量，请求出在这片地带中 力场强度 最强处的 力场强度。
 *
 * 注意：
 *
 * 力场范围的边缘同样被力场覆盖。
 */

public class Solution {

    public int fieldOfGreatestBlessing(int[][] forceField) {
        // [x, y1, y2]
        PriorityQueue<long[]> adds = new PriorityQueue<>(Comparator.comparingLong(v -> v[0]));
        PriorityQueue<long[]> rems = new PriorityQueue<>(Comparator.comparingLong(v -> v[0]));
        for (var ff : forceField) {
            adds.add(new long[]{ff[0] * 2L - ff[2], ff[1] * 2L - ff[2], ff[1] * 2L + ff[2]});
            rems.add(new long[]{ff[0] * 2L + ff[2] + 1, ff[1] * 2L - ff[2], ff[1] * 2L + ff[2]});
        }

        int ans = 0;
        TreeMap<Long, Integer> diff = new TreeMap<>();
        while (!adds.isEmpty() || !rems.isEmpty()) {
            long x = Long.MAX_VALUE;
            if (!adds.isEmpty()) x = Math.min(x, adds.peek()[0]);
            if (!rems.isEmpty()) x = Math.min(x, rems.peek()[0]);

            while (!adds.isEmpty() && adds.peek()[0] <= x) {
                var curr = adds.remove();
                diff.merge(curr[1], 1, Integer::sum);
                diff.merge(curr[2] + 1, -1, Integer::sum);
            }

            while (!rems.isEmpty() && rems.peek()[0] <= x) {
                var curr = rems.remove();
                diff.merge(curr[1], -1, Integer::sum);
                diff.merge(curr[2] + 1, 1, Integer::sum);
            }

            // 计算diff数组, 得到重叠最大的数值
            var curr = 0;
            for (var v : diff.entrySet()) {
                ans = Math.max(ans, (curr += v.getValue()));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().fieldOfGreatestBlessing(new int[][]{
            {0,0,1},{1,0,1}
        }) == 2;

        assert new Solution().fieldOfGreatestBlessing(new int[][]{
            {4,4,6},{7,5,3},{1,6,2},{5,6,3}
        }) == 3;

        assert new Solution().fieldOfGreatestBlessing(new int[][]{
            {565,34,242},{299,628,870},{724,673,221},{128,267,917},
            {561,488,696},{341,71,604},{835,92,846},{945,696,973},
            {554,776,430},{209,134,594},{987,898,282},{819,154,462},
            {618,946,505},{561,40,677},{602,863,657},{295,83,718},
            {456,920,939},{94,717,813},{611,946,366},{818,850,85},
            {395,554,333},{325,700,628},{590,401,119},{248,858,499},
            {298,723,602},{189,538,646},{194,283,344},{842,535,866},
            {192,832,195}
        }) == 15;
    }

}
