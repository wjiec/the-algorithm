package weekly.bw105.C;

import java.util.PriorityQueue;

/**
 * 2708. Maximum Strength of a Group
 *
 * https://leetcode.cn/contest/biweekly-contest-105/problems/maximum-strength-of-a-group/
 *
 * You are given a 0-indexed integer array nums representing the score of students in an exam.
 * The teacher would like to form one non-empty group of students with maximal strength, where
 * the strength of a group of students of indices i0, i1, i2, ... , ik is defined as
 * nums[i0] * nums[i1] * nums[i2] * ... * nums[ik].
 *
 * Return the maximum strength of a group the teacher can create.
 */

public class Solution {

    public long maxStrength(int[] nums) {
        if (nums.length == 1) return nums[0];

        long np = 0, nn = 0, pos = 1;
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        for (var v : nums) {
            if (v > 0) { np++; pos *= v; }
            else if (v < 0) { nn++; neg.add(v); }
        }
        if (np == 0 && nn < 2) return 0;

        for (; nn > 1; nn -= 2) {
            pos *= (long) neg.remove() * neg.remove();
        }
        return pos;
    }

    public static void main(String[] args) {
    }

}
