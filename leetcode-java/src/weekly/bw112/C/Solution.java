package weekly.bw112.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2841. Maximum Sum of Almost Unique Subarray
 *
 * https://leetcode.cn/contest/biweekly-contest-112/problems/maximum-sum-of-almost-unique-subarray/
 *
 * You are given an integer array nums and two positive integers m and k.
 *
 * Return the maximum sum out of all almost unique subarrays of length k of nums.
 * If no such subarray exists, return 0.
 *
 * A subarray of nums is almost unique if it contains at least m distinct elements.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long maxSum(List<Integer> nums, int m, int k) {
        long ans = 0, curr = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < nums.size(); r++) {
            curr += nums.get(r);
            map.merge(nums.get(r), 1, Integer::sum);

            if (r - l + 1 > k) {
                map.merge(nums.get(l), -1, (a, b) -> a + b == 0 ? null : (a + b));
                curr -= nums.get(l++);
            }

            if (r - l + 1 == k && map.size() >= m) ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
