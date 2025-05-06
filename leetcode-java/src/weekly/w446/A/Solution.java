package weekly.w446.A;

import java.util.HashSet;

/**
 * 3522. Calculate Score After Performing Instructions
 *
 * https://leetcode.cn/contest/weekly-contest-446/problems/calculate-score-after-performing-instructions/
 *
 * You are given two arrays, instructions and values, both of size n.
 *
 * You need to simulate a process based on the following rules:
 *
 * You start at the first instruction at index i = 0 with an initial score of 0.
 * If instructions[i] is "add":
 * Add values[i] to your score.
 * Move to the next instruction (i + 1).
 * If instructions[i] is "jump":
 * Move to the instruction at index (i + values[i]) without modifying your score.
 * The process ends when you either:
 *
 * Go out of bounds (i.e., i < 0 or i >= n), or
 * Attempt to revisit an instruction that has been previously executed. The revisited instruction is not executed.
 *
 * Return your score at the end of the process.
 */

public class Solution {

    public long calculateScore(String[] instructions, int[] values) {
        long ans = 0; int i = 0; HashSet<Integer> seen = new HashSet<>();
        while (seen.add(i) && i >= 0 && i < instructions.length) {
            if (instructions[i].equals("add")) ans += values[i++];
            else i += values[i];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
