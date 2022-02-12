package daily.d210627p909snakesandladders;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 909. Snakes and Ladders
 *
 * https://leetcode-cn.com/problems/snakes-and-ladders/
 *
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting
 * from the bottom left of the board, and alternating direction each row.
 *
 * You start on square 1 of the board (which is always in the last row and first column).
 * Each move, starting from square x, consists of the following:
 *
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
 * (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations,
 * regardless of the size of the board.)
 * If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
 * A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c].
 *
 * Note that you only take a snake or ladder at most once per move:
 * if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.
 * (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`,
 * then you finish your first move at `3`, because you do not continue moving to `4`.)
 *
 * Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
 */

public class Solution {

    private static class Coordinate {
        private final int x, y;
        public Coordinate(int x, int y) { this.x = x; this.y = y; }
        public String toString() { return "Coordinate{" + "x=" + x + ", y=" + y + '}'; }
    }

    private final Map<Integer, Coordinate> map = new HashMap<>();

    public int snakesAndLadders(int[][] board) {
        int l = board.length, idx = 1, max = l * l;
        for (int i = l - 1; i >= 0; i--) {
            for (int j = 0; j < l; j++) map.put(idx++, new Coordinate(i, j));

            if (--i >= 0) {
                for (int j = l - 1; j >= 0; j--) map.put(idx++, new Coordinate(i, j));
            }
        }

        int[] visited = new int[map.size() + 1]; visited[1] = 1;
        for (int i = 2; i <= max; i++) visited[i] = -1;
        Queue<Integer> queue = new ArrayDeque<>(); queue.add(1);
        while (!queue.isEmpty()) {
            int pos = queue.remove();
            for (int i = 1; i <= 6; i++) {
                int next = pos + i;
                if (next > max) break;

                var coordinate = map.get(next);
                if (board[coordinate.x][coordinate.y] > 0) {
                    next = board[coordinate.x][coordinate.y];
                }

                if (next == max) return visited[pos];
                if (visited[next] == -1) {
                    visited[next] = visited[pos] + 1;
                    queue.add(next);
                }
            }
        }

        return visited[max];
    }

    public static void main(String[] args) {
        assert new Solution().snakesAndLadders(new int[][]{
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1},
        }) == 4;
        assert new Solution().snakesAndLadders(new int[][]{
            {1,1,-1},
            {1,1,1},
            {-1,1,1},
        }) == -1;
    }

}
