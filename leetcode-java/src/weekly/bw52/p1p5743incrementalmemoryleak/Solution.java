package weekly.bw52.p1p5743incrementalmemoryleak;

import common.Checker;

/**
 * 5743. Incremental Memory Leak
 *
 * https://leetcode-cn.com/contest/biweekly-contest-52/problems/incremental-memory-leak/
 *
 * You are given two integers memory1 and memory2 representing the available memory in bits on two memory sticks.
 * There is currently a faulty program running that consumes an increasing amount of memory every second.
 *
 * At the ith second (starting from 1), i bits of memory are allocated to the stick with
 * more available memory (or from the first memory stick if both have the same available memory).
 * If neither stick has at least i bits of available memory, the program crashes.
 *
 * Return an array containing [crashTime, memory1crash, memory2crash],
 * where crashTime is the time (in seconds) when the program crashed and memory1crash and memory2crash
 * are the available bits of memory in the first and second sticks respectively.
 */

public class Solution {

    public int[] memLeak(int memory1, int memory2) {
        if (memory1 == 0 && memory2 == 0) {
            return new int[]{1, 0, 0};
        }

        int i = 1;
        for (int max = Math.max(memory1, memory2); i <= max; i++, max = Math.max(memory1, memory2)) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
        }

        return new int[]{i, memory1, memory2};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().memLeak(2, 2), new int[]{3, 1, 0});
        assert Checker.check(new Solution().memLeak(8, 11), new int[]{6, 0, 4});
    }

}
