package lcci.s16.p3intersectionlcci;

import ability.Equation;
import common.Checker;
import common.TODO;

/**
 * 面试题 16.03. 交点
 *
 * https://leetcode.cn/problems/intersection-lcci/
 *
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 *
 * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
 */

public class Solution {

    @TODO(tips = "数学")
    @TODO(tips = "非正确答案")
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int sx1 = start1[0], sy1 = start1[1], ex1 = end1[0], ey1 = end1[1];
        int sx2 = start2[0], sy2 = start2[1], ex2 = end2[0], ey2 = end2[1];

        double k1 = Equation.Linear.slope(sx1, sy1, ex1, ey1);
        double k2 = Equation.Linear.slope(sx2, sy2, ex2, ey2);
        double b1 = Equation.Linear.yIntercept(sx1, sy1, ex1, ey1);
        double b2 = Equation.Linear.yIntercept(sx2, sy2, ex2, ey2);
        if (k1 == k2) {
            if (!Equation.isNaN(k1) && b1 != b2) return null; // 平行线
            return new double[]{
                Math.max(Math.max(sx1, sx2), Math.max(ex1, ex2)),
                Math.min(Math.min(sy1, sy2), Math.min(ey1, ey2))
            };
        }

        // k1 * x + b1 = y1
        // k2 * x + b2 = y2
        // k1 * x + b1 = k2 * x + b2
        // x = (b2 - b1) / (k1 - k2)

        double x = (b2 - b1) / (k1 - k2);
        double y = k1 * x + b1;

        if (inRange(sx1, ex1, x) && inRange(sy1, ey1, y) && inRange(sx2, ex2, x) && inRange(sy2, ey2, y)) {
            return new double[]{x, y};
        }

        return new double[0];
    }

    private boolean inRange(double start, double end, double v) {
        return Math.min(start, end) <= v && v <= Math.max(start, end);
    }

    public static void main(String[] args) {
        Checker.check(new Solution().intersection(new int[]{0, 3}, new int[]{0, 6}, new int[]{0, 1}, new int[]{0, 5}), new double[]{0., 3.});

        Checker.check(new Solution().intersection(new int[]{0, 0}, new int[]{1, 0}, new int[]{1, 1}, new int[]{0, -1}), new double[]{0.5, 0});
        Checker.check(new Solution().intersection(new int[]{0, 0}, new int[]{3, 3}, new int[]{1, 1}, new int[]{2, 2}), new double[]{1, 1});
    }

}
