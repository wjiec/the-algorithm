package weekly.w408.D;

import ability.Ability.UnionFind;
import common.Contest;

@Contest
public class Solution {

    // 并查集, 检查圆形是否将左上和右下边界连起来了
    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        final int TOP_LEFT_GUARD = circles.length;
        final int RIGHT_BOTTOM_GUARD = circles.length + 1;

        UnionFind uf = new UnionFind(circles.length + 2);
        for (int i = 0; i < circles.length; i++) {
            long x1 = circles[i][0], y1 = circles[i][1], r1 = circles[i][2];
            // 圆与一条直线的关系可以通过计算圆心到直线的距离,并将其与圆的半径进行比较
            //  设直线的方程为 Ax + By + C = 0, 圆心的坐标为 (cx, cy)
            //  则圆心到直线的距离为 d = |A * cx + B * cy + C| / sqrt(A ^ 2 + B ^ 2)

            // 首先检查圆和矩形左、上边界的关系, 如果有接触的话, 就连接
            //  对于左边界来说, 表达式为 1X + 0Y = 0, 则有 d = |1 * cx + 0 * cy| / sqrt(1 ^ 2 + 0 ^ 2) = cx
            //  对于上边界来说, 表达式为 0X + 1Y - k = 0, 则有 d = |0 * cx + 1 * cy - k| / sqrt(0 ^ 2 + 1 ^ 2) = |cy - k|
            if (x1 <= r1 && y1 <= yCorner || y1 + r1 >= yCorner && x1 <= xCorner) uf.union(i, TOP_LEFT_GUARD);

            // 其次检查圆和矩形右、下边界的关系, 如果有接触的话, 就连接
            //  对于右边界来说, 表达式为 1X + 0Y - k = 0, 则有 d = |1 * cx + 0 * cy - k| / sqrt(1 ^ 2 + 0 ^ 2) = |cx - k|
            //  对于下边界来说, 表达式为 0X + 1Y = 0, 则有 d = |0 * cx + 1 * cy| / sqrt(0 ^ 2 + 1 ^ 2) = cy
            if (y1 <= r1 && x1 <= xCorner || x1 + r1 >= xCorner && y1 <= yCorner) uf.union(i, RIGHT_BOTTOM_GUARD);

            for (int j = i + 1; j < circles.length; j++) {
                long x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];
                // 两个圆相交的条件是
                //  d = sqrt((x1 - x2) ^ 2 + (y1 - y2) ^ 2) <= r1 + r2
                var d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (d <= (r1 + r2) * (r1 + r2)) {
                    // 还需要额外判断圆在矩形之外的情况
                    long xc = x1 * r2 + x2 * r1, yc = y1 * r2 + y2 * r1;
                    if (xc <= (r1 + r2) * xCorner && yc <= (r1 + r2) * yCorner) uf.union(i, j);
                }
            }
        }

        return uf.find(TOP_LEFT_GUARD) != uf.find(RIGHT_BOTTOM_GUARD);
    }

    private static class DFS {

        private boolean[] seen = null;

        public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
            seen = new boolean[circles.length];
            for (int i = 0; i < circles.length; i++) {
                long cx = circles[i][0], cy = circles[i][1], r = circles[i][2];
                // 如果圆 i 包含左下角或右上角, 则无法找到路径
                if (inCircle(cx, cy, r, 0, 0) || inCircle(cx, cy, r, xCorner, yCorner)) return false;

                // 如果已经遍历过了, 那就直接跳过
                if (seen[i]) continue;

                // 是否与上边界相交或相切, 圆心需要在矩形左右边界范围内, 即 0 <= cx <= xCorner,
                // 同时圆心到矩形上边界的距离需要满足 |cy - yCorner| <= r
                boolean top = cx <= xCorner && Math.abs(cy - yCorner) <= r;
                // 是否与左边界相交或相切, 圆心的需要在矩形的上下边界范围内, 即 0 <= cy <= yCorner,
                // 同时圆心到矩形左边界的距离需要满足 |cx - 0| <= r
                boolean left = cy <= yCorner && cx <= r;
                if ((top || left) && dfs(i, xCorner, yCorner, circles)) return false;
            }

            return true;
        }

        private boolean dfs(int curr, int xCorner, int yCorner, int[][] circles) {
            long cx = circles[curr][0], cy = circles[curr][1], r = circles[curr][2];
            // 是否与下边界相交或相切, 圆心需要在矩形左右边界范围内, 即 0 <= cx <= xCorner,
            // 同时圆心到矩形下边界的距离需要满足 |cy - 0| <= r
            boolean bottom = cx <= xCorner && cy <= r;
            // 是否与右边界相交或相切, 圆心的需要在矩形的上下边界范围内, 即 0 <= cy <= yCorner,
            // 同时圆心到矩形右边界的距离需要满足 |cx - xCorner| <= r
            boolean right = cy <= yCorner && Math.abs(cx - xCorner) <= r;
            // 如果与矩形的右/下边界相交或者相切, 那么就找到了一条路径
            if (bottom || right) return true;

            seen[curr] = true;
            for (int i = 0; i < circles.length; i++) {
                if (!seen[i] && intersecting(cx, cy, r, circles[i][0], circles[i][1], circles[i][2])) {
                    if (dfs(i, xCorner, yCorner, circles)) return true;
                }
            }

            return false;
        }

        // 判断点 (x, y) 是否在圆 (cx, cy, r) 内
        private boolean inCircle(long cx, long cy, long r, long x, long y) {
            return (cx - x) * (cx - x) + (cy - y) * (cy - y) <= r * r;
        }

        private boolean intersecting(long x1, long y1, long r1, long x2, long y2, long r2) {
            // 两个圆相交或者相切的条件是
            //  d = sqrt((x1 - x2) ^ 2 + (y1 - y2) ^ 2) <= r1 + r2
            var d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
            return d <= (r1 + r2) * (r1 + r2);
        }
    }

    public static void main(String[] args) {
        assert new Solution().canReachCorner(4, 4, new int[][]{{5,5,1}});
//        assert new DFS().canReachCorner(4, 4, new int[][]{{5,5,1}});

        System.out.println(new Solution());
        System.out.println();

        for (var m : Solution.class.getMethods()) {
            System.out.println(m.toString());
        }
    }

}
