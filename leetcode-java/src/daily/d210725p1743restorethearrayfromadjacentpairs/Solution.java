package daily.d210725p1743restorethearrayfromadjacentpairs;

import java.util.*;

/**
 * 1743. Restore the Array From Adjacent Pairs
 *
 * https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/
 *
 * There is an integer array nums that consists of n unique elements, but you have forgotten it.
 * However, you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi]
 * indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
 * either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 */

public class Solution {

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (var pair : adjacentPairs) {
            map.putIfAbsent(pair[0], new ArrayList<>());
            map.putIfAbsent(pair[1], new ArrayList<>());

            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        int nextValue = 0, index = 0, length = adjacentPairs.length + 1;
        for (var entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                nextValue = entry.getKey();
                break;
            }
        }

        int[] ans = new int[length];
        while (index != length) {
            ans[index++] = nextValue;

            var possible = map.get(nextValue);
            for (var number : possible) {
                if (index == 1 || number != ans[index - 2]) {
                    nextValue = number;
                    break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().restoreArray(new int[][]{
            {2, 1}, {3, 4}, {3, 2}
        })));

        System.out.println(Arrays.toString(new Solution().restoreArray(new int[][]{
            {4, -2}, {1, 4}, {-3, 1}
        })));

        System.out.println(Arrays.toString(new Solution().restoreArray(new int[][]{
            {100000, -100000}
        })));
    }

}
