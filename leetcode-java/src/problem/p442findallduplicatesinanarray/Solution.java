package problem.p442findallduplicatesinanarray;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. Find All Duplicates in an Array
 *
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 *
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n]
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 */

public class Solution {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                ans.add(Math.abs(nums[i]));
            }

            nums[index] = -nums[index];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findDuplicates(new int[]{10,2,5,10,9,1,1,4,3,7}), List.of(10, 1));

        assert Checker.anyOrder(new Solution().findDuplicates(new int[]{4,3,2,7,8,2,3,1}), List.of(2, 3));
        assert Checker.anyOrder(new Solution().findDuplicates(new int[]{1,1,2}), List.of(1));
        assert Checker.anyOrder(new Solution().findDuplicates(new int[]{1}), List.of());
    }

}
