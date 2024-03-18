package weekly.bw126.B;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 3080. Mark Elements on Array by Performing Queries
 *
 * https://leetcode.cn/contest/biweekly-contest-126/problems/mark-elements-on-array-by-performing-queries/
 *
 * You are given a 0-indexed array nums of size n consisting of positive integers.
 *
 * You are also given a 2D array queries of size m where queries[i] = [indexi, ki].
 *
 * Initially all elements of the array are unmarked.
 *
 * You need to apply m queries on the array in order, where on the ith query you do the following:
 *
 * Mark the element at index indexi if it is not already marked.
 * Then mark ki unmarked elements in the array with the smallest values. If multiple
 * such elements exist, mark the ones with the smallest indices. And if less than ki
 * unmarked elements exist, then mark all of them.
 *
 * Return an array answer of size m where answer[i] is the sum of unmarked
 * elements in the array after the ith query.
 */

public class Solution {

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (nums[a] != nums[b]) return nums[a] - nums[b];
            return a - b;
        });

        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            pq.add(i); sum += nums[i];
        }

        long[] ans = new long[queries.length];
        Set<Integer> marked = new HashSet<>();
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], k = queries[i][1];
            if (marked.add(idx)) sum -= nums[idx];
            while (k > 0 && !pq.isEmpty()) {
                int x = pq.remove();
                if (marked.add(x)) {
                    sum -= nums[x];
                    k--;
                }
            }
            ans[i] = sum;
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
