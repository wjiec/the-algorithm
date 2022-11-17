package offer2.p82;

import java.util.*;

/**
 * 剑指 Offer II 082. 含有重复元素集合的组合
 *
 * https://leetcode.cn/problems/4sjJUc/
 *
 * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 */

public class Solution {

    private final List<int[]> freq = new ArrayList<>();
    private final Deque<Integer> curr = new ArrayDeque<>();
    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (var v : candidates) {
            if (freq.isEmpty() || freq.get(freq.size() - 1)[0] != v) {
                freq.add(new int[]{v, 1});
            } else freq.get(freq.size() - 1)[1]++;
        }

        dfs(0, target);
        return ans;
    }

    private void dfs(int idx, int target) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (idx == freq.size()) return;

        int value = freq.get(idx)[0], count = freq.get(idx)[1];
        if (value > target) return;

        dfs(idx + 1, target);

        int most = Math.min(target / value, count);
        for (int i = 1; i <= most; i++) {
            curr.push(freq.get(idx)[0]);
            dfs(idx + 1, target - i * value);
        }
        for (int i = 1; i <= most; i++) curr.pop();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(new Solution().combinationSum2(new int[]{2,5,2,1,2}, 5));
    }

}
