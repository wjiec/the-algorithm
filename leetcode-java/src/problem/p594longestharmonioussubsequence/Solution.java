package problem.p594longestharmonioussubsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * 594. Longest Harmonious Subsequence
 *
 * https://leetcode-cn.com/problems/longest-harmonious-subsequence/
 *
 * We define a harmonious array as an array where the difference
 * between its maximum value and its minimum value is exactly 1.
 *
 * Given an integer array nums, return the length of its
 * longest harmonious subsequence among all its possible subsequences.
 *
 * A subsequence of array is a sequence that can be derived from the array by
 * deleting some or no elements without changing the order of the remaining elements.
 */

public class Solution {

    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int ans = 0;
        for (var kv : map.entrySet()) {
            int next = map.getOrDefault(kv.getKey() + 1, 0);
            if (next != 0) {
                ans = Math.max(ans, kv.getValue() + map.get(kv.getKey() + 1));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findLHS(new int[]{1,3,2,2,5,2,3,7}) == 5;
        assert new Solution().findLHS(new int[]{1,2,3,4}) == 2;
        assert new Solution().findLHS(new int[]{1,1,1,1}) == 0;
    }

}
