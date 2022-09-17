package problem.p1901findapeakelementii;

import common.PrettyPrinter;
import common.TODO;
import common.Tag;

/**
 * 1901. Find a Peak Element II
 *
 * https://leetcode.cn/problems/find-a-peak-element-ii/
 *
 * A peak element in a 2D grid is an element that is strictly greater than
 * all of its adjacent neighbors to the left, right, top, and bottom.
 *
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find
 * any peak element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter
 * with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 */

public class Solution {

    @TODO(tips = "以行号为索引进行二分")
    public int[] findPeakGrid(int[][] mat) {
        int ansX = -1, ansY = -1;
        for (int l = 0, r = mat.length - 1; l <= r; ) {
            int mid = l + (r - l) / 2;
            int idx = -1, val = -1;
            for (int i = 0; i < mat[mid].length; i++) {
                if (mat[mid][i] > val) {
                    idx = i; val = mat[mid][i];
                }
            }

            if ((mid == 0 || mat[mid - 1][idx] < val) && (mid == mat.length-1 || mat[mid + 1][idx] < val)) {
                ansX = mid; ansY = idx; break;
            } else if (mid > 0 && mat[mid - 1][idx] > val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return new int[]{ansX, ansY};
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findPeakGrid(new int[][]{{1,4},{3,2}}));
        PrettyPrinter.println(new Solution().findPeakGrid(new int[][]{{10,20,15},{21,30,14},{7,16,32}}));
    }

}
