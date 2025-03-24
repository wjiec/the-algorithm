package weekly.w440.B;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 3478. Choose K Elements With Maximum Sum
 *
 * https://leetcode.cn/contest/weekly-contest-440/problems/choose-k-elements-with-maximum-sum/
 *
 * You are given two integer arrays, nums1 and nums2, both of length n, along with a positive integer k.
 *
 * For each index i from 0 to n - 1, perform the following:
 *
 * Find all indices j where nums1[j] is less than nums1[i].
 * Choose at most k values of nums2[j] at these indices to maximize the total sum.
 *
 * Return an array answer of size n, where answer[i] represents the result for the corresponding index i.
 */

public class Solution {

    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;

        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) index[i] = i;
        Arrays.sort(index, Comparator.comparingInt(i -> nums1[i]));

        long[] ans = new long[n]; long curr = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int j = 0; j < n; j++) {
            int i = index[j], pre = j != 0 ? index[j - 1] : -1;

            ans[i] = j != 0 && nums1[i] == nums1[pre] ? ans[pre] : curr;
            pq.add(nums2[i]); curr += nums2[i];
            if (pq.size() > k) curr -= pq.remove();
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findMaxSum(new int[]{4,2,1,5,3}, new int[]{10,20,30,40,50}, 2), new long[]{80,30,0,80,50});
        assert Checker.check(new Solution().findMaxSum(new int[]{2,2,2,2}, new int[]{3,1,2,3}, 1), new long[]{0,0,0,0});
    }

}
