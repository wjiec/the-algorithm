package offer2.p81;

import common.Checker;

import java.util.*;

/**
 * 剑指 Offer II 081. 允许重复选择元素的组合
 *
 * https://leetcode.cn/problems/Ygoe9J/
 *
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 *
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        dfs(candidates, target, 0, new ArrayDeque<>());
        return ans;
    }

    private void dfs(int[] array, int target, int i, Deque<Integer> curr) {
        if (i == array.length) return;
        if (target == 0) { ans.add(new ArrayList<>(curr)); return; }

        if (array[i] <= target) {
            dfs(array, target, i + 1, curr);
            curr.push(array[i]);
            dfs(array, target - array[i], i, curr);
            curr.pop();
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().combinationSum(new int[]{2,3,6,7}, 7), List.of(
            List.of(7), List.of(3,2,2)
        ));

        assert Checker.anyOrder(new Solution().combinationSum(new int[]{2,3,5}, 8), List.of(
            List.of(2,2,2,2), List.of(3,3,2), List.of(5,3)
        ));

        assert Checker.anyOrder(new Solution().combinationSum(new int[]{2}, 1), List.of());
        assert Checker.anyOrder(new Solution().combinationSum(new int[]{1}, 1), List.of(List.of(1)));
        assert Checker.anyOrder(new Solution().combinationSum(new int[]{1}, 2), List.of(List.of(1,1)));
    }

}
