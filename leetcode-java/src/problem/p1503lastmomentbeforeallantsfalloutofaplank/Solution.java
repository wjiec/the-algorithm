package problem.p1503lastmomentbeforeallantsfalloutofaplank;

/**
 * 1503. Last Moment Before All Ants Fall Out of a Plank
 *
 * https://leetcode.cn/problems/last-moment-before-all-ants-fall-out-of-a-plank/
 *
 * We have a wooden plank of the length n units. Some ants are walking on the plank, each ant
 * moves with a speed of 1 unit per second.
 * Some of the ants move to the left, the other move to the right.
 *
 * When two ants moving in two different directions meet at some point, they change their
 * directions and continue moving again. Assume changing directions
 * does not take any additional time.
 *
 * When an ant reaches one end of the plank at a time t, it falls out of the plank immediately.
 *
 * Given an integer n and two integer arrays left and right, the positions of the ants
 * moving to the left and the right, return the moment when the last ant(s) fall out of the plank.
 */

public class Solution {

    public int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;
        for (var v : left) ans = Math.max(ans, v);
        for (var v : right) ans = Math.max(ans, n - v);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getLastMoment(4, new int[]{4,3}, new int[]{0,1}) == 4;
        assert new Solution().getLastMoment(7, new int[]{}, new int[]{0,1,2,3,4,5,6,7}) == 7;
        assert new Solution().getLastMoment(7, new int[]{0,1,2,3,4,5,6,7}, new int[]{}) == 7;
    }

}
