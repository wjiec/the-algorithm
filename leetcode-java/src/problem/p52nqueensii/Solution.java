package problem.p52nqueensii;

/**
 * 52. N-Queens II
 *
 * https://leetcode.cn/problems/n-queens-ii/
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private int ans = 0;

    public int totalNQueens(int n) {
        dfs(new int[n], 0);
        return ans;
    }

    private void dfs(int[] rows, int curr) {
        if (curr == rows.length) { ans++; return; }
        for (int i = 0; i < rows.length; i++) {
            if (check(rows, curr, i)) {
                rows[curr] = i;
                dfs(rows, curr + 1);
            }
        }
    }

    private boolean check(int[] rows, int curr, int c) {
        int l = c - 1, r = c + 1, i = curr - 1;
        for (; i >= 0; i--, l--, r++) {
            if (rows[i] == l || rows[i] == r || rows[i] == c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().totalNQueens(8) == 92;
    }

}
