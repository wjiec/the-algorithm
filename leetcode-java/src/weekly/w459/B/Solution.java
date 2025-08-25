package weekly.w459.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Count Number of Trapezoids I
 *
 * https://leetcode.cn/contest/weekly-contest-459/problems/count-number-of-trapezoids-i/
 *
 * You are given a 2D integer array points, where points[i] = [xi, yi] represents
 * the coordinates of the ith point on the Cartesian plane.
 *
 * A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal
 * sides (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.
 *
 * Return the number of unique horizontal trapezoids that can be formed by
 * choosing any four distinct points from points.
 *
 * Since the answer may be very large, return it modulo 1e9 + 7.
 */

public class Solution {

    public int countTrapezoids(int[][] points) {
        // 只要平行于 X 轴的线组合而成就行
        Map<Integer, Integer> yAxis = new HashMap<>();
        for (var point : points) yAxis.merge(point[1], 1, Integer::sum);

        // 将所有点放到一个数组中, 方便处理重复问题
        long[] nums = new long[yAxis.size()]; int i = 0;
        for (var v : yAxis.values()) nums[i++] = v;

        // 我们从一个平行于 y 轴的线上任取两点组成一条直线, 与不用 y 轴上的任意两点即可组成一个水平梯形
        //  - 每条线有 n 个点, 一个可以组成 n-1, n-2, ..., 1 条不同的直线, 也就是 (n - 1) * (n - 2) / 2 条不同的直线
        final int MOD = 1_000_000_007; long total = 0;
        for (int j = 0; j < nums.length; j++) {
            // n * (a1 + an) / 2
            nums[j] = ((nums[j] - 1) * (1 + nums[j] - 1) / 2) % MOD;
            total += nums[j];
        }

        // 从前往后遍历并计算所有的可能
        long ans = 0;
        for (long edges : nums) {
            total -= edges;
            ans = (ans + (edges * total) % MOD) % MOD;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().countTrapezoids(new int[][]{{1,0},{2,0},{3,0},{2,2},{3,2}}) == 3;
        assert new Solution().countTrapezoids(new int[][]{{0,0},{1,0},{0,1},{2,1}}) == 1;
    }

}
