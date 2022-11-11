package problem.p739dailytemperatures;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. Daily Temperatures
 *
 * https://leetcode.cn/problems/daily-temperatures/
 *
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait
 * after the ith day to get a warmer temperature.
 *
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            int value = temperatures[i];
            // 如果当前的值比「还未找到更大温度的值」高的话，就为这些下标赋值
            while (!stack.isEmpty() && value > temperatures[stack.peek()]) {
                int small = stack.pop();
                ans[small] = i - small;
            }
            // 这里保证每个下标只会处理一次
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().dailyTemperatures(new int[]{
            73,74,75,71,69,72,76,73
        }), new int[]{1,1,4,2,1,1,0,0});

        assert Checker.check(new Solution().dailyTemperatures(new int[]{
            30,40,50,60
        }), new int[]{1,1,1,0});

        assert Checker.check(new Solution().dailyTemperatures(new int[]{
            30,60,90
        }), new int[]{1,1,0});
    }

}
