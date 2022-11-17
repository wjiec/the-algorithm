package offer2.p79;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 079. 所有子集
 *
 * https://leetcode.cn/problems/TVdhkn/
 *
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */

public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        int n = 1 << nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(select(nums, i));
        }
        return ans;
    }

    private List<Integer> select(int[] array, int state) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if ((state & (1 << i)) != 0) {
                ans.add(array[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().subsets(new int[]{1,2,3}), List.of(
            List.of(), List.of(1), List.of(2), List.of(3), List.of(1,2), List.of(2,3), List.of(1,3), List.of(1,2,3)
        ));
    }

}
