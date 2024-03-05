package weekly.bw125.B;

import java.util.PriorityQueue;

/**
 * 3066. Minimum Operations to Exceed Threshold Value II
 *
 * https://leetcode.cn/contest/biweekly-contest-125/problems/minimum-operations-to-exceed-threshold-value-ii/
 *
 * You are given a 0-indexed integer array nums, and an integer k.
 *
 * In one operation, you will:
 *
 * Take the two smallest integers x and y in nums.
 * Remove x and y from nums.
 * Add min(x, y) * 2 + max(x, y) anywhere in the array.
 * Note that you can only apply the described operation if nums contains at least two elements.
 *
 * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
 */

public class Solution {

    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (var v : nums) pq.add((long) v);

        int ans = 0;
        while (pq.size() > 1 && pq.peek() < k) {
            long a = pq.remove();
            long b = pq.remove();
            pq.add(a * 2 + b);
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
