package weekly.w373.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2948. Make Lexicographically Smallest Array by Swapping Elements
 *
 * https://leetcode.cn/contest/weekly-contest-373/problems/make-lexicographically-smallest-array-by-swapping-elements/
 *
 * You are given a 0-indexed array of positive integers nums and a positive integer limit.
 *
 * In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.
 *
 * Return the lexicographically smallest array that can be obtained by performing the operation any number of times.
 *
 * An array a is lexicographically smaller than an array b if in the first position
 * where a and b differ, array a has an element that is less than the corresponding
 * element in b.
 *
 * For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because
 * they differ at index 0 and 2 < 10.
 */

public class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        Map<Integer, Integer> groups = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> members = new HashMap<>();
        for (int i = 0, g = 0; i < sorted.length; i++) {
            if (i != 0 && sorted[i] - sorted[i - 1] > limit) g++;
            groups.put(sorted[i], g);
            members.computeIfAbsent(g, v -> new PriorityQueue<>()).add(sorted[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = members.get(groups.get(nums[i])).remove();
        }
        return nums;
    }

    public static void main(String[] args) {
    }

}
