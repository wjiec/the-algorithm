package problem.p491increasingsubsequences;

import java.util.ArrayList;
import java.util.List;

/**
 * 491. Increasing Subsequences
 *
 * https://leetcode-cn.com/problems/increasing-subsequences/
 *
 * Given an integer array nums, return all the different possible increasing subsequences of the given array
 * with at least two elements. You may return the answer in any order.
 *
 * The given array may contain duplicates, and two equal integers should also be
 * considered a special case of increasing sequence.
 */

public class Solution {

    private List<Integer> curr = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(int[] nums, int i, int last) {
        if (i == nums.length) {
            if (curr.size() > 1) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        if (nums[i] >= last) {
            curr.add(nums[i]);
            dfs(nums, i + 1, nums[i]);
            curr.remove(curr.size() - 1);
        }
        if (nums[i] != last) {
            dfs(nums, i + 1, last);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findSubsequences(new int[]{4,6,7,7}));
        System.out.println(new Solution().findSubsequences(new int[]{4,4,3,2,1}));
    }

}
