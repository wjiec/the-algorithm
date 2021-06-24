package problem.p1037validboomerang;

/**
 * 1037. Valid Boomerang
 *
 * https://leetcode-cn.com/problems/valid-boomerang/
 *
 * Given an array points where points[i] = [xi, yi] represents a point on the X-Y plane,
 * return true if these points are a boomerang.
 *
 * A boomerang is a set of three points that are all distinct and not in a straight line.
 */

public class Solution {

    public boolean isBoomerang(int[][] points) {
        return (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]) != (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
    }

    public static void main(String[] args) {
        assert new Solution().isBoomerang(new int[][]{{0,0}, {2,0}, {0,2}});
        assert !new Solution().isBoomerang(new int[][]{{0,0}, {2,0}, {3,0}});
        assert !new Solution().isBoomerang(new int[][]{{0,0}, {1,1}, {1,1}});
        assert new Solution().isBoomerang(new int[][]{{0,0}, {0,2}, {2,1}});
        assert new Solution().isBoomerang(new int[][]{{1,1}, {2,3}, {3,2}});
        assert !new Solution().isBoomerang(new int[][]{{1,1}, {2,2}, {3,3}});
    }

}
