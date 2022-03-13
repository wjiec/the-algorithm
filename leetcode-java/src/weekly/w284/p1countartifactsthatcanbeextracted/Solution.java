package weekly.w284.p1countartifactsthatcanbeextracted;

import java.util.HashSet;
import java.util.Set;

/**
 * 5203. Count Artifacts That Can Be Extracted
 *
 * https://leetcode-cn.com/contest/weekly-contest-284/problems/count-artifacts-that-can-be-extracted/
 *
 * There is an n x n 0-indexed grid with some artifacts buried in it. You are given the integer n
 * and a 0-indexed 2D integer array artifacts describing the positions of the rectangular artifacts
 * where artifacts[i] = [r1i, c1i, r2i, c2i] denotes that the ith artifact is buried in the subgrid where:
 *
 * (r1i, c1i) is the coordinate of the top-left cell of the ith artifact and
 * (r2i, c2i) is the coordinate of the bottom-right cell of the ith artifact.
 * You will excavate some cells of the grid and remove all the mud from them. If the cell has
 * a part of an artifact buried underneath, it will be uncovered.
 *
 * If all the parts of an artifact are uncovered, you can extract it.
 *
 * Given a 0-indexed 2D integer array dig where dig[i] = [ri, ci] indicates that you will excavate
 * the cell (ri, ci), return the number of artifacts that you can extract.
 *
 * The test cases are generated such that:
 *
 * No two artifacts overlap.
 * Each artifact only covers at most 4 cells.
 * The entries of dig are unique.
 */

public class Solution {

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        Set<Integer> digg = new HashSet<>();
        for (var item : dig) digg.add((item[0] << 16) | item[1]);

        int ans = 0;
        for (var artifact : artifacts) {
            if (check(artifact[0], artifact[1], artifact[2], artifact[3], digg)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean check(int x1, int y1, int x2, int y2, Set<Integer> digg) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (!digg.contains((x << 16) | y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().digArtifacts(2, new int[][]{{0,0,0,0},{0,1,1,1}}, new int[][]{{0,0},{0,1}}) == 1;
        assert new Solution().digArtifacts(2, new int[][]{{0,0,0,0},{0,1,1,1}}, new int[][]{{0,0},{0,1},{1,1}}) == 2;
    }

}
