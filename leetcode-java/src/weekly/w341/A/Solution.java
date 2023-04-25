package weekly.w341.A;

/**
 * 2643. Row With Maximum Ones
 *
 * https://leetcode.cn/contest/weekly-contest-341/problems/row-with-maximum-ones/
 *
 * Given a m x n binary matrix mat, find the 0-indexed position of the row that
 * contains the maximum count of ones, and the number of ones in that row.
 *
 * In case there are multiple rows that have the maximum count of ones, the row
 * with the smallest row number should be selected.
 *
 * Return an array containing the index of the row, and the number of ones in it.
 */

public class Solution {

    public int[] rowAndMaximumOnes(int[][] mat) {
        int idx = 0, cnt = 0;
        for (int i = 0; i < mat.length; i++) {
            int curr = 0;
            for (var v : mat[i]) curr += v;
            if (curr > cnt) { cnt = curr; idx = i; }
        }
        return new int[]{idx, cnt};
    }

    public static void main(String[] args) {
    }
    
}
