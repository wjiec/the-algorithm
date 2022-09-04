package weekly.w309.C;

/**
 * 6169. Longest Nice Subarray
 *
 * https://leetcode.cn/contest/weekly-contest-309/problems/longest-nice-subarray/
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements
 * that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 *
 * Note that subarrays of length 1 are always considered nice.
 */

public class Solution {

    public int longestNiceSubarray(int[] nums) {
        int[] states = new int[32];
        int ans = 0, n = nums.length;
        for (int l = 0, r = 0; r < n; r++) {
            add(states, nums[r]);
            while (!check(states)) {
                remove(states, nums[l++]);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    private void add(int[] state, int v) {
        for (int i = 0; i < 31 && v != 0; i++) {
            state[i] += v & 1;
            v >>= 1;
        }
    }

    private void remove(int[] state, int v) {
        for (int i = 0; i < 31 && v != 0; i++) {
            state[i] -= v & 1;
            v >>= 1;
        }
    }

    private boolean check(int[] state) {
        for (var s : state) {
            if (s > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
