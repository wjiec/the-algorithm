package problem.p1072flipcolumnsformaximumnumberofequalrows;

import java.util.HashMap;
import java.util.Map;

/**
 * 1072. Flip Columns For Maximum Number of Equal Rows
 *
 * https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/
 *
 * You are given an m x n binary matrix matrix.
 *
 * You can choose any number of columns in the matrix and flip every cell in that
 * column (i.e., Change the value of the cell from 0 to 1 or vice versa).
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 */

public class Solution {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] line : matrix) {
            boolean turn = line[0] == 0;
            StringBuilder sb = new StringBuilder();
            for (var v : line) sb.append(turn ? (v == 1 ? 0 : 1) : v);
            map.merge(sb.toString(), 1, Integer::sum);
        }

        int ans = 0;
        for (var v : map.values()) ans = Math.max(ans, v);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxEqualRowsAfterFlips(new int[][]{{0,1},{1,1}}) == 1;
        assert new Solution().maxEqualRowsAfterFlips(new int[][]{{0,1},{1,0}}) == 2;
        assert new Solution().maxEqualRowsAfterFlips(new int[][]{{0,0,0},{0,0,1},{1,1,0}}) == 2;
    }

}
