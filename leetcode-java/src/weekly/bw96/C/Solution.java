package weekly.bw96.C;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2542. Maximum Subsequence Score
 *
 * https://leetcode.cn/problems/maximum-subsequence-score/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and
 * a positive integer k. You must choose a subsequence of indices from nums1 of length k.
 *
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 *
 * The sum of the selected elements from nums1 multiplied with the minimum of the
 * selected elements from nums2.
 *
 * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) *
 *  min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 *
 * Return the maximum possible score.
 *
 * A subsequence of indices of an array is a set that can be derived
 * from the set {0, 1, ..., n-1} by deleting some or no elements.
 */

public class Solution {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        Integer[] sorted = new Integer[nums1.length];
        for (int i = 0; i < nums1.length; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> nums2[b] - nums2[a]);

        long sum = 0, ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (var idx : sorted) {
            sum += nums1[idx];
            queue.add(nums1[idx]);
            if (queue.size() > k) sum -= queue.remove();
            if (queue.size() == k) {
                ans = Math.max(ans, sum * nums2[idx]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{2,1,14,12}, new int[]{11,7,13,6}, 3) == 168;
        assert new Solution().maxScore(new int[]{44,10,25,0,25,49,0}, new int[]{18,39,15,31,43,20,45}, 6) == 2304;

        assert new Solution().maxScore(new int[]{1,3,3,2}, new int[]{2,1,3,4}, 3) == 12;
        assert new Solution().maxScore(new int[]{4,2,3,1,1}, new int[]{7,5,10,9,6}, 1) == 30;
    }

}
