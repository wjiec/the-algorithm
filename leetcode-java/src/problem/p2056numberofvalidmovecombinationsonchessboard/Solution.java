package problem.p2056numberofvalidmovecombinationsonchessboard;

import common.Tag;

import java.util.*;

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

@Tag("NOT COMPLETED")
public class Solution {

    private final Map<String, int[][]> dirs = new HashMap<>();
    {
        dirs.put("rook", new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}});
        dirs.put("bishop", new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
        dirs.put("queen", new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
    }

    private int[][] originPos = null;

    public int countCombinations(String[] pieces, int[][] positions) {
        originPos = positions;
        List<List<int[]>> all = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            all.add(allPossible(pieces[i], positions[i]));
        }

        dfs(all, 0, new int[all.size()]);
        return ans;
    }

    private int ans = 0;

    private void dfs(List<List<int[]>> g, int i, int[] posIndex) {
        if (i == g.size()) {
            if (isValid(g, posIndex, g.size())) ans++;
            return;
        }

        int len = g.get(i).size();
        for (int k = 0; k < len; k++) {
            posIndex[i] = k;
            dfs(g, i + 1, posIndex);
        }
    }

    private boolean isValid(List<List<int[]>> g, int[] posIndex, int r) {
        if (g.size() == 1) return true;

        // 是否有重叠
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < r; i++) {
            int[] finiPos = g.get(i).get(posIndex[i]);
            if (!seen.add((finiPos[0] << 16) | finiPos[1])) {
                return false;
            }
        }

        // 检查是否能移动棋子
        for (int i = 0; i < r; i++) {
            var checkPos = g.get(i).get(posIndex[i]);
            for (int j = 0; j < r; j++) {
                if (i == j) continue;

                var initPos = originPos[j];
                var finiPos = g.get(j).get(posIndex[j]);
                var dir = getDir(finiPos, initPos);
                int dx = initPos[0], dy = initPos[1];
                while (dx != finiPos[0] && dy != finiPos[1]) {
                    if (dx == checkPos[0] && dy == checkPos[1]) {
                        return false;
                    }
                    dx += dir[0]; dy += dir[1];
                }
            }
        }

        return true;
    }

    private int[] getDir(int[] dst, int[] src) {
        return new int[]{Integer.compare(dst[0], src[0]), Integer.compare(dst[1], src[1])};
    }

    private List<int[]> allPossible(String piece, int[] initPos) {
        List<int[]> possibles = new ArrayList<>();
        possibles.add(initPos);

        for (var dir : dirs.get(piece)) {
            int dx = initPos[0] + dir[0], dy = initPos[1] + dir[1];
            while (dx >= 1 && dx <= 8 && dy >= 1 && dy <= 8) {
                possibles.add(new int[]{dx, dy});
                dx += dir[0]; dy += dir[1];
            }
        }

        return possibles;
    }

    public static void main(String[] args) {
        assert new Solution().countCombinations(new String[]{"rook"}, new int[][]{{1,1}}) == 15;
        assert new Solution().countCombinations(new String[]{"queen"}, new int[][]{{1,1}}) == 22;
        assert new Solution().countCombinations(new String[]{"bishop"}, new int[][]{{4,3}}) == 12;
        assert new Solution().countCombinations(new String[]{"rook","rook"}, new int[][]{{1,1},{8,8}}) == 223;
        assert new Solution().countCombinations(new String[]{"queen","bishop"}, new int[][]{{5,7},{3,4}}) == 281;
    }

}
