package daily.d210822p789escapetheghosts;

/**
 * 789. Escape The Ghosts
 *
 * https://leetcode-cn.com/problems/escape-the-ghosts/
 *
 * You are playing a simplified PAC-MAN game on an infinite 2-D grid. You start at the point [0, 0],
 * and you are given a destination point target = [xtarget, ytarget], which you are trying to get to.
 *
 * There are several ghosts on the map with their starting positions given as an array ghosts,
 * where ghosts[i] = [xi, yi] represents the starting position of the ith ghost. All inputs are integral coordinates.
 *
 * Each turn, you and all the ghosts may independently choose to either move 1 unit
 * in any of the four cardinal directions: north, east, south, or west or stay still.
 * All actions happen simultaneously.
 *
 * You escape if and only if you can reach the target before any ghost reaches you.
 * If you reach any square (including the target) at the same time as a ghost, it does not count as an escape.
 *
 * Return true if it is possible to escape, otherwise return false.
 */

public class Solution {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int require = distance(target[0], target[1], 0, 0);
        for (var ghost : ghosts) {
            if (distance(target[0], target[1], ghost[0], ghost[1]) <= require) {
                return false;
            }
        }
        return true;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        assert new Solution().escapeGhosts(new int[][]{{1,0},{0,3}}, new int[]{0,1});
        assert !new Solution().escapeGhosts(new int[][]{{1,0}}, new int[]{2,0});
        assert !new Solution().escapeGhosts(new int[][]{{2,0}}, new int[]{1,0});
        assert new Solution().escapeGhosts(new int[][]{{-1,0},{0,1},{-1,0},{0,1},{-1,0}}, new int[]{0,0});
    }

}
