package problem.p1275findwinneronatictactoegame;

/**
 * 1275. Find Winner on a Tic Tac Toe Game
 *
 * https://leetcode-cn.com/problems/find-winner-on-a-tic-tac-toe-game/
 *
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row
 * and column of the grid where they mark their respective character in the order in which A and B play.
 *
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw",
 * if there are still movements to play return "Pending".
 *
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe),
 * the grid is initially empty and A will play first.
 */

public class Solution {

    public String tictactoe(int[][] moves) {
        if (moves.length < 5) return "Pending";
        char[][] grid = new char[3][3];
        grid[moves[0][0]][moves[0][1]] = 'A';
        grid[moves[1][0]][moves[1][1]] = 'B';
        grid[moves[2][0]][moves[2][1]] = 'A';
        grid[moves[3][0]][moves[3][1]] = 'B';
        for (int i = 4; i < moves.length; i++) {
            grid[moves[i][0]][moves[i][1]] = i % 2 == 0 ? 'A' : 'B';
            if (winner(grid, 'A')) return "A";
            if (winner(grid, 'B')) return "B";
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private boolean winner(char[][] grid, char c) {
        for (int i = 0; i < 3; i++) {
            boolean hor = true, ver = true;
            for (int j = 0; j < 3 && (hor || ver); j++) {
                hor = hor && grid[i][j] == c;
                ver = ver && grid[j][i] == c;
            }

            if (hor || ver) return true;
        }
        return (grid[0][0] == c && grid[1][1] == c && grid[2][2] == c) ||
            (grid[0][2] == c && grid[1][1] == c && grid[2][0] == c);
    }

    public static void main(String[] args) {
        assert new Solution().tictactoe(new int[][]{
            {0,0}, {2,0}, {1,1}, {2,1}, {2,2}
        }).equals("A");
        assert new Solution().tictactoe(new int[][]{
            {0,0},{1,1},{0,1},{0,2},{1,0},{2,0}
        }).equals("B");
        assert new Solution().tictactoe(new int[][]{
            {0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}
        }).equals("Draw");
        assert new Solution().tictactoe(new int[][]{
            {0,0},{1,1}
        }).equals("Pending");
    }

}
