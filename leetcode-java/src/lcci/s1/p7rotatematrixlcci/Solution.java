package lcci.s1.p7rotatematrixlcci;

import common.PrettyPrinter;

/**
 * 面试题 01.07. 旋转矩阵
 *
 * https://leetcode.cn/problems/rotate-matrix-lcci/
 *
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 */

public class Solution {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                swap(matrix, i, l, i, r);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                swap(matrix, i, j, n - j - 1, n - i - 1);
            }
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int stash = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = stash;
    }

    public static void main(String[] args) {
        int[][] s1 = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        new Solution().rotate(s1);
        PrettyPrinter.println(s1);

        int[][] s2 = new int[][]{
            { 5, 1, 9,11},
            { 2, 4, 8,10},
            {13, 3, 6, 7},
            {15,14,12,16}
        };
        new Solution().rotate(s2);
        PrettyPrinter.println(s2);
    }

}
