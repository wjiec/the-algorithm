package problem.p2056numberofvalidmovecombinationsonchessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2056. Number of Valid Move Combinations On Chessboard
 *
 * https://leetcode.cn/problems/number-of-valid-move-combinations-on-chessboard/
 *
 * There is an 8 x 8 chessboard containing n pieces (rooks, queens, or bishops).
 * You are given a string array pieces of length n, where pieces[i] describes
 * the type (rook, queen, or bishop) of the ith piece. In addition, you are given
 * a 2D integer array positions also of length n, where positions[i] = [ri, ci] indicates
 * that the ith piece is currently at the 1-based coordinate (ri, ci) on the chessboard.
 *
 * When making a move for a piece, you choose a destination square that the piece will travel toward and stop on.
 *
 * A rook can only travel horizontally or vertically from (r, c) to the
 * direction of (r+1, c), (r-1, c), (r, c+1), or (r, c-1).
 *
 * A queen can only travel horizontally, vertically, or diagonally from (r, c) to the
 * direction of (r+1, c), (r-1, c), (r, c+1), (r, c-1), (r+1, c+1), (r+1, c-1), (r-1, c+1), (r-1, c-1).
 *
 * A bishop can only travel diagonally from (r, c) to the direction of (r+1, c+1), (r+1, c-1), (r-1, c+1), (r-1, c-1).
 *
 * You must make a move for every piece on the board simultaneously.
 * A move combination consists of all the moves performed on all the given pieces.
 * Every second, each piece will instantaneously travel one square towards their
 * destination if they are not already at it. All pieces start traveling at the 0th second.
 * A move combination is invalid if, at a given time, two or more pieces occupy the same square.
 *
 * Return the number of valid move combinations.
 *
 * Notes:
 *
 * No two pieces will start in the same square.
 * You may choose the square a piece is already on as its destination.
 * If two pieces are directly adjacent to each other, it is valid for them to move past each
 * other and swap positions in one second.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private final static int INF = 9;
    private final Map<String, int[][]> dirs = new HashMap<>();
    {
        dirs.put("rook", new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}});
        dirs.put("bishop", new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
        dirs.put("queen", new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
    }

    private List<List<int[]>>[] enums = null;

    public int countCombinations(String[] pieces, int[][] positions) {
        enums = new List[pieces.length];
        for (int i = 0; i < pieces.length; i++) {
            enums[i] = enumerate(pieces[i], positions[i]);
        }

        dfs(0, new int[pieces.length]);
        return ans;
    }

    private int ans = 0;

    private void dfs(int i, int[] selected) {
        if (i == selected.length) {
            if (checkValid(selected)) ans++;
            return;
        }

        for (int j = 0; j < enums[i].size(); j++) {
            selected[i] = j;
            dfs(i + 1, selected);
        }
    }

    private boolean checkValid(int[] selected) {
        boolean[][] board = new boolean[INF][INF];
        for (int j = 0; j < INF; j++) {
            if (j != 0) {
                for (int i = 0; i < selected.length; i++) {
                    if (j - 1 < enums[i].get(selected[i]).size() - 1) {
                        int[] prev = enums[i].get(selected[i]).get(j - 1);
                        board[prev[0]][prev[1]] = false;
                    }
                }
            }

            for (int i = 0; i < selected.length; i++) {
                if (j < enums[i].get(selected[i]).size()) {
                    int[] curr = enums[i].get(selected[i]).get(j);
                    if (board[curr[0]][curr[1]]) return false;
                    board[curr[0]][curr[1]] = true;
                }
            }
        }

        return true;
    }

    private List<List<int[]>> enumerate(String piece, int[] pos) {
        List<List<int[]>> ans = new ArrayList<>();
        ans.add(List.of(new int[]{pos[0], pos[1]}));

        int[][] dirs = this.dirs.get(piece);
        for (var dir : dirs) {
            List<int[]> curr = new ArrayList<>();
            int cx = pos[0] + dir[0], cy = pos[1] + dir[1];
            while (cx > 0 && cx < INF && cy > 0 && cy < INF) {
                curr.add(new int[]{cx, cy});
                ans.add(new ArrayList<>(curr));

                cx += dir[0]; cy += dir[1];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countCombinations(new String[]{"rook"}, new int[][]{{1,1}}) == 15;
        assert new Solution().countCombinations(new String[]{"queen"}, new int[][]{{1,1}}) == 22;
        assert new Solution().countCombinations(new String[]{"bishop"}, new int[][]{{4,3}}) == 12;
        assert new Solution().countCombinations(new String[]{"rook","rook"}, new int[][]{{1,1},{8,8}}) == 223;
        assert new Solution().countCombinations(new String[]{"queen","bishop"}, new int[][]{{5,7},{3,4}}) == 281;
    }

}
