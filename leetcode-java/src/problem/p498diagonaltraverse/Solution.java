package problem.p498diagonaltraverse;

import common.Checker;
import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 498. Diagonal Traverse
 *
 * https://leetcode-cn.com/problems/diagonal-traverse/
 *
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 */

public class Solution {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m * n];
        boolean upward = true;

        for (int i = 0, x = 0, y = 0; i < ans.length; i++) {
            ans[i] = mat[x][y];
            mat[x][y] = 0;
            if (upward) {
                if (x == 0 || y == n - 1) upward = false;
                if (x == 0 && y == n - 1) x++;
                else if (x == 0) y++;
                else if (y == n - 1) x++;
                else {
                    x--; y++;
                }
            } else {
                if (x == m - 1 || y == 0) upward = true;
                if (x == m - 1 && y == 0) y++;
                else if (x == m - 1) y++;
                else if (y == 0) x++;
                else {
                    x++; y--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}),
            new int[]{1,2,4,7,5,3,6,8,9});

        assert Checker.check(new Solution().findDiagonalOrder(new int[][]{{1,2},{3,4}}), new int[]{1,2,3,4});

        assert Checker.check(new Solution().findDiagonalOrder(new int[][]{{1,2,3,4,5,6,7,8},{8,7,6,5,4,3,2,1}}),
            new int[]{1,2,8,7,3,4,6,5,5,6,4,3,7,8,2,1});

        assert Checker.check(new Solution().findDiagonalOrder(new int[][]{{1,2},{3,4},{5,6},{7,8},{9,0}}),
            new int[]{1, 2, 3, 5, 4, 6, 7, 9, 8, 0});
    }

}
