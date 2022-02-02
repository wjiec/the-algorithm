package problem.p47permutationsii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 *
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 */

public class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> ans, List<Integer> list) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0, prev = -0xff; i < nums.length; i++) {
            if (nums[i] != 0xff && nums[i] != prev) {
                int v = nums[i];
                nums[i] = 0xff;

                list.add(v);
                backtrack(nums, index + 1, ans, list);
                list.remove(list.size() - 1);

                prev = v;
                nums[i] = v;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1,1,2}));
        System.out.println(new Solution().permuteUnique(new int[]{1,1,1}));
        System.out.println(new Solution().permuteUnique(new int[]{1,1,1,2,2,2}));
        System.out.println(new Solution().permuteUnique(new int[]{1,2,3}));
    }

}
