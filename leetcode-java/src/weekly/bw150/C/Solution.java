package weekly.bw150.C;

import common.Checker;
import common.PrettyPrinter;
import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 3454. Separate Squares II
 *
 * https://leetcode.cn/contest/biweekly-contest-150/problems/separate-squares-ii/
 *
 * You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents
 * the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.
 *
 * Find the minimum y-coordinate value of a horizontal line such that the total area
 * covered by squares above the line equals the total area covered by squares below the line.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Note: Squares may overlap. Overlapping areas should be counted only once in this version.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    // 比第二题多了个条件, 重叠的区域只计算一次
    public double separateSquares(int[][] squares) {
        // 把 squares 中重叠的部分先扣掉, 剩下的就是第二题的内容了
        //  - 把 squares 中的矩形重叠起来, 计算出一个二维矩阵, 按照二维矩阵的相同值划分正方形
        return separateSquares(split(squares));
    }

    private List<long[]> split(int[][] squares) {
        int n = squares.length;
        long[] xs = new long[2 * n], ys = new long[2 * n];
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            int x = squares[i][0], y = squares[i][1], l = squares[i][2];
            xs[j++] = x; xs[j++] = x + l;
            ys[k++] = y; ys[k++] = y + l;
        }

        // 排序去重
        xs = unique(xs); ys = unique(ys);

        // 离散化 + 二维差分
        int xl = xs.length, yl = ys.length;
        int[][] diff = new int[xl + 2][yl + 2];
        for (var square : squares) {
            int x = square[0], y = square[1], l = square[2];
            // 左下角坐标
            int x1 = Arrays.binarySearch(xs, x), y1 = Arrays.binarySearch(ys, y);
            int x2 = Arrays.binarySearch(xs, x + l), y2 = Arrays.binarySearch(ys, y + l);
            diff[x1 + 1][y1 + 1]++; diff[x1 + 1][y2 + 2]--; diff[x2 + 2][y1 + 1]--; diff[x2 + 2][y2 + 2]++;
        }

        // 复原 diff 数组的值
        for (int i = 1; i <= xl + 1; i++) {
            for (int j = 1; j <= yl + 1; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
            }
        }

        PrettyPrinter.println(diff);

        // 将 diff 数组中的所有不等于 0 的区块, 切分为一个个的矩形
        List<long[]> ans = new ArrayList<>();
        for (int i = 1; i <= xl; i++) {
            for (int j = 1; j <= yl; j++) {
                if (diff[i][j] != 0 && diff[i + 1][j] != 0 && diff[i][j + 1] != 0 && diff[i + 1][j + 1] != 0) {
                    // 现在从 (i, j) 到 (i + 1, j + 1) 就围成了一个矩形, 但是这个可能是由空白区域围成的矩形得排除
                    long l = xs[i] - xs[i - 1], h = ys[j] - ys[j - 1], x = xs[i - 1], y = ys[j - 1];
                    ans.add(new long[]{x, y, l, h});
                }
            }
        }

        return ans;
    }

    private long[] unique(long[] array) {
        Arrays.sort(array);

        int len = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1]) {
                array[len++] = array[i];
            }
        }
        return Arrays.copyOf(array, len);
    }

    // [x, y, xl, yl]
    private double separateSquares(List<long[]> squares) {
        PrettyPrinter.println(squares);
        long sum = 0, loY = Integer.MAX_VALUE, hiY = 0;
        for (var square : squares) {
            long y = square[1], xl = square[2], yl = square[3];

            sum += xl * yl;
            loY = Math.min(loY, y);
            hiY = Math.max(hiY, y + yl);
        }

        // 有了总面积之后, 我们需要找到一条线, 使得这条线上面正方形的面积与下面正方形的面积相同
        //  使用二分查找中间位置, 直到找到最接近一半面积的位置
        double l = loY, r = hiY, half = sum / 2.0;
        while (l + 1e-6 < r) {
            double mid = l + (r - l) / 2.0;
            if (check(squares, mid, half)) r = mid;
            else l = mid;
        }
        return l;
    }

    // 检查使用 line 这条线下部的面积是否大于等于 area
    private boolean check(List<long[]> squares, double line, double area) {
        double sum = 0;
        for (var square : squares) {
            double y = square[1], xl = square[2], yl = square[3];
            if (y + yl <= line) sum += xl * yl;
            else if (y < line && y + yl > line) sum += xl * (line - y);
        }
        return sum >= area;
    }

    private static class Optimization {
        private static class SegmentTree {
            private final int n;
            private final int[] minCoverLen; // 区间内被矩形覆盖次数最少的底边长之和
            private final int[] minCover; // 区间内被矩形覆盖的最小次数
            private final int[] lazy; // 子树内所有节点的 minCover 需要增加的值

            public SegmentTree(int[] data) {
                n = data.length - 1; // len(data) 个横坐标有 len(data) - 1 个 gap
                int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
                minCoverLen = new int[size];
                minCover = new int[size];
                lazy = new int[size];
                build(data, 1, 0, n - 1);
            }

            private void build(int[] data, int curr, int l, int r) {
                if (l == r) {
                    minCoverLen[curr] = data[l + 1] - data[l];
                    return;
                }

                int mid = l + (r - l) / 2;
                build(data, curr * 2, l, mid);
                build(data, curr * 2 + 1, mid + 1, r);
                maintain(curr);
            }

            // 根据左右子节点的信息, 更新当前节点的信息
            private void maintain(int curr) {
                int v = Math.min(minCover[curr * 2], minCover[curr * 2 + 1]);
                minCover[curr] = v;

                minCoverLen[curr] = (minCover[curr * 2] == v ? minCoverLen[curr * 2] : 0) +
                    (minCover[curr * 2 + 1] == v ? minCoverLen[curr * 2 + 1] : 0);
            }

            public void update(int l, int r, int v) { update(1, 0, n - 1, l, r, v); }
            private void update(int curr, int s, int e, int l, int r, int v) {
                if (l <= s && e <= r) {
                    lazyUpdate(curr, v);
                    return;
                }

                spread(curr);
                int mid = s + (e - s) / 2;
                if (l <= mid) update(curr * 2, s, mid, l, r, v);
                if (mid < r) update(curr * 2 + 1, mid + 1, e, l, r, v);
                maintain(curr);
            }
            private void spread(int curr) {
                if (lazy[curr] != 0) {
                    lazyUpdate(curr * 2, lazy[curr]);
                    lazyUpdate(curr * 2 + 1, lazy[curr]);
                    lazy[curr] = 0;
                }
            }
            private void lazyUpdate(int curr, int v) { minCover[curr] += v; lazy[curr] += v; }

            public int uncoveredLen() { return minCover[1] == 0 ? minCoverLen[1] : 0; }
        }

        private record Event(int y, int lx, int rx, int delta) {}
        private record Record(long area, int len) {}

        @Tag({"矩形面积并"})
        public double separateSquares(int[][] squares) {
            int[] xs = new int[2 * squares.length];
            Event[] events = new Event[2 * squares.length];
            for (int i = 0, j = 0, k = 0; i < squares.length; i++) {
                int x = squares[i][0], y = squares[i][1], l = squares[i][2];
                xs[j++] = x; xs[j++] = x + l;
                events[k++] = new Event(y, x, x + l, 1);
                events[k++] = new Event(y + l, x, x + l, -1);
            }

            // 排序方便后续做离散化
            Arrays.sort(xs);
            // 模拟扫描线从下往上扫描
            Arrays.sort(events, Comparator.comparingInt(e -> e.y));

            long sumArea = 0;
            SegmentTree st = new SegmentTree(xs);
            Record[] records = new Record[xs.length];
            for (int i = 0; i < xs.length - 1; i++) {
                var e = events[i];

                // 离散化
                int l = Arrays.binarySearch(xs, e.lx);
                int r = Arrays.binarySearch(xs, e.rx) - 1; // 对应 xs[r] 和 rx = xs[r + 1] 的差值

                st.update(l, r, e.delta); // 更新覆盖次数
                int sumLen = xs[xs.length - 1] - xs[0] - st.uncoveredLen(); // 减去没被矩阵覆盖的长度

                records[i] = new Record(sumArea, sumLen);
                // 新增面积 = 被至少一个矩形覆盖的底边长度之和乘以扫过的距离
                sumArea += (long) sumLen * (events[i + 1].y - e.y);
            }

            int i = 0;
            while (i < xs.length - 1 && records[i].area * 2 < sumArea) { i++; } i--;
            return events[i].y + (sumArea - records[i].area * 2) / (records[i].len * 2.0);
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Optimization().separateSquares(new int[][]{{12,23,3}, {11,27,8}}), 30.4375);
        assert Checker.check(new Optimization().separateSquares(new int[][]{{15,21,2}, {19,21,3}}), 22.3);
        assert Checker.check(new Optimization().separateSquares(new int[][]{{0,0,2}, {1,1,1}}), 1.00000);
        assert Checker.check(new Optimization().separateSquares(new int[][]{{0,0,1}, {2,2,1}}), 1.00000);

//        assert Checker.check(new Solution().separateSquares(new int[][]{{12,23,3}, {11,27,8}}), 30.4375);
//        assert Checker.check(new Solution().separateSquares(new int[][]{{15,21,2}, {19,21,3}}), 22.3);
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,2}, {1,1,1}}), 1.00000);
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,1}, {2,2,1}}), 1.00000);
    }

}
