package offer2.p6;

import common.Checker;

/**
 * 剑指 Offer II 006. 排序数组中两个数字之和
 *
 * https://leetcode-cn.com/problems/kLl5u1/
 *
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，
 * 所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
 *
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 */

public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int s = numbers[l] + numbers[r];
            if (s == target) return new int[]{l, r};
            else if (s > target) r--;
            else l++;
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().twoSum(new int[]{1,2,4,6,10}, 8), new int[]{1,3});
        assert Checker.check(new Solution().twoSum(new int[]{2,3,4}, 6), new int[]{0,2});
        assert Checker.check(new Solution().twoSum(new int[]{-1,0}, -1), new int[]{0,1});
    }

}
