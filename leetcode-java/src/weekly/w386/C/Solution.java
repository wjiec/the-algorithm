package weekly.w386.C;

import java.util.Arrays;

/**
 * 3048. Earliest Second to Mark Indices I
 *
 * https://leetcode.cn/contest/weekly-contest-386/problems/earliest-second-to-mark-indices-i/
 *
 * You are given two 1-indexed integer arrays, nums and, changeIndices, having lengths n and m, respectively.
 *
 * Initially, all indices in nums are unmarked. Your task is to mark all indices in nums.
 *
 * In each second, s, in order from 1 to m (inclusive), you can perform one of the following operations:
 *
 * Choose an index i in the range [1, n] and decrement nums[i] by 1.
 * If nums[changeIndices[s]] is equal to 0, mark the index changeIndices[s].
 * Do nothing.
 *
 * Return an integer denoting the earliest second in the range [1, m] when all indices
 * in nums can be marked by choosing operations optimally, or -1 if it is impossible.
 */

public class Solution {

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int m = changeIndices.length, n = nums.length;
        if (n > m) return -1;

        int ans = -1;
        for (int l = n, r = m + 1; l < r; ) {
            int mid = l + (r - l) / 2;
            if (check(nums, changeIndices, mid)) {
                r = mid; ans = mid;
            }  else l = mid + 1;
        }
        return ans;
    }

    private boolean check(int[] nums, int[] ci, int sec) {
        int[] last = new int[nums.length];
        Arrays.fill(last, -1);
        for (int s = 0; s < sec; s++) last[ci[s] - 1] = s;
        for (var t : last) if (t < 0) return false;

        int cnt = 0;
        for (int s = 0; s < sec; s++) {
            int idx = ci[s] - 1;
            if (s == last[idx]) {
                if (nums[idx] > cnt) return false;
                cnt -= nums[idx];
            } else cnt++;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
