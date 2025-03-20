package weekly.w439.A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3471. Find the Largest Almost Missing Integer
 *
 * https://leetcode.cn/contest/weekly-contest-439/problems/find-the-largest-almost-missing-integer/
 *
 * You are given an integer array nums and an integer k.
 *
 * An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.
 *
 * Return the largest almost missing integer from nums. If no such integer exists, return -1.
 *
 * A subarray is a contiguous sequence of elements within an array.
 */

public class Solution {

    public int largestInteger(int[] nums, int k) {
        int[] window = new int[51];
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            window[nums[r]]++;
            if (r >= k - 1) {
                for (int v = 0; v < window.length; v++) {
                    if (window[v] != 0) {
                        m.computeIfAbsent(v, kk -> new HashSet<>()).add(r);
                    }
                }
                window[nums[l++]]--;
            }
        }

        int ans = -1;
        for (var kv : m.entrySet()) {
            if (kv.getValue().size() == 1) {
                ans = Math.max(ans, kv.getKey());
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestInteger(new int[]{3,9,2,1,7}, 3) == 7;
        assert new Solution().largestInteger(new int[]{3,9,7,2,1,7}, 4) == 3;
        assert new Solution().largestInteger(new int[]{0,0}, 1) == -1;
    }

}
