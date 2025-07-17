package weekly.bw159.B;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Q2. Find Maximum Area of a Triangle
 *
 * https://leetcode.cn/contest/biweekly-contest-159/problems/find-maximum-area-of-a-triangle/
 *
 * You are given a 2D array coords of size n x 2, representing the coordinates of n points
 * in an infinite Cartesian plane.
 *
 * Find twice the maximum area of a triangle with its corners at any three elements from coords,
 * such that at least one side of this triangle is parallel to the x-axis or y-axis.
 *
 * Formally, if the maximum area of such a triangle is A, return 2 * A.
 *
 * If no such triangle exists, return -1.
 *
 * Note that a triangle cannot have zero area.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public long maxArea(int[][] coords) {
        // 需要有一条边和坐标轴平行, S = (底 * 高) / 2
        //  - 如果平行的话, 也就是底确定, 高的话就是另一点到这条边的距离
        //
        // 对于与 x 轴平行的三角形, 有如下方式
        //  - 选择最小的 x 坐标, 找到首尾两点连线作为底边
        //  - 找到最大的 y 坐标对应的点作为三角形的第三个点
        //  - 计算 (x2 - x1) * (y3 - y1)
        TreeMap<Integer, TreeSet<Integer>> xAxis = new TreeMap<>();
        TreeMap<Integer, TreeSet<Integer>> yAxis = new TreeMap<>();
        for (var coord : coords) {
            xAxis.computeIfAbsent(coord[0], k -> new TreeSet<>()).add(coord[1]);
            yAxis.computeIfAbsent(coord[1], k -> new TreeSet<>()).add(coord[0]);
        }
        if (xAxis.size() == 1 || yAxis.size() == 1) return -1;

        long ans = -1;

        // 计算平行于 x 轴的三角形
        for (var kv : yAxis.entrySet()) {
            if (kv.getValue().size() == 1) continue;

            long y3 = kv.getKey();
            ans = Math.max(ans, (kv.getValue().last() - kv.getValue().first()) * Math.abs(y3 - yAxis.lastKey()));
            ans = Math.max(ans, (kv.getValue().last() - kv.getValue().first()) * Math.abs(y3 - yAxis.firstKey()));
        }

        for (var kv : xAxis.entrySet()) {
            if (kv.getValue().size() == 1) continue;

            long x3 = kv.getKey();
            ans = Math.max(ans, (kv.getValue().last() - kv.getValue().first()) * Math.abs(x3 - xAxis.lastKey()));
            ans = Math.max(ans, (kv.getValue().last() - kv.getValue().first()) * Math.abs(x3 - xAxis.firstKey()));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxArea(new int[][]{{2,9},{2,6},{2,5}}) == -1;
        assert new Solution().maxArea(new int[][]{{1,1},{6,10},{6,5}}) == 25;
    }

}
