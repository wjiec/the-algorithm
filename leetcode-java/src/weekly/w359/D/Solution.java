package weekly.w359.D;

import java.util.ArrayList;
import java.util.List;

/**
 * 2831. Find the Longest Equal Subarray
 *
 * https://leetcode.cn/contest/weekly-contest-359/problems/find-the-longest-equal-subarray/
 *
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * A subarray is called equal if all of its elements are equal. Note that the empty subarray is an equal subarray.
 *
 * Return the length of the longest possible equal subarray after deleting at most k elements from nums.
 *
 * A subarray is a contiguous, possibly empty sequence of elements within an array.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size(), ans = 0;
        List<Integer>[] pos = new List[n + 1];
        for (int i = 0; i <= n; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            pos[val].add(i - pos[val].size());
        }

        for (var p : pos) {
            if (p.size() <= ans) continue;
            for (int l = 0, r = 0; r < p.size(); r++) {
                while (p.get(r) - p.get(l) > k) l++;
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestEqualSubarray(List.of(1,3,2,3,1,3), 3) == 3;
    }

}
