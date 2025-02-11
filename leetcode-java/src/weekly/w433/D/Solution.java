package weekly.w433.D;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 3430. Maximum and Minimum Sums of at Most Size K Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-433/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays/
 *
 * You are given an integer array nums and a positive integer k. Return the sum of the
 * maximum and minimum elements of all subarrays with at most k elements.
 */

public class Solution {

    public long minMaxSubarraySum(int[] nums, int k) {
        long ans = maxSubarraySum(nums, k);
        for (int i = 0; i < nums.length; i++) nums[i] = -nums[i];
        ans -= maxSubarraySum(nums, k);

        return ans;
    }

    /** @noinspection DataFlowIssue*/
    private long maxSubarraySum(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(-1);

        long ans = 0;
        for (int r = 0; r <= nums.length; r++) {
            long v = r < nums.length ? nums[r] : Integer.MIN_VALUE;
            while (dq.size() > 1 && nums[dq.peek()] >= v) {
                int i = dq.pop(), l = dq.peek(); long x = nums[i];
                if (r - l - 1 > k) {
                    long ll = Math.max(l, i - k), rr = Math.min(r, i + k);
                    // 左端点 > rr - k 的子数组个数
                    long c1 = (rr - i) * (i - (rr - k));
                    // 左端点 <= rr - k 的子数组个数
                    long c2 = (ll + rr + k - 2L * i + 1) * (rr - ll - k) / 2;
                    ans += x * (c1 + c2);
                } else ans += x * (i - l) * (r - i);
            }
            dq.push(r);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMaxSubarraySum(new int[]{-7,-7}, 2) == -42;

        assert new Solution().minMaxSubarraySum(new int[]{1,2,3}, 2) == 20;
        assert new Solution().minMaxSubarraySum(new int[]{1,-3,1}, 2) == -6;
    }

}
