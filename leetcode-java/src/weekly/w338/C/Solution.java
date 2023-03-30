package weekly.w338.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2602. Minimum Operations to Make All Array Elements Equal
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/
 *
 * You are given an array nums consisting of positive integers.
 *
 * You are also given an integer array queries of size m.
 * For the ith query, you want to make all of the elements of nums equal to queries[i].
 *
 * You can perform the following operation on the array any number of times:
 *
 * Increase or decrease an element of the array by 1.
 * Return an array answer of size m where answer[i] is the minimum number of operations to
 * make all elements of nums equal to queries[i].
 *
 * Note that after each query the array is reset to its original state.
 */

public class Solution {

    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);

        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        List<Long> ans = new ArrayList<>();
        for (var query : queries) {
            int idx = lowerBound(nums, query);
            long left = (long) query * idx - sum[idx];
            long right = sum[n] - sum[idx] -  (long) query * (n - idx);
            ans.add(left + right);
        }
        return ans;
    }

    private int lowerBound(int[] nums, int query) {
        int l = -1, r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < query) l = mid;
            else r = mid;
        }
        return r;
    }

    public static void main(String[] args) {
    }

}
