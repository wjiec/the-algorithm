package weekly.w403.D;

/**
 * 3197. Find the Minimum Area to Cover All Ones II
 *
 * https://leetcode.cn/contest/weekly-contest-403/problems/find-the-minimum-area-to-cover-all-ones-ii/
 *
 * You are given a 2D binary array grid. You need to find 3 non-overlapping rectangles having non-zero
 * areas with horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.
 *
 * Return the minimum possible sum of the area of these rectangles.
 *
 * Note that the rectangles are allowed to touch.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    /**
     * 可以有以下几种情况
     *
     *  1.
     *      A B
     *      CCC
     *
     *  2.
     *      AAA
     *      B C
     *
     *  3.
     *      A B
     *      C B
     *
     *  4.
     *      A B
     *      A C
     *
     *  5.
     *      A B C
     *      A B C
     *
     *  6.
     *      A A
     *      B B
     *      C C
     *
     * 分类讨论即可
     */
    public int minimumSum(int[][] grid) {
        int ans = Integer.MAX_VALUE;
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                ans = min(
                    ans,
                    case1(grid, i, j, m, n),
                    case2(grid, i, j, m, n),
                    case3(grid, i, j, m, n),
                    case4(grid, i, j, m, n)
                );
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                ans = min(ans, case6(grid, i, j, m, n));
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = min(ans, case5(grid, i, j, m, n));
            }
        }
        return ans;
    }

    public int case1(int[][] grid, int x, int y, int m, int n) {
        // A B
        // CCC
        return rect(grid, 0, 0, x, y) // A
            + rect(grid, 0, y, x, n) // B
            + rect(grid, x, 0, m, n); // C
    }

    public int case2(int[][] grid, int x, int y, int m, int n) {
        // AAA
        // B C
        return rect(grid, 0, 0, x, n) // A
            + rect(grid, x, 0, m, y) // B
            + rect(grid, x, y, m, n); // C
    }

    public int case3(int[][] grid, int x, int y, int m, int n) {
        // A B
        // C B
        return rect(grid, 0, 0, x, y) // A
            + rect(grid, 0, y, m, n) // B
            + rect(grid, x, 0, m, y); // C
    }

    public int case4(int[][] grid, int x, int y, int m, int n) {
        // A B
        // A C
        return rect(grid, 0, 0, m, y) // A
            + rect(grid, 0, y, x, n) // B
            + rect(grid, x, y, m, n); // C
    }

    public int case5(int[][] grid, int y1, int y2, int m, int n) {
        // A B C
        // A B C
        return rect(grid, 0, 0, m, y1) // A
            + rect(grid, 0, y1, m, y2) // B
            + rect(grid, 0, y2, m, n); // C
    }

    public int case6(int[][] grid, int x1, int x2, int m, int n) {
        // A A
        // B B
        // C C
        return rect(grid, 0, 0, x1, n) // A
            + rect(grid, x1, 0, x2, n) // B
            + rect(grid, x2, 0, m, n); // C
    }

    private int rect(int[][] grid, int tx, int ty, int bx, int by) {
        int miX = 1001, miY = 1001, mxX = 0, mxY = 0, c = 0;
        for (int i = tx; i < bx; i++) {
            for (int j = ty; j < by; j++) {
                if (grid[i][j] == 1) {
                    c++;
                    miX = Math.min(miX, i);
                    miY = Math.min(miY, j);
                    mxX = Math.max(mxX, i);
                    mxY = Math.max(mxY, j);
                }
            }
        }
        if (c == 0) return 0;

        return (mxX - miX + 1) * (mxY - miY + 1);
    }

    private int min(int ...nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < ans) ans = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSum(new int[][]{{1,0,1},{1,1,1}}) == 5;
        assert new Solution().minimumSum(new int[][]{{1,0,1,0},{0,1,0,1}}) == 5;
    }

}
