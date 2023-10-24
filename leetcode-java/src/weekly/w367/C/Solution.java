package weekly.w367.C;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * 2905. Find Indices With Index and Value Difference II
 *
 * https://leetcode.cn/contest/weekly-contest-367/problems/find-indices-with-index-and-value-difference-ii/
 *
 * You are given a 0-indexed integer array nums having length n, an integer
 * indexDifference, and an integer valueDifference.
 *
 * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
 *
 * abs(i - j) >= indexDifference, and
 * abs(nums[i] - nums[j]) >= valueDifference
 * Return an integer array answer, where answer = [i, j] if there are two
 * such indices, and answer = [-1, -1] otherwise. If there are multiple
 * choices for the two indices, return any of them.
 *
 * Note: i and j may be equal.
 */

public class Solution {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        if (indexDifference >= nums.length) return new int[]{-1, -1};
        if (valueDifference == 0) {
            return new int[]{0, indexDifference};
        }

        TreeMap<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i = indexDifference; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], v -> new HashSet<>()).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            int prevIdx = i - indexDifference;
            if (prevIdx >= 0) {
                map.computeIfAbsent(nums[prevIdx], v -> new HashSet<>()).add(prevIdx);
            }

            if (!map.isEmpty() && map.lastKey() - nums[i] >= valueDifference) {
                return new int[]{i, map.lastEntry().getValue().iterator().next()};
            }
            if (!map.isEmpty() && nums[i] - map.firstKey() >= valueDifference) {
                return new int[]{i, map.firstEntry().getValue().iterator().next()};
            }

            int nextIdx = i + indexDifference;
            if (nextIdx < nums.length) {
                map.get(nums[nextIdx]).remove(nextIdx);
                if (map.get(nums[nextIdx]).isEmpty()) {
                    map.remove(nums[nextIdx]);
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findIndices(new int[]{36,14,30,15,23,34,7,16,4,42,11,24}, 5, 36)));

        System.out.println(Arrays.toString(new Solution().findIndices(new int[]{1}, 1, 0)));
        System.out.println(Arrays.toString(new Solution().findIndices(new int[]{5, 1, 4, 1}, 2, 4)));
        System.out.println(Arrays.toString(new Solution().findIndices(new int[]{2, 1}, 0, 0)));
        System.out.println(Arrays.toString(new Solution().findIndices(new int[]{1,2,3}, 2, 4)));
    }

}
