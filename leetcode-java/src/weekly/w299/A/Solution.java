package weekly.w299.A;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (grid[i][i] == 0) return false;
            points.add(new Point(i, i));
        }

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][n - i - 1] == 0) return false;
            points.add(new Point(i, n - i - 1));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!points.contains(new Point(i, j))) {
                    if (grid[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
