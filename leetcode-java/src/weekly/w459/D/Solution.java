package weekly.w459.D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static ability.Ability.Math.gcd;

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
        int n = points.length;
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));
        // 枚举两个点组成一条线, 再枚举两条线平行组成一个梯形
        //  - 不同的两点有 499 * (1 + 499) / 2 = 124750 条边
        //  - 将所有边按照斜率进行分组, 并计算组合数
        //
        // 枚举的两条边不能在同一条直线上
        //  - 特殊情况下, 如果所有的点都在同一条直线上, 判断是否能构成四边形会很复杂
        //      - 计算按照这个斜率当 x = 0 时, y 落在哪里来判断是否共线
        Map<Frac, Map<Frac, Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                Frac slope = slope(x1, y1, x2, y2);
                g.computeIfAbsent(slope, k -> new HashMap<>())
                    .merge(intercept(slope, x1, y1), 1, Integer::sum);
            }
        }

        int ans = 0;
        for (var groups : g.values()) {
            int total = 0;
            for (var v : groups.values()) total += v;
            for (var v : groups.values()) {
                total -= v;
                ans += v * total;
            }
        }
        return ans;
    }

    private record Frac(int numerator, int denominator) {}

    private Frac slope(int x1, int y1, int x2, int y2) {
        return frac(y2 - y1, x2 - x1);
    }

    private Frac intercept(Frac slope, int x1, int y1) {
        if (slope.denominator == 0) return new Frac(x1, 0);
        return frac(slope.denominator * y1 - slope.numerator * x1, slope.denominator);
    }

    private Frac frac(int dy, int dx) {
        if (dy == 0) return new Frac(0, Integer.MAX_VALUE);
        if (dx == 0) return new Frac(Integer.MAX_VALUE, 0);

        int gcd = gcd(Math.abs(dy), Math.abs(dx));
        return new Frac(dy / gcd, dx / gcd);
    }

    public static void main(String[] args) {
        assert new Solution().countTrapezoids(new int[][]{{71,-89},{-75,-89},{-9,11},{-24,-89},{-51,-89},{-77,-89},{42,11}}) == 10;

        assert new Solution().countTrapezoids(new int[][]{{-3,2},{3,0},{2,3},{3,2},{2,-3}}) == 2;
        assert new Solution().countTrapezoids(new int[][]{{0,0},{1,0},{0,1},{2,1}}) == 1;
    }

}
