package weekly.w425.A;

import java.util.List;

/**
 * 3364. Minimum Positive Sum Subarray
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/minimum-positive-sum-subarray/
 *
 * You are given an integer array nums and two integers l and r. Your task is to find the
 * minimum sum of a subarray whose size is between l and r (inclusive) and whose sum is greater than 0.
 *
 * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = Integer.MAX_VALUE;
        for (int sz = l; sz <= r; sz++) {
            int curr = 0;
            for (int i = 0; i < nums.size(); i++) {
                curr += nums.get(i);
                if (i >= sz) curr -= nums.get(i - sz);
                if (i + 1 >= sz && curr > 0) ans = Math.min(ans, curr);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSumSubarray(List.of(1,2,3,4), 2, 3) == 3;
    }

}
