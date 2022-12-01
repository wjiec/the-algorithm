package lcci.s16.p13bisectsquareslcci;

import common.Checker;
import common.TODO;

/**
 * 面试题 16.13. 平分正方形
 *
 * https://leetcode.cn/problems/bisect-squares-lcci/
 *
 * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
 *
 * 每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。
 * 所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，
 * 这2个点所连成的线段一定会穿过另外2个交点）。
 *
 * 2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
 *
 * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
 */

public class Solution {

    @TODO
    public double[] cutSquares(int[] square1, int[] square2) {
        double d1 = square1[2], d2 = square2[2];

        // 过两个正方形的中心
        double mx1 = square1[0] + d1 / 2.0, mx2 = square2[0] + d2 / 2.0;
        double my1 = square1[1] + d1 / 2.0, my2 = square2[1] + d2 / 2.0;

        // 两个正方形的分割线垂直于 X 轴
        if (mx1 == mx2) {
            return new double[]{
                mx1, Math.min(square1[1], square2[1]),
                mx2, Math.max(square1[1] + d1, square2[1] + d2)
            };
        }

        // y = kx + b
        double k = (my1 - my2) / (mx1 - mx2);
        double b = my1 - k * mx1;

        double[] ans = new double[4];
        if (Math.abs(k) > 1) {
            ans[1] = Math.min(square1[1], square2[1]);
            ans[0] = (ans[1] - b) / k;
            ans[3] = Math.max(square1[1] + d1, square2[1] + d2);
            ans[2] = (ans[3] - b) / k;
        } else {
            ans[0] = Math.min(square1[0], square2[0]);
            ans[1] = k * ans[0] + b;
            ans[2] = Math.max(square1[0] + d1, square2[0] + d2);
            ans[3] = k * ans[2] + b;
        }

        if (ans[0] > ans[2]) ans = new double[]{ ans[2], ans[3], ans[0], ans[1]};
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().cutSquares(new int[]{-1, 1, 5},
            new int[]{-2, -2, 7}), new double[]{1.5,-2.0,1.5,6.0});

        assert Checker.anyOrder(new Solution().cutSquares(new int[]{-1, -1, 2},
            new int[]{0, -1, 2}), new double[]{-1,0,2,0});
    }

}
