package weekly.w362.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 2850. Minimum Moves to Spread Stones Over Grid
 *
 * https://leetcode.cn/contest/weekly-contest-362/problems/minimum-moves-to-spread-stones-over-grid/
 *
 * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell.
 * The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
 *
 * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
 *
 * Return the minimum number of moves required to place one stone in each cell.
 */

public class Solution {

    public int minimumMoves(int[][] grid) {
        List<int[]> to = new ArrayList<>();
        List<int[]> from = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) to.add(new int[]{i, j});
                for (int k = 1; k < grid[i][j]; k++) {
                    from.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (var states : permutations(from)) {
            int curr = 0;
            for (int i = 0; i < states.size(); i++) {
                int[] x = states.get(i), y = to.get(i);
                curr += Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
            }
            ans = Math.min(ans, curr);
        }

        return ans;
    }

    private List<List<int[]>> permutations(List<int[]> array) {
        List<List<int[]>> ans = new ArrayList<>();
        permutations(array, 0, ans);
        return ans;
    }

    private void permutations(List<int[]> array, int i, List<List<int[]>> ans) {
        if (i == array.size()) {
            ans.add(new ArrayList<>(array));
            return;
        }

        for (int j = i; j < array.size(); j++) {
            swap(array, i, j);
            permutations(array, i + 1, ans);
            swap(array, i, j);
        }
    }

    private void swap(List<int[]> array, int x, int y) {
        int[] temp = array.get(x);
        array.set(x, array.get(y));
        array.set(y, temp);
    }

    public static void main(String[] args) {
    }

}
