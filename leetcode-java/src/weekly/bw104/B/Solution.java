package weekly.bw104.B;

import java.util.PriorityQueue;

/**
 * 6367. Sum in a Matrix
 *
 * https://leetcode.cn/contest/biweekly-contest-104/problems/sum-in-a-matrix/
 *
 * You are given a 0-indexed 2D integer array nums. Initially, your score is 0.
 * Perform the following operations until the matrix becomes empty:
 *
 * From each row in the matrix, select the largest number and remove it.
 * In the case of a tie, it does not matter which number is chosen.
 *
 * Identify the highest number amongst all those removed in step 1. Add that number to your score.
 *
 * Return the final score.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int matrixSum(int[][] nums) {
        PriorityQueue<Integer>[] queues = new PriorityQueue[nums.length];
        for (int i = 0; i < nums.length; i++) {
            queues[i] = new PriorityQueue<>((a, b) -> b - a);
            for (var v : nums[i]) queues[i].add(v);
        }

        int ans = 0;
        while (!queues[0].isEmpty()) {
            int curr = 0;
            for (var q : queues) curr = Math.max(curr, q.remove());
            ans += curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
