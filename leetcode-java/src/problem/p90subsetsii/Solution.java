package problem.p90subsetsii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 *
 * https://leetcode-cn.com/problems/subsets-ii/
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */

public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int[] nums, List<List<Integer>> ans, List<Integer> curr, int i) {
        ans.add(new ArrayList<>(curr));

        int prev = -0xff;
        for (; i < nums.length; i++) {
            if (nums[i] != 0xff && nums[i] != prev) {
                int temp = nums[i]; nums[i] = 0xff;

                curr.add(temp);
                dfs(nums, ans, curr, i);

                nums[i] = temp; prev = temp;
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subsetsWithDup(new int[]{1,2,2}));
        System.out.println(new Solution().subsetsWithDup(new int[]{0}));

        System.out.println(new Solution().subsetsWithDup(new int[]{2,2,2}));
    }

}
