package problem.p2059minimumoperationstoconvertnumber;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2059. Minimum Operations to Convert Number
 *
 * https://leetcode.cn/problems/minimum-operations-to-convert-number/
 *
 * You are given a 0-indexed integer array nums containing distinct numbers, an integer
 * start, and an integer goal. There is an integer x that is initially set to start, and
 * you want to perform operations on x such that it is converted to goal.
 *
 * You can perform the following operation repeatedly on the number x:
 *
 * If 0 <= x <= 1000, then for any index i in the array (0 <= i < nums.length), you
 * can set x to any of the following:
 *
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i] (bitwise-XOR)
 *
 * Note that you can use each nums[i] any number of times in any order.
 * Operations that set x to be out of the range 0 <= x <= 1000 are valid, but
 * no more operations can be done afterward.
 *
 * Return the minimum number of operations needed to convert x = start
 * into goal, and -1 if it is not possible.
 */

public class Solution {

    public int minimumOperations(int[] nums, int start, int goal) {
        boolean[] visited = new boolean[1001];
        visited[start] = true;

        // [value, step]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int val = curr[0], step = curr[1];

            for (var n : nums) {
                int[] next = new int[]{val + n, val - n, val ^ n};
                for (var x : next) {
                    if (x == goal) return step + 1;
                    if (x >= 0 && x <= 1000 && !visited[x]) {
                        visited[x] = true;
                        queue.add(new int[]{x, step + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(new int[]{2,4,12}, 2, 12) == 2;
        assert new Solution().minimumOperations(new int[]{3,5,7}, 0, -4) == 2;
        assert new Solution().minimumOperations(new int[]{2,8,16}, 0, 1) == -1;
    }

}
