package offer2.p83;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 *
 * https://leetcode.cn/problems/VvJkup/
 *
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, new ArrayDeque<>());
        return ans;
    }

    private void dfs(int[] array, Deque<Integer> curr) {
        if (curr.size() == array.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != Integer.MIN_VALUE) {
                curr.push(array[i]);
                array[i] = Integer.MIN_VALUE;
                dfs(array, curr);
                array[i] = curr.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
        System.out.println(new Solution().permute(new int[]{0, 1}));
        System.out.println(new Solution().permute(new int[]{1}));
    }

}
