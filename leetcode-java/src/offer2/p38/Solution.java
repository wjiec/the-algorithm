package offer2.p38;

import common.Checker;

import java.util.ArrayDeque;

/**
 * 剑指 Offer II 038. 每日温度
 *
 * https://leetcode.cn/problems/iIQa4I/
 *
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：
 * 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                ans[prev] = i - prev;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}), new int[]{1,1,4,2,1,1,0,0});
        assert Checker.check(new Solution().dailyTemperatures(new int[]{30,40,50,60}), new int[]{1,1,1,0});
        assert Checker.check(new Solution().dailyTemperatures(new int[]{30,60,90}), new int[]{1,1,0});
    }

}
