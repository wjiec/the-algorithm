package weekly.bw108.B;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 6469. Relocate Marbles
 *
 * https://leetcode.cn/contest/biweekly-contest-108/problems/relocate-marbles/
 *
 * You are given a 0-indexed integer array nums representing the initial positions of some marbles.
 * You are also given two 0-indexed integer arrays moveFrom and moveTo of equal length.
 *
 * Throughout moveFrom.length steps, you will change the positions of the marbles.
 * On the ith step, you will move all marbles at position moveFrom[i] to position moveTo[i].
 *
 * After completing all the steps, return the sorted list of occupied positions.
 *
 * Notes:
 *
 * We call a position occupied if there is at least one marble in that position.
 * There may be multiple marbles in a single position.
 */

public class Solution {

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        TreeSet<Integer> ans = new TreeSet<>();
        for (var v : nums) ans.add(v);
        for (int i = 0; i < moveFrom.length; i++) {
            if (ans.remove(moveFrom[i])) {
                ans.add(moveTo[i]);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
    }

}
