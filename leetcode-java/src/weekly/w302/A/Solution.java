package weekly.w302.A;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 6120. Maximum Number of Pairs in Array
 *
 * https://leetcode.cn/contest/weekly-contest-302/problems/maximum-number-of-pairs-in-array/
 *
 * You are given a 0-indexed integer array nums. In one operation, you may do the following:
 *
 * Choose two integers in nums that are equal.
 * Remove both integers from nums, forming a pair.
 * The operation is done on nums as many times as possible.
 *
 * Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs
 * that are formed and answer[1] is the number of leftover integers in nums after doing the
 * operation as many times as possible.
 */

public class Solution {

    public int[] numberOfPairs(int[] nums) {
        Arrays.sort(nums);

        int pair = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (var c : nums) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else if (stack.peek() == c) {
                pair++;
                stack.pop();
            }
        }
        return new int[]{pair, stack.size()};
    }

    public static void main(String[] args) {
    }

}
