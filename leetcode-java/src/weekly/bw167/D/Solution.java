package weekly.bw167.D;

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

        // 二分处理, 枚举最大可能划分因子 k, 也就是
        //  - 每个点只能加入 A 组或者 B 组
        //  - 新加入的点必须和组内的其他点的最小距离为 k
        // 如果当前点既可以加入 A 组也可以加入 B 组的话, 我们需要找到一个最优的情况

        // 现在问题变成: 是否可以将一堆点分成 2 个组, 并且每个组之内任意两点的距离不超过 k
        //  - 每个点只能属于单个组
        //  - 可能有一个点即可以加入 A 组, 也可以加入 B 组

        // 将曼哈顿距离转换为切比雪夫距离
        //  - 也就是对于点 (x, y) 转换到切比雪夫距离为 (x + y, x - y)
        //  - 对于两点之间的切比雪夫距离为 max(|x_a - x_b|, |y_a - y_b|)
        //
        // 此时在一个组内加入一个点 (x_c, y_c), 我们需要找到距离该点最近的距离是多少
        //  - 也就是对于组内的所有点 i, 计算 min{ max(|x_c - x_i|, |y_c - y_i|) }

        int l = 0, r = (int) (1e9 + 1);
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(points, mid)) l = mid;
            else r = mid;
        }
        return l;
    }

    // 是否能将所有点分成两组, 且每组内的最小距离为 lower
    private boolean check(int[][] points, int lower) {
        return true;
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
