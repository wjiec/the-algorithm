package problem.p2089findtargetindicesaftersortingarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2089. Find Target Indices After Sorting Array
 *
 * https://leetcode-cn.com/problems/find-target-indices-after-sorting-array/
 *
 * You are given a 0-indexed integer array nums and a target element target.
 *
 * A target index is an index i such that nums[i] == target.
 *
 * Return a list of the target indices of nums after sorting nums in non-decreasing order.
 *
 * If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 */

public class Solution {

    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().targetIndices(new int[]{1,2,5,2,3}, 2).equals(List.of(1,2));
        assert new Solution().targetIndices(new int[]{1,2,5,2,3}, 3).equals(List.of(3));
        assert new Solution().targetIndices(new int[]{1,2,5,2,3}, 5).equals(List.of(4));
        assert new Solution().targetIndices(new int[]{1,2,5,2,3}, 4).equals(List.of());
    }

}
