package problem.p1824minimumsidewayjumps;

/**
 * 1824. Minimum Sideway Jumps
 *
 * https://leetcode.cn/problems/minimum-sideway-jumps/
 *
 * There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n.
 * A frog starts at point 0 in the second lane and wants to jump to point n. However, there
 * could be obstacles along the way.
 *
 * You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3)
 * describes an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no
 * obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.
 *
 * For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
 * The frog can only travel from point i to point i + 1 on the same lane if there is not
 * an obstacle on the lane at point i + 1. To avoid obstacles, the frog can also perform a
 * side jump to jump to another lane (even if they are not adjacent) at the same point
 * if there is no obstacle on the new lane.
 *
 * For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
 * Return the minimum number of side jumps the frog needs to reach any lane at point n starting
 * from lane 2 at point 0.
 *
 * Note: There will be no obstacles on points 0 and n.
 */

public class Solution {

    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length, INF = 1_000_000_007;
        int a1 = 1, b1 = 0, c1 = 1, a2 = INF, b2 = INF, c2 = INF;
        for (int i = 1; i < n; i++) {
            if (obstacles[i] != 1) a2 = a1;
            if (obstacles[i] != 2) b2 = b1;
            if (obstacles[i] != 3) c2 = c1;
            if (obstacles[i] != 1) a2 = Math.min(a2, Math.min(b2, c2) + 1);
            if (obstacles[i] != 2) b2 = Math.min(b2, Math.min(a2, c2) + 1);
            if (obstacles[i] != 3) c2 = Math.min(c2, Math.min(a2, b2) + 1);

            a1 = a2; b1 = b2; c1 = c2; a2 = b2 = c2 = INF;
        }
        return Math.min(Math.min(a1, b1), c1);
    }

    public static void main(String[] args) {
        assert new Solution().minSideJumps(new int[]{0,0,3,1,0,1,0,2,3,1,0}) == 2;

        assert new Solution().minSideJumps(new int[]{0,1,2,3,0}) == 2;
        assert new Solution().minSideJumps(new int[]{0,1,1,3,3,0}) == 0;
        assert new Solution().minSideJumps(new int[]{0,2,1,0,3,0}) == 2;
    }

}
