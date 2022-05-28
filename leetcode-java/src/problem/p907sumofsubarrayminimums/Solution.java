package problem.p907sumofsubarrayminimums;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 907. Sum of Subarray Minimums
 *
 * https://leetcode.cn/problems/sum-of-subarray-minimums/
 *
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large, return the answer modulo 109 + 7.
 */

public class Solution {

    @TODO
    public int sumSubarrayMins(int[] arr) {
        int ans = 0, MOD = 1_000_000_007, dot = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int val : arr) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] >= val) {
                int[] curr = stack.pop();
                count += curr[1];
                dot -= curr[0] * curr[1];
            }

            stack.push(new int[]{val, count});
            dot += val * count;
            ans += dot;
            ans %= MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumSubarrayMins(new int[]{3,1,2,4}) == 17;
        assert new Solution().sumSubarrayMins(new int[]{11,81,94,43,3}) == 444;
    }

}
