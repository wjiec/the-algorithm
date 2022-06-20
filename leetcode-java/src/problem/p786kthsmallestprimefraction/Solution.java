package problem.p786kthsmallestprimefraction;

import common.Checker;

import java.util.PriorityQueue;

/**
 * 786. K-th Smallest Prime Fraction
 *
 * https://leetcode.cn/problems/k-th-smallest-prime-fraction/
 *
 * You are given a sorted integer array arr containing 1 and prime numbers,
 * where all the integers of arr are unique. You are also given an integer k.
 *
 * For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
 *
 * Return the kth smallest fraction considered. Return your answer as an array of
 * integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
            arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]);

        for (int i = 0; i < arr.length; i++) {
            queue.add(new int[]{0, i});
        }

        for (int i = 1; i < k; i++) {
            int[] curr = queue.remove();
            int a = curr[0], b = curr[1];
            if (a + 1 < b) {
                queue.add(new int[]{a + 1, b});
            }
        }
        return new int[]{arr[queue.peek()[0]], arr[queue.peek()[1]]};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3), new int[]{2, 5});
        assert Checker.check(new Solution().kthSmallestPrimeFraction(new int[]{1,7}, 1), new int[]{1, 7});
    }

}
