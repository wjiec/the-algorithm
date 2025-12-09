package weekly.bw167.D;

import java.util.ArrayList;
import java.util.List;

/**
 * Q4. Maximum Partition Factor
 *
 * https://leetcode.cn/contest/biweekly-contest-167/problems/maximum-partition-factor/
 *
 * You are given a 2D integer array points, where points[i] = [xi, yi] represents
 * the coordinates of the ith point on the Cartesian plane.
 *
 * The Manhattan distance between two points points[i] = [xi, yi] and points[j] = [xj, yj] is |xi - xj| + |yi - yj|.
 *
 * Split the n points into exactly two non-empty groups. The partition factor of a split is the
 * minimum Manhattan distance among all unordered pairs of points that lie in the same group.
 *
 * Return the maximum possible partition factor over all valid splits.
 *
 * Note: A group of size 1 contributes no intra-group pairs. When n = 2 (both groups size 1),
 * there are no intra-group pairs, so define the partition factor as 0.
 */

public class Solution {

    private record Point(int x, int y) {}

    public int maxPartitionFactor(int[][] points) {
        if (points.length == 2) return 0;
        // 将点划分到两个组内, 每个组的划分因子是与这个组内的所有点之间的最小距离
        // 求最大可能划分因子

        // 二分划分, 枚举最大可能划分因子 k, 也就是
        //  - 每个点只能加入 A 组或者 B 组
        //  - 新加入的点必须和组内的其他点的最小距离为 k

        // 将曼哈顿距离转换为切比雪夫距离
        //  - 也就是对于点 (x, y) 转换到切比雪夫距离为 (x + y, x - y)
        //  - 对于两点之间的切比雪夫距离为 max(|x_a - x_b|, |y_a - y_b|)
        // 此时在一个组内加入一个点 (x_c, y_c), 我们只需要找到 min(|x_i - x_c|) 和 min(|y_i - y_c|) 即可
        Point[] pts = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pts[i] = new Point(points[i][0] + points[i][1], points[i][0] - points[i][1]);
        }

        int l = 0, r = (int) (1e9 + 1);
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(points, mid)) l = mid;
            else r = mid;
        }
        System.out.println(l);
        return l;
    }

    // 是否能将所有点分成两组, 且每组内的最小距离为 lower
    private boolean check(int[][] points, int lower) {
        List<int[]> groupA = new ArrayList<>();
        List<int[]> groupB = new ArrayList<>();
        for (var point : points) {
            // 选择将当前点加入到 a 组
            if (minDistance(point, groupA) >= lower) groupA.add(point);
            else if (minDistance(point, groupB) >= lower) groupB.add(point);
            else return false;
        }
        return true;
    }

    private int minDistance(int[] p, List<int[]> group) {
        // 每个点的计算方式是 max{ |p.x - x|, |p.y - y| }
        //  - 我们需要找到 p 与这里面所有点的最小距离
        // 也就是 min{ max{ |p.x - x_1|, |p.y - y_1| }, max{ |p.x - x_2|, |p.y - y_2| } }
        int ans = Integer.MAX_VALUE;
        for (var x : group) ans = Math.min(ans, Math.abs(p[0] - x[0]) + Math.abs(p[1] - x[1]));
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxPartitionFactor(new int[][]{
            {95922,-85190},{25931,81942},{-31245,-30278},{34636,-69149},{2662,-57718}
        }) == 104752;

        assert new Solution().maxPartitionFactor(new int[][]{
            {-62605,90618},{-35991,-7552},{19322,3983},{30761,15721}
        }) == 90025;

        assert new Solution().maxPartitionFactor(new int[][]{{0, 0}, {0, 2}}) == 0;
        assert new Solution().maxPartitionFactor(new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}}) == 4;
        assert new Solution().maxPartitionFactor(new int[][]{{0, 0}, {0, 1}, {10, 0}}) == 11;
    }

}
