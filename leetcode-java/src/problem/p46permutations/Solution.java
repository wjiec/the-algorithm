package problem.p46permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 *
 * https://leetcode-cn.com/problems/permutations/
 *
 * Given an array nums of distinct integers, return all the possible permutations.
 *
 * You can return the answer in any order.
 */

public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 1) return List.of(List.of(nums[0]));
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, ans, new ArrayList<>());
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, List<Integer> curr) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0xff) {
                int v = nums[i];
                nums[i] = 0xff;

                curr.add(v);
                dfs(nums, ans, curr);
                curr.remove(curr.size() - 1);

                nums[i] = v;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1}));
        System.out.println(new Solution().permute(new int[]{1,2}));
        System.out.println(new Solution().permute(new int[]{1,2,3}));
        System.out.println(new Solution().permute(new int[]{1,2,3,4}));
        System.out.println(new Solution().permute(new int[]{1,2,3,4,5}));
        System.out.println(new Solution().permute(new int[]{1,2,3,4,5,6}));
    }

}
