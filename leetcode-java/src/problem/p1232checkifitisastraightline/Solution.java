package problem.p1232checkifitisastraightline;

/**
 * 1232. Check If It Is a Straight Line
 *
 * https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/
 *
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 */

public class Solution {

    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        int l = coordinates.length, e = l - 1;
        for (int i = 1; i < e; i++) {
            if ((coordinates[i][0] - coordinates[0][0]) * (coordinates[i][1] - coordinates[e][1]) != (coordinates[i][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[e][0])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        assert new Solution().checkStraightLine(new int[][]{{0,0},{0,1},{0,-1}});

        assert new Solution().checkStraightLine(new int[][]{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}});
        assert !new Solution().checkStraightLine(new int[][]{{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}});
    }

}
