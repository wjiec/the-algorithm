package weekly.w493.D;

import common.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q4. Maximum Points Activated with One Addition
 *
 * https://leetcode.cn/contest/weekly-contest-493/problems/maximum-points-activated-with-one-addition/
 *
 * You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point.
 * All coordinates in points are distinct.
 *
 * If a point is activated, then all points that have the same x-coordinate or y-coordinate become activated as well.
 *
 * Activation continues until no additional points can be activated.
 *
 * You may add one additional point at any integer coordinate (x, y) not already present in points.
 * Activation begins by activating this newly added point.
 *
 * Return an integer denoting the maximum number of points that can be activated, including the newly added point.
 */

public class Solution {

    private final Map<Long, Long> parent = new HashMap<>();

    private long find(long x) {
        if (!parent.containsKey(x)) return x;

        long px = parent.get(x);
        if (px != x) parent.put(x, find(px));
        return parent.get(x);
    }

    private void union(long a, long b) {
        parent.put(find(a), find(b));
    }

    @Tag({"二维并查集", "中介并查集"})
    public int maxActivated(int[][] points) {
        final long GAP = 3_000_000_007L;
        // 某个点被激活, 则具有相同的 x 或 y 的点也都会被激活(连通)
        //  - 使用"中介"来连接所有的点, 也就是每个点连接到坐标轴的数字上
        for (var point : points) union(point[0], point[1] + GAP);

        // 统计所有连通块的大小
        Map<Long, Integer> size = new HashMap<>();
        for (var p : points) size.merge(find(p[0]), 1, Integer::sum);

        // 找到两个最大的连通块
        List<Integer> sorted = new ArrayList<>(size.values());
        if (sorted.size() == 1) return sorted.get(0) + 1;

        sorted.sort(Integer::compare);
        return sorted.get(sorted.size() - 1) + sorted.get(sorted.size() - 2) + 1;
    }

    public static void main(String[] args) {
    }

}
