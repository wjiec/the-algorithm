package problem.p735asteroidcollision;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 735. Asteroid Collision
 *
 * https://leetcode.cn/problems/asteroid-collision/
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size,
 * and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet,
 * the smaller one will explode.
 * If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 */

public class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) stack.push(asteroid);
            if (asteroid < 0) {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) stack.push(asteroid);
                if (!stack.isEmpty() && stack.peek() == -asteroid) stack.pop();
            }
        }

        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) ans[i] = stack.pop();
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().asteroidCollision(new int[]{5,10,-5}), new int[]{5, 10});
        assert Checker.check(new Solution().asteroidCollision(new int[]{8,-8}), new int[]{});
        assert Checker.check(new Solution().asteroidCollision(new int[]{10,2,-5}), new int[]{10});
        assert Checker.check(new Solution().asteroidCollision(new int[]{10,2}), new int[]{10,2});
        assert Checker.check(new Solution().asteroidCollision(new int[]{10,2,-11}), new int[]{-11});
        assert Checker.check(new Solution().asteroidCollision(new int[]{-1,-2,10,2,-11}), new int[]{-1,-2,-11});
        assert Checker.check(new Solution().asteroidCollision(new int[]{1,-1,-2,10,2,-11}), new int[]{-2,-11});
    }

}
