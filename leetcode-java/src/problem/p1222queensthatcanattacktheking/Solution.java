package problem.p1222queensthatcanattacktheking;

import common.Builder;
import common.Checker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1222. Queens That Can Attack the King
 *
 * https://leetcode.cn/problems/queens-that-can-attack-the-king/
 *
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 *
 * Given an array of integer coordinates queens that represents the positions of the Black Queens, and
 * a pair of coordinates king that represent the position of the White King, return the coordinates of
 * all the queens (in any order) that can attack the King.
 */

public class Solution {

    private final int[][] dirs = new int[][]{
        {-1, -1}, {-1, +0}, {-1, +1},
        {+0, -1},           {+0, +1},
        {+1, -1}, {+1, +0}, {+1, +1}
    };

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> set = new HashSet<>();
        for (var queen : queens) set.add(hash(queen[0], queen[1]));

        int x = king[0], y = king[1];
        List<List<Integer>> ans = new ArrayList<>();
        for (var dir : dirs) {
            int dx = x + dir[0], dy = y + dir[1];
            while (dx >= 0 && dx < 8 && dy >= 0 && dy < 8) {
                if (set.contains(hash(dx, dy))) {
                    ans.add(List.of(dx, dy));
                    break;
                }
                dx += dir[0]; dy += dir[1];
            }
        }

        return ans;
    }

    private int hash(int x, int y) { return (x << 16) | y; }

    public static void main(String[] args) {
        assert Checker.check(new Solution().queensAttacktheKing(new int[][]{
            {0,1},{1,0},{4,0},{0,4},{3,3},{2,4}
        }, new int[]{0, 0}), Builder.buildList(new int[][]{{0,1},{1,0},{3,3}}));

        assert Checker.check(new Solution().queensAttacktheKing(new int[][]{
            {0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}
        }, new int[]{3, 3}), Builder.buildList(new int[][]{{2,2},{3,4},{4,4}}));

        assert Checker.check(new Solution().queensAttacktheKing(new int[][]{
            {5,6},{7,7},{2,1},{0,7},{1,6},{5,1},{3,7},{0,3},{4,0},{1,2},
            {6,3},{5,0},{0,4},{2,2},{1,1},{6,4},{5,4},{0,0},{2,6},{4,5},
            {5,2},{1,4},{7,5},{2,3},{0,5},{4,2},{1,0},{2,7},{0,1},{4,6},
            {6,1},{0,6},{4,3},{1,7}
        }, new int[]{3, 4}), Builder.buildList(new int[][]{{2,3},{1,4},{1,6},{3,7},{4,3},{5,4},{4,5}}));
    }

}
