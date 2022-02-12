package problem.p1030matrixcellsindistanceorder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 1030. Matrix Cells in Distance Order
 *
 * https://leetcode-cn.com/problems/matrix-cells-in-distance-order/
 *
 * We are given a matrix with rows rows and cols columns has cells with integer coordinates (r, c),
 * where 0 <= r < rows and 0 <= c < cols.
 *
 * Additionally, we are given a cell in that matrix with coordinates (rCenter, cCenter).
 *
 * Return the coordinates of all cells in the matrix, sorted by their distance from (rCenter, cCenter)
 * from smallest distance to largest distance.
 *
 * Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance,
 * |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)
 */

public class Solution {

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int idx = 0;
        int[][] ans = new int[rows * cols][2];
        boolean[][] visited = new boolean[rows][cols];
        int[] dx = new int[]{0, 1, 0, -1}, dy = new int[]{1, 0, -1, 0};

        Queue<int[]> queue = new ArrayDeque<>(); queue.add(new int[]{rCenter, cCenter});
        while (!queue.isEmpty()) {
            var coordinate = queue.remove();
            if (visited[coordinate[0]][coordinate[1]]) continue;

            ans[idx++] = coordinate;
            visited[coordinate[0]][coordinate[1]] = true;
            for (int i = 0; i < 4; i++) {
                int x = coordinate[0] + dx[i], y = coordinate[1] + dy[i];
                if (x >= 0 && y >= 0 && x < rows && y < cols) {
                    queue.add(new int[]{x, y});
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().allCellsDistOrder(1, 2, 0, 0)));
        System.out.println(Arrays.deepToString(new Solution().allCellsDistOrder(2, 2, 0, 1)));
        System.out.println(Arrays.deepToString(new Solution().allCellsDistOrder(2, 3, 1, 2)));
    }

}
