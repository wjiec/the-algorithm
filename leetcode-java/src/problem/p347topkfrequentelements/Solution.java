package problem.p347topkfrequentelements;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 */

public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        for (int i = 0; i < nums.length; i++) {
            int r = upperBound(nums, nums[i]);
            queue.add(new int[]{r - i + 1, nums[i]});
            i = r;
            if (queue.size() > k) queue.remove();
        }

        int i = 0;
        int[] ans = new int[k];
        while (!queue.isEmpty()) ans[i++] = queue.remove()[1];

        return ans;
    }

    public int upperBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) l = mid + 1;
            else r = mid;
        }
        if (l == nums.length || nums[l] != target) return l - 1;
        return l;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1,2}, 2),
            new int[]{1,2});

        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1,1,1,2,2,3}, 2),
            new int[]{1, 2});

        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1}, 1),
            new int[]{1});
    }

}
