package weekly.w462.A;

/**
 * Q1. Flip Square Submatrix Vertically
 *
 * https://leetcode.cn/contest/weekly-contest-462/problems/flip-square-submatrix-vertically/
 *
 * You are given an m x n integer matrix grid, and three integers x, y, and k.
 *
 * The integers x and y represent the row and column indices of the top-left
 * corner of a square submatrix and the integer k represents
 * the size (side length) of the square submatrix.
 *
 * Your task is to flip the submatrix by reversing the order of its rows vertically.
 *
 * Return the updated matrix.
 */

public class Solution {

    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // 每一行逐渐翻转, 第一行是 x, 最后一行是 x + k - 1
        for (int l = x, r = x + k - 1; l < r; l++, r--) {
            // 每一行的 (l, y) 和 (r, y) 互换
            for (int j = 0; j < k; j++) {
                int temp = grid[l][y + j];
                grid[l][y + j] = grid[r][y + j];
                grid[r][y + j] = temp;
            }
        }
        return grid;
    }

    public static void main(String[] args) {
    }

}
