package problem.p59spiralmatrixii;

import java.util.Arrays;

/**
 * 59. Spiral Matrix II
 *
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 *
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 */

public class Solution {

    public int[][] generateMatrix(int n) {
        int x = 0, y = 0, dir = 0;
        int[][] ans = new int[n][n];
        for (int i = 0, e = n * n; i < e; i++) {
            ans[x][y] = i + 1;
            switch (dir) {
                case 0: // right
                    if (y + 1 == n || ans[x][y + 1] != 0) {
                        dir = 1;
                        x++;
                    } else {
                        y++;
                    }
                    break;
                case 1: // down
                    if (x + 1 == n || ans[x + 1][y] != 0) {
                        dir = 2;
                        y--;
                    } else {
                        x++;
                    }
                    break;
                case 2: // left
                    if (y == 0 || ans[x][y - 1] != 0) {
                        dir = 3;
                        x--;
                    } else {
                        y--;
                    }
                    break;
                case 3: // up
                    if (x == 0 || ans[x - 1][y] != 0) {
                        dir = 0;
                        y++;
                    } else {
                        x--;
                    }
                    break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(1)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(2)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(3)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(4)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(5)));
        System.out.println(Arrays.deepToString(new Solution().generateMatrix(6)));
    }

}
