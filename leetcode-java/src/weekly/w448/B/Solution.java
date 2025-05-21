package weekly.w448.B;

import common.PrettyPrinter;

/**
 * 3537. Fill a Special Grid
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/fill-a-special-grid/
 *
 * You are given a non-negative integer n representing a 2n x 2n grid.
 * You must fill the grid with integers from 0 to 22n - 1 to make it special.
 *
 * A grid is special if it satisfies all the following conditions:
 *
 * All numbers in the top-right quadrant are smaller than those in the bottom-right quadrant.
 * All numbers in the bottom-right quadrant are smaller than those in the bottom-left quadrant.
 * All numbers in the bottom-left quadrant are smaller than those in the top-left quadrant.
 * Each of its quadrants is also a special grid.
 *
 * Return the special 2n x 2n grid.
 * Note: Any 1x1 grid is special.
 */

public class Solution {

    public int[][] specialGrid(int n) {
        int[][] ans = new int[1 << n][1 << n];
        // 将当前数字范围的分成 4 份, 然后填入到指定的区域里, 按照右上, 右下, 左下, 左上的顺序填入
        fill(0, 0, (1 << n) - 1, (1 << n) - 1, 0, 1 << (2 * n), ans);
        return ans;
    }

    // 在左上角坐标为 (x1, y1), 右下角坐标为 (x2, y2) 的矩形里填入 [l, r) 的数字
    private void fill(int x1, int y1, int x2, int y2, int l, int r, int[][] ans) {
        if (l + 1 == r) { ans[x1][y1] = l; return; }

        // 否则我们需要根据顺序枚举填充
        int quarter = (r - l) / 4;
        fill(x1, y1 + (y2 - y1) / 2 + 1, x1 + (x2 - x1) / 2, y2, l, l + quarter, ans);
        fill(x1 + (x2 - x1) / 2 + 1, y1 + (y2 - y1) / 2 + 1, x2, y2, l + quarter, l + 2 * quarter, ans);
        fill(x1 + (x2 - x1) / 2 + 1, y1, x2, y1 + (y2 - y1) / 2, l + 2 * quarter, l + 3 * quarter, ans);
        fill(x1, y1, x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2, l + 3 * quarter, r, ans);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().specialGrid(0));
        PrettyPrinter.println(new Solution().specialGrid(1));
        PrettyPrinter.println(new Solution().specialGrid(2));
        PrettyPrinter.println(new Solution().specialGrid(3));
    }

}
