package problem.p51nqueens;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 *
 * https://leetcode.cn/problems/n-queens/
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space, respectively.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] rows = new int[n];
        dfs(rows, 0);

        return ans;
    }

    private final String[] PADDINGS = new String[]{
        "Q........",
        ".Q.......",
        "..Q......",
        "...Q.....",
        "....Q....",
        ".....Q...",
        "......Q..",
        ".......Q.",
        "........Q",
    };

    private void dfs(int[] rows, int curr) {
        if (curr == rows.length) {
            List<String> row = new ArrayList<>();
            for (int j : rows) {
                row.add(PADDINGS[j].substring(0, rows.length));
            }
            ans.add(row);
            return;
        }

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
        PrettyPrinter.println(new Solution().solveNQueens(1));
        PrettyPrinter.println(new Solution().solveNQueens(2));
        PrettyPrinter.println(new Solution().solveNQueens(3));
        PrettyPrinter.println(new Solution().solveNQueens(4));
    }

}
