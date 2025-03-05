package weekly.bw150.C;

import common.Checker;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static void main(String[] args) {
//        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,2}, {1,1,2}}), 1.00000);

        assert Checker.check(new Solution().separateSquares(new int[][]{{12,23,3}, {11,27,8}}), 30.4375);
        assert Checker.check(new Solution().separateSquares(new int[][]{{15,21,2}, {19,21,3}}), 22.3);
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,2}, {1,1,1}}), 1.00000);
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,1}, {2,2,1}}), 1.00000);
    }

}
