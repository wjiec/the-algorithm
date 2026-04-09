package weekly.bw172.C;

import java.util.PriorityQueue;

/**
 * Q3. Maximum Score After Binary Swaps
 *
 * https://leetcode.cn/contest/biweekly-contest-172/problems/maximum-score-after-binary-swaps/
 *
 * You are given an integer array nums of length n and a binary string s of the same length.
 *
 * Initially, your score is 0. Each index i where s[i] = '1' contributes nums[i] to the score.
 *
 * You may perform any number of operations (including zero). In one operation,
 * you may choose an index i such that 0 <= i < n - 1, where s[i] = '0', and s[i + 1] = '1',
 * and swap these two characters.
 *
 * Return an integer denoting the maximum possible score you can achieve.
 */

public class Solution {

    public long maximumScore(int[] nums, String s) {
        // 1 只能往左边移动, 那么每个 1 应该移动到左边最大的数上
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (s.charAt(i) == '1') ans += pq.remove();
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
