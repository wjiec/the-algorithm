package problem.p78subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 78. Subsets
 *
 * https://leetcode-cn.com/problems/subsets/
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */

public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int[] nums, List<List<Integer>> ans, List<Integer> curr, int i) {
        ans.add(new ArrayList<>(curr));
        for (; i < nums.length; i++) {
            if (nums[i] != 0xff) {
                int temp = nums[i]; nums[i] = 0xff;

                curr.add(temp);
                dfs(nums, ans, curr, i);

                nums[i] = temp;
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subsets(new int[]{1}));
        System.out.println(new Solution().subsets(new int[]{1,2}));
        System.out.println(new Solution().subsets(new int[]{1,2,3}));
        System.out.println(new Solution().subsets(new int[]{1,2,3,4}));
        System.out.println(new Solution().subsets(new int[]{1,2,3,4,5}));
        System.out.println(new Solution().subsets(new int[]{1,2,3,4,5,6}));
    }

}
