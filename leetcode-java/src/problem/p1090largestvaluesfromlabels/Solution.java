package problem.p1090largestvaluesfromlabels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1090. Largest Values From Labels
 *
 * https://leetcode.cn/problems/largest-values-from-labels/
 *
 * There is a set of n items. You are given two integer arrays values and labels
 * where the value and the label of the ith element are values[i] and labels[i] respectively.
 *
 * You are also given two integers numWanted and useLimit.
 *
 * Choose a subset s of the n elements such that:
 *
 * The size of the subset s is less than or equal to numWanted.
 * There are at most useLimit items with the same label in s.
 * The score of a subset is the sum of the values in the subset.
 *
 * Return the maximum score of a subset s.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            map.computeIfAbsent(labels[i], v -> new PriorityQueue<>((a, b) -> b - a))
                .add(values[i]);
        }

        int ans = 0;
        Map<Integer, Integer> used = new HashMap<>();
        for (int i = 0; i < numWanted && !map.isEmpty(); i++) {
            int label = -1, value = -1;
            for (var kv : map.entrySet()) {
                if (kv.getValue().peek() > value) {
                    label = kv.getKey();
                    value = kv.getValue().peek();
                }
            }

            ans += value;
            map.get(label).remove();
            int count = used.merge(label, 1, Integer::sum);
            if (map.get(label).isEmpty() || count >= useLimit) {
                map.remove(label);
            }
        }
        return ans;
    }

    private static class Optimization {
        public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
            int[][] items = new int[values.length][2];
            for (int i = 0; i < values.length; i++) {
                items[i][0] = values[i]; items[i][1] = labels[i];
            }
            Arrays.sort(items, (a, b) -> b[0] - a[0]);

            int ans = 0;
            Map<Integer, Integer> used = new HashMap<>();
            for (int i = 0, j = 0; i < items.length && j < numWanted; i++) {
                int count = used.getOrDefault(items[i][1], 0);
                if (count < useLimit) {
                    used.put(items[i][1], count + 1);
                    ans += items[i][0];
                    j++;
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,1,2,2,3}, 3, 1) == 9;
        assert new Solution().largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,3,3,3,2}, 3, 2) == 12;
        assert new Solution().largestValsFromLabels(new int[]{9,8,8,7,6}, new int[]{0,0,0,1,1}, 3, 1) == 16;

        assert new Optimization().largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,1,2,2,3}, 3, 1) == 9;
        assert new Optimization().largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,3,3,3,2}, 3, 2) == 12;
        assert new Optimization().largestValsFromLabels(new int[]{9,8,8,7,6}, new int[]{0,0,0,1,1}, 3, 1) == 16;
    }

}
