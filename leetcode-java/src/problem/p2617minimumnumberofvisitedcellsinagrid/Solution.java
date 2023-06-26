package problem.p2617minimumnumberofvisitedcellsinagrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2617. Minimum Number of Visited Cells in a Grid
 *
 * https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid/
 *
 * You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).
 *
 * Starting from the cell (i, j), you can move to one of the following cells:
 *
 * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
 * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
 *
 * Return the minimum number of cells you need to visit to reach the bottom-right
 * cell (m - 1, n - 1). If there is no valid path, return -1.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        List<int[]>[] cols = new ArrayList[n]; // 每列的单调栈
        Arrays.setAll(cols, e -> new ArrayList<int[]>());

        for (int i = m - 1; i >= 0; --i) {
            var rows = new ArrayList<int[]>(); // 当前行的单调栈
            for (int j = n - 1; j >= 0; --j) {
                var col = cols[j];
                ans = Integer.MAX_VALUE;
                int g = grid[i][j];
                if (i == m - 1 && j == n - 1) ans = 0;

                else if (g > 0) {
                    // 在单调栈上二分
                    int k = search(rows, j + g);
                    if (k < rows.size()) ans = Math.min(ans, rows.get(k)[0]);
                    k = search(col, i + g);
                    if (k < col.size()) ans = Math.min(ans, col.get(k)[0]);
                }
                if (ans == Integer.MAX_VALUE) continue;

                ++ans; // 加上 (i,j) 这个格子
                // 插入单调栈
                while (!rows.isEmpty() && ans <= rows.get(rows.size() - 1)[0])
                    rows.remove(rows.size() - 1);
                rows.add(new int[]{ans, j});

                while (!col.isEmpty() && ans <= col.get(col.size() - 1)[0])
                    col.remove(col.size() - 1);
                col.add(new int[]{ans, i});
            }
        }
        return ans < Integer.MAX_VALUE ? ans : -1;
    }

    private int search(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) >>> 1;
            if (st.get(mid)[1] > target) left = mid; // 范围缩小到 (mid, right)
            else right = mid; // 范围缩小到 (left, mid)
        }
        return right;
    }

    public static void main(String[] args) {
        assert new Solution().minimumVisitedCells(new int[][]{{3,4,2,1},{4,2,3,1},{2,1,0,0},{2,4,0,0}}) == 4;
        assert new Solution().minimumVisitedCells(new int[][]{{3,4,2,1},{4,2,1,1},{2,1,1,0},{3,4,1,0}}) == 3;
        assert new Solution().minimumVisitedCells(new int[][]{{2,1,0},{1,0,0}}) == -1;
    }

}
