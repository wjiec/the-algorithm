package weekly.bw87.C;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 6186. Smallest Subarrays With Maximum Bitwise OR
 *
 * https://leetcode.cn/contest/biweekly-contest-87/problems/smallest-subarrays-with-maximum-bitwise-or/
 *
 * You are given a 0-indexed array nums of length n, consisting of non-negative integers.
 * For each index i from 0 to n - 1, you must determine the size of the minimum sized
 * non-empty subarray of nums starting at i (inclusive) that has the maximum
 * possible bitwise OR.
 *
 * In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find
 * the smallest subarray starting at i, such that bitwise OR of this subarray is equal to
 * max(Bik) where i <= k <= n - 1.
 *
 * The bitwise OR of an array is the bitwise OR of all the numbers in it.
 *
 * Return an integer array answer of size n where answer[i] is the length of the minimum
 * sized subarray starting at i with maximum bitwise OR.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int[] smallestSubarrays(int[] nums) {
        Queue<Integer>[] bits = new Queue[32];
        for (int i = 0; i < bits.length; i++) bits[i] = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0, v = nums[i]; v != 0; v >>= 1, j++) {
                if ((v & 1) == 1) bits[j].add(i);
            }
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = i;
            for (var bit : bits) {
                if (!bit.isEmpty()) {
                    int next = bit.peek();
                    maxIndex = Math.max(maxIndex, next);
                    if (next <= i) bit.remove();
                }
            }
            ans[i] = maxIndex - i + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().smallestSubarrays(new int[]{1,0,2,1,3}), new int[]{3,3,2,2,1});
        assert Checker.check(new Solution().smallestSubarrays(new int[]{1,2}), new int[]{2,1});
    }

}
