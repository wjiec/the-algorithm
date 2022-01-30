package problem.p39combinationsum;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. Combination Sum
 *
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 *
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 *
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is
 * less than 150 combinations for the given input.
 */

public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, target, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int index) {
        if (candidates.length == index) return;

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        dfs(candidates, target, ans, combine, index + 1);
        if (target - candidates[index] >= 0) {
            combine.add(candidates[index]);
            dfs(candidates, target - candidates[index], ans, combine, index);
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(new Solution().combinationSum(new int[]{2,3,5}, 8));
    }

}
