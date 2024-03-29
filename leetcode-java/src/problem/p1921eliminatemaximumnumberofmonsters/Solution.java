package problem.p1921eliminatemaximumnumberofmonsters;

import java.util.Arrays;

/**
 * 1921. Eliminate Maximum Number of Monsters
 *
 * https://leetcode.cn/problems/eliminate-maximum-number-of-monsters/
 *
 * You are playing a video game where you are defending your city from a group of n monsters.
 * You are given a 0-indexed integer array dist of size n, where dist[i] is the initial distance
 * in kilometers of the ith monster from the city.
 *
 * The monsters walk toward the city at a constant speed. The speed of each monster is given to
 * you in an integer array speed of size n, where speed[i] is the speed of the ith monster in
 * kilometers per minute.
 *
 * You have a weapon that, once fully charged, can eliminate a single monster. However, the weapon
 * takes one minute to charge.The weapon is fully charged at the very start.
 *
 * You lose when any monster reaches your city. If a monster reaches the city at the exact moment
 * the weapon is fully charged, it counts as a loss, and the game ends before you can use your weapon.
 *
 * Return the maximum number of monsters that you can eliminate before you lose, or n if you can
 * eliminate all the monsters before they reach the city.
 */

public class Solution {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] arrived = new int[dist.length];
        for (int i = 0; i < dist.length; i++) {
            arrived[i] = (int) Math.ceil((double) dist[i] / speed[i]);
        }

        Arrays.sort(arrived);
        for (int i = 0; i < arrived.length; i++) {
            if (arrived[i] <= i) return i;
        }
        return arrived.length;
    }

    public static void main(String[] args) {
        assert new Solution().eliminateMaximum(new int[]{1,3,4}, new int[]{1,1,1}) == 3;
        assert new Solution().eliminateMaximum(new int[]{1,1,2,3}, new int[]{1,1,1,1}) == 1;
        assert new Solution().eliminateMaximum(new int[]{3,2,4}, new int[]{5,3,2}) == 1;
    }

}
