package problem.p1138alphabetboardpath;

import java.util.HashMap;
import java.util.Map;

/**
 * 1138. Alphabet Board Path
 *
 * https://leetcode.cn/problems/alphabet-board-path/
 *
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.
 *
 * You may return any path that does so.
 */

public class Solution {

    private static final char[][] board = new char[][]{
        {'a', 'b', 'c', 'd', 'e'},
        {'f', 'g', 'h', 'i', 'j'},
        {'k', 'l', 'm', 'n', 'o'},
        {'p', 'q', 'r', 's', 't'},
        {'u', 'v', 'w', 'x', 'y'},
        {'z'}
    };

    public String alphabetBoardPath(String target) {
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                map.put(board[i][j], new int[]{i, j});
            }
        }

        int x = 0, y = 0;
        StringBuilder sb = new StringBuilder();
        for (var c : target.toCharArray()) {
            int[] curr = map.get(c);
            sb.append(paths(x, y, curr[0], curr[1]));
            x = curr[0]; y = curr[1];
        }

        return sb.toString();
    }

    private String paths(int a, int b, int c, int d) {
        StringBuilder sb = new StringBuilder();
        while (a != c || b != d) {
            int dx = a - c, dy = b - d;

            if (dx < 0 && isValid(a + 1, b)) { sb.append("D"); a++; continue; }
            if (dx > 0 && isValid(a - 1, b)) { sb.append("U"); a--; continue; }
            if (dy < 0 && isValid(a, b + 1)) { sb.append("R"); b++; continue; }
            if (dy > 0 && isValid(a, b - 1)) { sb.append("L"); b--; }
        }
        return sb.append("!").toString();
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().alphabetBoardPath("leet"));
        System.out.println(new Solution().alphabetBoardPath("code"));
        System.out.println(new Solution().alphabetBoardPath("zard"));
        System.out.println(new Solution().alphabetBoardPath("yz"));
    }

}
