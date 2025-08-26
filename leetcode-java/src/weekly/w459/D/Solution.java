package weekly.w459.D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Count Number of Trapezoids II
 *
 * https://leetcode.cn/contest/weekly-contest-459/problems/count-number-of-trapezoids-ii/
 *
 * You are given a 2D integer array points where points[i] = [xi, yi] represents the
 * coordinates of the ith point on the Cartesian plane.
 *
 * Return the number of unique trapezoids that can be formed by choosing any four distinct points from points.
 *
 * A trapezoid is a convex quadrilateral with at least one pair of parallel sides.
 *
 * Two lines are parallel if and only if they have the same slope.
 */

public class Solution {

    // 4 <= points.length <= 500
    // –1000 <= xi, yi <= 1000
    public int countTrapezoids(int[][] points) {
        // 枚举两个点组成一条线, 再枚举两条线平行组成一个梯形
        //  - 不同的两点有 499 * (1 + 499) / 2 = 124750 条边
        //  - 将所有边按照斜率进行分组, 并计算组合数
        //
        // 枚举的两条边不能在同一条直线上
        //  - 特殊情况下, 如果所有的点都在同一条直线上, 判断是否能构成四边形会很复杂
        //      - 计算按照这个斜率当 x = 0 时, y 落在哪里来判断是否共线
        //
        // 如果是个平行四边形, 两条对边也需要进行去重
        //  - 按照中点去重
        int n = points.length;
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));

        // {k: {b: c}} 表示斜率为 k 且截距为 b 的直线个数
        Map<Double, Map<Double, Integer>> m1 = new HashMap<>();
        // {m: {k: c}} 表示直线的中点为 m 且斜率为 k 的直线个数
        Map<Integer, Map<Double, Integer>> m2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1];

                double dx = x2 - x1, dy = y2 - y1;
                // 求斜率 k = dy / dx, 如果 dx = 0 也就是直线垂直于 x 轴, 我们使用 INF 表示
                double k = dx == 0 ? Double.MAX_VALUE : (dy / dx);
                // 求截距 b = y - kx = y - dy / dx * x = y - (dy * x) / dx = (y * dx - x * dy) / dx
                //  - 如果直线垂直于 x 轴, 则直接使用 x 值作为截距
                double b = dx == 0 ? x1 : ((y1 * dx - x1 * dy) / dx);
                // 计算直线的中点并压缩
                int mid = (x1 + x2 + 2500) << 16 | (y1 + y2 + 2500);

                // 归一化 k 和 b
                if (k == -0) k = 0;
                if (b == -0) b = 0;

                m1.computeIfAbsent(k, kk -> new HashMap<>()).merge(b, 1, Integer::sum);
                m2.computeIfAbsent(mid, kk -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;
        for (var group : m1.values()) {
            int sum = 0;
            for (var v : group.values()) {
                ans += sum * v; sum += v;
            }
        }

        // 去掉平行四边形的重复统计
        for (var group : m2.values()) {
            int sum = 0;
            for (var v : group.values()) {
                ans -= sum * v; sum += v;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countTrapezoids(new int[][]{{71,-89},{-75,-89},{-9,11},{-24,-89},{-51,-89},{-77,-89},{42,11}}) == 10;

        assert new Solution().countTrapezoids(new int[][]{{-3,2},{3,0},{2,3},{3,2},{2,-3}}) == 2;
        assert new Solution().countTrapezoids(new int[][]{{0,0},{1,0},{0,1},{2,1}}) == 1;
    }

}
