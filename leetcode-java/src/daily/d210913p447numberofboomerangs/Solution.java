package daily.d210913p447numberofboomerangs;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. Number of Boomerangs
 *
 * https://leetcode-cn.com/problems/number-of-boomerangs/
 *
 * You are given n points in the plane that are all distinct, where points[i] = [xi, yi].
 *
 * A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance
 * between i and k (the order of the tuple matters).
 *
 * Return the number of boomerangs.
 */

public class Solution {

    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (var a : points) {
            Map<Integer, Integer> count = new HashMap<>();
            for (var b : points) {
                int dist = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
                count.merge(dist, 1, Integer::sum);
            }
            for (var entry : count.entrySet()) {
                ans += (entry.getValue() - 1) * entry.getValue();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfBoomerangs(new int[][]{
            {0,0}, {1,0}, {2,0}
        }) == 2;

        assert new Solution().numberOfBoomerangs(new int[][]{
            {1,1}, {2,2}, {3,3}
        }) == 2;

        assert new Solution().numberOfBoomerangs(new int[][]{
            {1,1}
        }) == 0;
    }

}
