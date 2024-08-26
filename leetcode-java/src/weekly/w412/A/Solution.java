package weekly.w412.A;

import java.util.PriorityQueue;

/**
 * 3265. Count Almost Equal Pairs I
 *
 * https://leetcode.cn/contest/weekly-contest-412/problems/count-almost-equal-pairs-i/
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call two integers x and y in this problem almost equal if both integers can become
 * equal after performing the following operation at most once:
 *
 * Choose either x or y and swap any two digits within the chosen number.
 * Return the number of indices i and j in nums where i < j such that nums[i] and nums[j] are almost equal.
 *
 * Note that it is allowed for an integer to have leading zeros after performing an operation.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (nums[a] != nums[b]) return nums[a] - nums[b];
            return a - b;
        });

        for (int i = 0; i < nums.length; i++) pq.add(i);
        for (int i = 0; i < k; i++) {
            var idx = pq.remove();
            nums[idx] *= multiplier;
            pq.add(idx);
        }

        return nums;
    }

    public static void main(String[] args) {
    }

}
