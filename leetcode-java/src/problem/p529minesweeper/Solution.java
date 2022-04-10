package problem.p529minesweeper;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 529. Minesweeper
 *
 * https://leetcode-cn.com/problems/minesweeper/
 *
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given an m x n char matrix board representing the game board where:
 *
 * 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * 'X' represents a revealed mine.
 * You are also given an integer array click where click = [clickr, clickc] represents the next click
 * position among all the unrevealed squares ('M' or 'E').
 *
 * Return the board after revealing this position according to the following rules:
 *
 * If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 * If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and
 * all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
 * representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 */

public class Solution {

    private int[][] dirs = new int[][]{
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1}, /*{0, 0}*/ {0, 1},
        {1, -1}, {1, 0}, {1, 1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1], m = board.length, n = board[0].length;
        if (board[x][y] == 'M') board[x][y] = 'X';
        if (board[x][y] == 'E') {
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> queue = new ArrayDeque<>();
            for (queue.add(click); !queue.isEmpty(); ) {
                int[] curr = queue.remove();
                visited[curr[0]][curr[1]] = true;

                int count = 0;
                for (var dir : dirs) {
                    x = curr[0] + dir[0]; y = curr[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'M') count++;
                }
                board[curr[0]][curr[1]] = count == 0 ? 'B' : (char) ('0' + count);

                if (board[curr[0]][curr[1]] == 'B') {
                    for (var dir : dirs) {
                        x = curr[0] + dir[0]; y = curr[1] + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'E' && !visited[x][y]) {
                            visited[x][y] = true;
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
        }

        return board;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().updateBoard(new char[][]{
            {'E','E','E','E','E'},
            {'E','E','M','E','E'},
            {'E','E','E','E','E'},
            {'E','E','E','E','E'},
        }, new int[]{3,0}));

        PrettyPrinter.println(new Solution().updateBoard(new char[][]{
            {'B','1','E','1','B'},
            {'B','1','M','1','B'},
            {'B','1','1','1','B'},
            {'B','B','B','B','B'}
        }, new int[]{1,2}));
    }

}
