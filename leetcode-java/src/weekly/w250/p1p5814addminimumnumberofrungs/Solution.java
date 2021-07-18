package weekly.w250.p1p5814addminimumnumberofrungs;

/**
 * 5814. Add Minimum Number of Rungs
 *
 * https://leetcode-cn.com/contest/weekly-contest-250/problems/add-minimum-number-of-rungs/
 *
 * You are given a strictly increasing integer array rungs that represents the height of rungs on a ladder.
 * You are currently on the floor at height 0, and you want to reach the last rung.
 *
 * You are also given an integer dist. You can only climb to the next highest rung
 * if the distance between where you are currently at (the floor or on a rung) and the next rung is at most dist.
 * You are able to insert rungs at any positive integer height if a rung is not already there.
 *
 * Return the minimum number of rungs that must be added to the ladder in order for you to climb to the last rung.
 */

public class Solution {

    public int addRungs(int[] rungs, int dist) {
        int ans = 0, curr = 0;
        for (int i = 0, l = rungs.length; i < l; ) {
            if (rungs[i] - curr > dist) {
                int count = (rungs[i] - curr) / dist, mod = (rungs[i] - curr) % dist;
                if (mod == 0) count -= 1;
                ans += count;
                curr += count * dist;
            } else {
                curr = rungs[i];
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().addRungs(new int[]{3}, 1) == 2;

        assert new Solution().addRungs(new int[]{1,3,5,10}, 2) == 2;
        assert new Solution().addRungs(new int[]{3,6,8,10}, 3) == 0;
        assert new Solution().addRungs(new int[]{3,4,6,7}, 2) == 1;
        assert new Solution().addRungs(new int[]{5}, 10) == 0;
    }

}
