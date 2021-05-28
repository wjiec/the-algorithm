package problem.p598rangeadditionii;

import java.util.HashMap;
import java.util.Map;

/**
 * 598. Range Addition II
 *
 * https://leetcode-cn.com/problems/range-addition-ii/
 *
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops,
 * where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 *
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 */

public class Solution {

    public int maxCount(int m, int n, int[][] ops) {
        for (var op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }

    public static void main(String[] args) {
        assert new Solution().maxCount(3, 3, new int[][]{{2,2}, {3,3}}) == 4;
        assert new Solution().maxCount(3, 3, new int[][]{
            {2,2},{3,3},{3,3},{3,3},{2,2},{3,3},{3,3},{3,3},{2,2},{3,3},{3,3},{3,3}
        }) == 4;
    }

}
