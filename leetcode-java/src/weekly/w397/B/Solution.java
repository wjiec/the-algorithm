package weekly.w397.B;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 3147. Taking Maximum Energy From the Mystic Dungeon
 *
 * https://leetcode.cn/contest/weekly-contest-397/problems/taking-maximum-energy-from-the-mystic-dungeon/
 *
 * In a mystic dungeon, n magicians are standing in a line. Each magician
 * has an attribute that gives you energy. Some magicians can give you
 * negative energy, which means taking energy from you.
 *
 * You have been cursed in such a way that after absorbing energy from magician i,
 * you will be instantly transported to magician (i + k). This process will be
 * repeated until you reach the magician where (i + k) does not exist.
 *
 * In other words, you will choose a starting point and then teleport with k jumps
 * until you reach the end of the magicians' sequence, absorbing all the
 * energy during the journey.
 *
 * You are given an array energy and an integer k. Return the maximum possible energy you can gain.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int maximumEnergy(int[] energy, int k) {
        Deque<Integer>[] groups = new Deque[k];
        Arrays.setAll(groups, i -> new ArrayDeque<>());
        for (int i = 0; i < energy.length; i++) {
            groups[i % k].push(energy[i]);
        }

        int ans = Integer.MIN_VALUE;
        for (var group : groups) {
            int curr = group.pop();
            ans = Math.max(ans, curr);
            while (!group.isEmpty()) {
                curr += group.pop();
                ans = Math.max(ans, curr);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
