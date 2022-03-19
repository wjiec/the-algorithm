package problem.p365waterandjugproblem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 365. Water and Jug Problem
 *
 * https://leetcode-cn.com/problems/water-and-jug-problem/
 *
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters.
 * There is an infinite amount of water supply available. Determine whether it is
 * possible to measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity
 * liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full,
 * or the first jug itself is empty.
 */

public class Solution {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Set<Long> visited = new HashSet<>();
        Deque<int[]> stack = new ArrayDeque<>();
        for (stack.push(new int[]{0, 0}); !stack.isEmpty(); ) {
            if (visited.contains(hash(stack.peek()))) {
                stack.pop(); continue;
            }

            int[] curr = stack.pop(); visited.add(hash(curr));
            int a = curr[0], b = curr[1];
            if (a == targetCapacity || b == targetCapacity || a + b == targetCapacity) {
                return true;
            }

            // 把1倒满
            if (!visited.contains(hash(jug1Capacity, b))) {
                stack.push(new int[]{jug1Capacity, b});
            }
            // 把2倒满
            if (!visited.contains(hash(a, jug2Capacity))) {
                stack.push(new int[]{a, jug2Capacity});
            }
            // 把1倒空
            if (!visited.contains(hash(0, b))) {
                stack.push(new int[]{0, b});
            }
            // 把2倒空
            if (!visited.contains(hash(a, 0))) {
                stack.push(new int[]{a, 0});
            }
            // 把1倒到2里面
            int v1 = Math.min(a, jug2Capacity - b); // a的水量和b剩余的水量最小值
            if (!visited.contains(hash(a - v1, b + v1))) {
                stack.push(new int[]{a - v1, b + v1});
            }
            // 把2倒进1里面
            int v2 = Math.min(b, jug1Capacity - a); // b的水量和a剩余的水量最小值
            if (!visited.contains(hash(a + v2, b - v2))) {
                stack.push(new int[]{a + v2, b - v2});
            }
        }
        return false;
    }

    private long hash(int[] p) { return ((long) p[0] << 32) | p[1]; }
    private long hash(int a, int b) { return ((long) a << 32) | b; }

    public static void main(String[] args) {
        assert new Solution().canMeasureWater(3, 5, 4);
        assert !new Solution().canMeasureWater(2, 6, 5);
        assert new Solution().canMeasureWater(1, 2, 3);
    }

}
