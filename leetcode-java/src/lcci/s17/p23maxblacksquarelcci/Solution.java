package lcci.s17.p23maxblacksquarelcci;

import common.Checker;

/**
 * 面试题 17.23. 最大黑方阵
 *
 * https://leetcode.cn/problems/max-black-square-lcci/
 *
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
 *
 * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
 * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。
 * 若无满足条件的子方阵，返回空数组。
 */

public class Solution {

    public int[] findSquare(int[][] matrix) {
        int n = matrix.length;

        int[][] l2r = new int[n][n]; // left -> right
        int[][] t2b = new int[n][n]; // top -> bottom
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != 0 && matrix[i][j] == 0) l2r[i][j] = l2r[i][j - 1] + 1;
                else l2r[i][j] = matrix[i][j] == 0 ? 1 : 0;

                if (j != 0 && matrix[j][i] == 0) t2b[j][i] = t2b[j - 1][i] + 1;
                else t2b[j][i] = matrix[j][i] == 0 ? 1 : 0;
            }
        }

        int[][] r2l = new int[n][n]; // right -> left
        int[][] b2t = new int[n][n]; // bottom -> top
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (j != n - 1 && matrix[i][j] == 0) r2l[i][j] = r2l[i][j + 1] + 1;
                else r2l[i][j] = matrix[i][j] == 0 ? 1 : 0;

                if (j != n - 1 && matrix[j][i] == 0) b2t[j][i] = b2t[j + 1][i] + 1;
                else b2t[j][i] = matrix[j][i] == 0 ? 1 : 0;
            }
        }

        int x = 0, y = 0, s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int lr = r2l[i][j], tb = b2t[i][j];
                    for (int k = Math.min(lr, tb); k > 0; k--) {
                        int rbx = i + k - 1, rby = j + k - 1;
                        if (t2b[rbx][rby] >= k && l2r[rbx][rby] >= k) {
                            if (k > s) { s = k; x = i; y = j; }
                            break;
                        }
                    }
                }
            }
        }

        return s == 0 ? new int[0] : new int[]{x, y, s};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findSquare(new int[][]{
            {1,0,1},
            {0,0,1},
            {0,0,1}
        }), new int[]{1, 0, 2});

        assert Checker.check(new Solution().findSquare(new int[][]{
            {0,1,1},
            {1,0,1},
            {1,1,0}
        }), new int[]{0, 0, 1});
    }

}
