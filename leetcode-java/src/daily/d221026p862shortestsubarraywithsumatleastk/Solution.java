package daily.d221026p862shortestsubarraywithsumatleastk;

import common.TODO;
import common.Tag;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 862. Shortest Subarray with Sum at Least K
 *
 * https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
 *
 * Given an integer array nums and an integer k, return the length of the shortest non-empty
 * subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 */

public class Solution {

    @TODO
    @Tag({"子数组的和至少为N"})
    public int shortestSubarray(int[] nums, int k) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        int ans = nums.length + 1;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= nums.length; i++) {
            long curr = prefix[i];
            while (!deque.isEmpty() && curr - prefix[deque.peekFirst()] >= k) {
                ans = Math.min(ans, i - deque.removeFirst());
            }
            while (!deque.isEmpty() && prefix[deque.peekLast()] >= curr) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        return ans > nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestSubarray(new int[]{1}, 1) == 1;
        assert new Solution().shortestSubarray(new int[]{1, 2}, 4) == -1;
        assert new Solution().shortestSubarray(new int[]{2, -1, 2}, 3) == 3;
    }

}
