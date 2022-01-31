package problem.p40combinationsumii;

import java.util.*;

/**
 * 40. Combination Sum II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 */

public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, target, 0, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] candidates, int target, int index, List<List<Integer>> ans, List<Integer> combine) {
        if (target == 0) { ans.add(new ArrayList<>(combine)); return; }
        if (index >= candidates.length) return;

        int candidate = candidates[index];
        if (candidate <= target) {
            combine.add(candidate);
            dfs(candidates, target - candidate, index + 1, ans, combine);
            combine.remove(combine.size() - 1);
        }

        while (index < candidates.length && candidates[index] == candidate) index++;
        if (index < candidates.length && candidates[index] <= target) {
            dfs(candidates, target, index, ans, combine);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(new Solution().combinationSum2(new int[]{2,5,2,1,2}, 5));
    }

}
