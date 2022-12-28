package problem.p773slidingpuzzle;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 773. Sliding Puzzle
 *
 * https://leetcode.cn/problems/sliding-puzzle/
 *
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that
 * the state of the board is solved. If it is impossible for the state of the board
 * to be solved, return -1.
 */

public class Solution {

    private final int ROW_SIZE = 2, COL_SIZE = 3;
    private final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private record State(int[][] board, int x, int y, int s) {}

    public int slidingPuzzle(int[][] board) {
        Queue<State> queue = new ArrayDeque<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                if (board[i][j] == 0) {
                    queue.add(new State(board, i, j, 0));
                    break;
                }
            }
        }

        Set<String> visited = new HashSet<>();
        visited.add(toKey(board));

        while (!queue.isEmpty()) {
            State curr = queue.remove();
            if (toKey(curr.board).equals("123450")) return curr.s;

            for (var dir : dirs) {
                int dx = curr.x + dir[0], dy = curr.y + dir[1];
                if (dx >= 0 && dx < ROW_SIZE && dy >= 0 && dy < COL_SIZE) {
                    int[][] next = swap(board, curr.x, curr.y, dx, dy);
                    if (visited.add(toKey(next))) {
                        queue.add(new State(next, dx, dy, curr.s + 1));
                    }
                }
            }
        }

        return -1;
    }

    private int[][] swap(int[][] board, int x1, int y1, int x2, int y2) {
        int[][] swapped = new int[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; i++) {
            System.arraycopy(board[i], 0, swapped[i], 0, COL_SIZE);
        }

        int stash = swapped[x1][y1];
        swapped[x1][y1] = swapped[x2][y2];
        swapped[x2][y2] = stash;
        return swapped;
    }

    private String toKey(int[][] board) {
        char[] chars = new char[6];
        chars[0] = (char) (board[0][0] + '0');
        chars[1] = (char) (board[0][1] + '0');
        chars[2] = (char) (board[0][2] + '0');
        chars[3] = (char) (board[1][0] + '0');
        chars[4] = (char) (board[1][1] + '0');
        chars[5] = (char) (board[1][2] + '0');
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}) == 1;
        assert new Solution().slidingPuzzle(new int[][]{{1,2,3},{5,4,0}}) == -1;
        assert new Solution().slidingPuzzle(new int[][]{{4,1,2},{5,0,3}}) == 5;
    }

}
