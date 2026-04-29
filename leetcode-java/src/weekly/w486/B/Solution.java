package weekly.w486.B;

import java.util.ArrayList;
import java.util.List;

/**
 * Q2. Rotate Non Negative Elements
 *
 * https://leetcode.cn/contest/weekly-contest-486/problems/rotate-non-negative-elements/
 *
 * You are given an integer array nums and an integer k.
 *
 * Rotate only the non-negative elements of the array to the left by k positions, in a cyclic manner.
 *
 * All negative elements must stay in their original positions and must not move.
 *
 * After rotation, place the non-negative elements back into the array in the new order,
 * filling only the positions that originally contained non-negative values and skipping all negative positions.
 *
 * Return the resulting array.
 */

public class Solution {

    public int[] rotateElements(int[] nums, int k) {
        List<Integer> positive = new ArrayList<>();
        for (var v : nums) if (v >= 0) positive.add(v);
        if (positive.isEmpty()) return nums;

        k %= positive.size();
        // 向左移动 k 位实际上就是用后 k 个位置替代
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] < 0) continue;

            nums[i] = positive.get((k + j++) % positive.size());
        }
        return nums;
    }

    public static void main(String[] args) {
    }

}
