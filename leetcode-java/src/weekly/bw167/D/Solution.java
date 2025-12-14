package weekly.bw167.D;

import common.Tag;

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

    @Tag({"二分图", "二分图染色"})
    public int maxPartitionFactor(int[][] points) {
        if (points.length == 2) return 0;
        // 将点划分到两个组内, 每个组的划分因子是与这个组内的所有点之间的最小距离
        // 求最大可能划分因子

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
        // 由于我们要求同组内的任意点的最小曼哈顿距离都大于等于 lower
        //  - 我们我们在不满足要求的点上连一条边, 使得这两个点必须在二分图中被分到不同的组里
        //  - 最后我们检查建图之后是否满足二分图划分即可

        // 可能包含多个连通块
        int[] colors = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            if (colors[i] == 0 && !dfs(points, colors, i, 1, lower)) return false;
        }
        return true;
    }

    // 检查是否是二分图
    private boolean dfs(int[][] points, int[] colors, int i, int c, int lower) {
        colors[i] = c;
        for (int j = 0; j < points.length; j++) {
            if (j == i || Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]) >= lower) continue;
            if (colors[j] == c || (colors[j] == 0 && !dfs(points, colors, j, -c, lower))) return false;
        }
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
