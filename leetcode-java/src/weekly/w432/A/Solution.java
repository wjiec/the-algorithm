package weekly.w432.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 3417. Zigzag Grid Traversal With Skip
 *
 * https://leetcode.cn/contest/weekly-contest-432/problems/zigzag-grid-traversal-with-skip/
 *
 * You are given an m x n 2D array grid of positive integers.
 *
 * Your task is to traverse grid in a zigzag pattern while skipping every alternate cell.
 *
 * Zigzag pattern traversal is defined as following the below actions:
 *
 * Start at the top-left cell (0, 0).
 * Move right within a row until the end of the row is reached.
 * Drop down to the next row, then traverse left until the beginning of the row is reached.
 * Continue alternating between right and left traversal until every row has been traversed.
 * Note that you must skip every alternate cell during the traversal.
 *
 * Return an array of integers result containing, in order, the value of the cells
 * visited during the zigzag traversal with skips.
 */

public class Solution {

    public List<Integer> zigzagTraversal(int[][] grid) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, c = 0; i < grid.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (c++ % 2 == 0) ans.add(grid[i][j]);
                }
            } else {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if (c++ % 2 == 0) ans.add(grid[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
