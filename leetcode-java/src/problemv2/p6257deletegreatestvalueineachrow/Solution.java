package problemv2.p6257deletegreatestvalueineachrow;

import java.util.PriorityQueue;

/**
 * 6257. Delete Greatest Value in Each Row
 *
 * https://leetcode.cn/problems/delete-greatest-value-in-each-row/
 *
 * You are given an m x n matrix grid consisting of positive integers.
 *
 * Perform the following operation until grid becomes empty:
 *
 * Delete the element with the greatest value from each row.
 * If multiple such elements exist, delete any of them.
 * Add the maximum of deleted elements to the answer.
 * Note that the number of columns decreases by one after each operation.
 *
 * Return the answer after performing the operations described above.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int deleteGreatestValue(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        PriorityQueue<Integer>[] pqs = new PriorityQueue[m];
        for (int i = 0; i < m; i++) {
            pqs[i] = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                pqs[i].add(grid[i][j]);
            }
        }

        for (int j = 0; j < n; j++) {
            int curr = 0;
            for (int i = 0; i < m; i++) {
                curr = Math.max(curr, pqs[i].remove());
            }
            ans += curr;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().deleteGreatestValue(new int[][]{{1,2,4},{3,3,1}}) == 8;
        assert new Solution().deleteGreatestValue(new int[][]{{10}}) == 10;
    }

}
