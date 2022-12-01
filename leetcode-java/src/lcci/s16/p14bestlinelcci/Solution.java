package lcci.s16.p14bestlinelcci;

import common.Checker;

/**
 * 面试题 16.14. 最佳直线
 *
 * https://leetcode.cn/problems/best-line-lcci/
 *
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。
 * 请找出一条直线，其通过的点的数目最多。
 *
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，
 * 若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 */

public class Solution {

    public int[] bestLine(int[][] points) {
        int[] ans = new int[2];
        int max = 0, n = points.length;
        for (int p1 = 0; p1 < n; p1++) {
            int x1 = points[p1][0];
            int y1 = points[p1][1];

            for (int p2 = p1 + 1; p2 < n; p2++) {
                int x2 = points[p2][0];
                int y2 = points[p2][1];

                int count = 0, ci = 0;
                int[] curr = new int[2];

                for (int i = 0; i < n; i++) {
                    if (inline(x1, y1, x2, y2, points[i][0], points[i][1])) {
                        count++;
                        if (ci < curr.length) curr[ci++] = i;
                    }
                }

                if (count > max) { ans = curr; max = count; }
                if (count == max && curr[0] < ans[0]) ans = curr;
                if (count == max && curr[0] == ans[0] && curr[1] < ans[1]) ans = curr;
            }
        }

        return ans;
    }

    private boolean inline(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x1 - x2) * (y3 - y2) == (x3 - x2) * (y1 - y2);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().bestLine(new int[][]{{0,0},{1,1},{1,0},{2,0}}), new int[]{0, 2});
    }

}
