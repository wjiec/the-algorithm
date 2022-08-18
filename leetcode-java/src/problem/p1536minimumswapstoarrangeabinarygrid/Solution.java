package problem.p1536minimumswapstoarrangeabinarygrid;

/**
 * 1536. Minimum Swaps to Arrange a Binary Grid
 *
 * https://leetcode.cn/problems/minimum-swaps-to-arrange-a-binary-grid/
 *
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 *
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 *
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 *
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 */

public class Solution {

    public int minSwaps(int[][] grid) {
        int ans = 0, n = grid.length;
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) count++;
                else break;
            }
            counts[i] = count;
        }

        // 从上到下是递减的，相当于冒泡排序
        for (int i = 0; i < n - 1; i++) {
            if (counts[i] >= n - i - 1) continue;

            int j = i;
            while (j < n && counts[j] < n - i - 1) j++;
            if (j == n) return -1;

            for (; j > i; j--) {
                int temp = counts[j];
                counts[j] = counts[j - 1];
                counts[j - 1] = temp;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSwaps(new int[][]{{0,0,1},{1,1,0},{1,0,0}}) == 3;
        assert new Solution().minSwaps(new int[][]{{0,1,1,0},{0,1,1,0},{0,1,1,0},{0,1,1,0}}) == -1;
        assert new Solution().minSwaps(new int[][]{{1,0,0},{1,1,0},{1,1,1}}) == 0;
    }

}
