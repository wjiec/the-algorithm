package offer2.p37;

import common.Checker;

import java.util.Stack;

/**
 * 剑指 Offer II 037. 小行星碰撞
 *
 * https://leetcode.cn/problems/XagZNi/
 *
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗小行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (var asteroid : asteroids) {
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0 && stack.peek() < -asteroid) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                if (stack.peek() >= -asteroid) {
                    if (stack.peek() == -asteroid) stack.pop();
                    continue;
                }
            }

            stack.push(asteroid);
        }

        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().asteroidCollision(new int[]{5, 10, -5}), new int[]{5, 10});
        assert Checker.check(new Solution().asteroidCollision(new int[]{8, -8}), new int[]{});
        assert Checker.check(new Solution().asteroidCollision(new int[]{10,2,-5}), new int[]{10});
        assert Checker.check(new Solution().asteroidCollision(new int[]{-2,-1,1,2}), new int[]{-2,-1,1,2});
    }

}
