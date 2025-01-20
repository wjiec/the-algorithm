package weekly.w432.A;

import java.util.ArrayList;
import java.util.List;

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
