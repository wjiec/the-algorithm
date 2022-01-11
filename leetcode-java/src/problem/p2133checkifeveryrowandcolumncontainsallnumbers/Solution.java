package problem.p2133checkifeveryrowandcolumncontainsallnumbers;

import java.util.HashSet;
import java.util.Set;

/**
 * 2133. Check if Every Row and Column Contains All Numbers
 *
 * https://leetcode-cn.com/problems/check-if-every-row-and-column-contains-all-numbers/
 *
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
 *
 * Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
 */

public class Solution {

    public boolean checkValid(int[][] matrix) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0, n = matrix.length; i < n; i++) {
            set.clear();
            for (int j = 0; j < n; j++) {
                if (set.contains(matrix[i][j])) {
                    return false;
                }
                set.add(matrix[i][j]);
            }
        }
        for (int i = 0, n = matrix.length; i < n; i++) {
            set.clear();
            for (int j = 0; j < n; j++) {
                if (set.contains(matrix[j][i])) {
                    return false;
                }
                set.add(matrix[j][i]);
            }
        }
        return true;
    }


    public static void main(String[] args) {
        assert new Solution().checkValid(new int[][]{
            {1,2,3},
            {3,1,2},
            {2,3,1}
        });

        assert !new Solution().checkValid(new int[][]{
            {1,1,1},
            {1,2,3},
            {1,2,3}
        });
    }

}
