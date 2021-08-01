package daily.d210801p1337thekweakestrowsinamatrix;

import common.Checker;

import java.util.Arrays;

/**
 * 1337. The K Weakest Rows in a Matrix
 *
 * https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/
 *
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
 * The soldiers are positioned in front of the civilians.
 *
 * That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 *
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 */

public class Solution {

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] values = new int[mat.length];
        for (int i = 0, m = mat.length, n = mat[0].length; i < m; i++) {
            int cnt = 0;
            for (; cnt < n && mat[i][cnt] == 1; cnt++);
            values[i] = cnt << 16 | i;
        }

        Arrays.sort(values);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = values[i] & 0xffff;
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().kWeakestRows(new int[][]{
            {1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}
        }, 3), new int[]{2, 0, 3});

        assert Checker.check(new Solution().kWeakestRows(new int[][]{
            {1,0,0,0}, {1,1,1,1}, {1,0,0,0}, {1,0,0,0}
        }, 2), new int[]{0, 2});
    }

}
