package problem.p659splitarrayintoconsecutivesubsequences;

import java.util.HashMap;
import java.util.Map;

/**
 * 659. Split Array into Consecutive Subsequences
 *
 * https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/
 *
 * You are given an integer array nums that is sorted in non-decreasing order.
 *
 * Determine if it is possible to split nums into one or more subsequences
 * such that both of the following conditions are true:
 *
 * Each subsequence is a consecutive increasing sequence
 * (i.e. each integer is exactly one more than the previous integer).
 *
 * All subsequences have a length of 3 or more.
 * Return true if you can split nums according to the above conditions, or false otherwise.
 *
 * A subsequence of an array is a new array that is formed from the original array
 * by deleting some (can be none) of the elements without disturbing the relative positions of
 * the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 */

public class Solution {

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (var n : nums) counts.merge(n, 1, Integer::sum);

        Map<Integer, Integer> ends = new HashMap<>();
        for (var n : nums) {
            int currCount = counts.getOrDefault(n, 0);
            if (currCount > 0) {
                int prevEndCount = ends.getOrDefault(n - 1, 0);
                if (prevEndCount > 0) {
                    counts.put(n, currCount - 1);
                    ends.put(n - 1, prevEndCount - 1);
                    ends.merge(n, 1, Integer::sum);
                } else {
                    int next1Count = counts.getOrDefault(n + 1, 0);
                    int next2Count = counts.getOrDefault(n + 2, 0);
                    if (next1Count > 0 && next2Count > 0) {
                        counts.put(n, currCount - 1);
                        counts.put(n + 1, next1Count - 1);
                        counts.put(n + 2, next2Count - 1);
                        ends.merge(n + 2, 1, Integer::sum);
                    } else return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isPossible(new int[]{1,2,3,3,4,5});
        assert new Solution().isPossible(new int[]{1,2,3,3,4,4,5,5});
        assert !new Solution().isPossible(new int[]{1,2,3,4,4,5});
    }

}
