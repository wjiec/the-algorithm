package weekly.w247.p1p5798cyclicallyrotatingagrid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 5798. Cyclically Rotating a Grid
 *
 * https://leetcode-cn.com/contest/weekly-contest-247/problems/cyclically-rotating-a-grid/
 *
 * You are given an m x n integer matrix grid, where m and n are both even integers, and an integer k.
 *
 * The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:
 */

public class Solution {

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0, j = 0; i < m / 2 && j < n / 2; i++, j++) {
            rotate(grid, i, j, m - i, n - j, k);
        }

        return grid;
    }

    private void rotate(int[][] grid, int x1, int y1, int x2, int y2, int k) {
        int count = (x2 - x1) * 2 + (y2 - y1) * 2 - 4; k %= count;
        ArrayDeque<Integer> queue = layerNumbers(grid, x1, y1, x2, y2);
        for (int i = 0; i < k; i++) {
            queue.addFirst(queue.removeLast());
        }
        replaceLayer(grid, x1, y1, x2, y2, queue);
    }

    private ArrayDeque<Integer> layerNumbers(int[][] grid, int x1, int y1, int x2, int y2) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = x1; i < x2; i++) {
            queue.add(grid[i][y1]);
        }
        for (int i = y1 + 1; i < y2; i++) {
            queue.add(grid[x2 - 1][i]);
        }
        for (int i = x2 - 2; i >= x1; i--) {
            queue.add(grid[i][y2 - 1]);
        }
        for (int i = y2 - 2; i > y1; i--) {
            queue.add(grid[x1][i]);
        }

        return queue;
    }

    private void replaceLayer(int[][] grid, int x1, int y1, int x2, int y2, Queue<Integer> numbers) {
        for (int i = x1; i < x2; i++) {
            grid[i][y1] = numbers.remove();
        }
        for (int i = y1 + 1; i < y2; i++) {
            grid[x2 - 1][i] = numbers.remove();
        }
        for (int i = x2 - 2; i >= x1; i--) {
            grid[i][y2 - 1] = numbers.remove();
        }
        for (int i = y2 - 2; i > y1; i--) {
            grid[x1][i] = numbers.remove();
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().rotateGrid(new int[][]{
            {40, 10}, {30, 20}
        }, 1)));

        System.out.println(Arrays.deepToString(new Solution().rotateGrid(new int[][]{
            {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}
        }, 2)));
    }

}
