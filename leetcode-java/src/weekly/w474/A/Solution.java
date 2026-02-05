package weekly.w474.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q1. Find Missing Elements
 *
 * https://leetcode.cn/contest/weekly-contest-474/problems/find-missing-elements/
 *
 * You are given an integer array nums consisting of unique integers.
 *
 * Originally, nums contained every integer within a certain range.
 * However, some integers might have gone missing from the array.
 *
 * The smallest and largest integers of the original range are still present in nums.
 *
 * Return a sorted list of all the missing integers in this range.
 * If no integers are missing, return an empty list.
 */

public class Solution {

    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            for (var v = nums[i - 1] + 1; v != nums[i]; v++) ans.add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMissingElements(new int[]{1,4,2,5}).equals(List.of(3));
    }

}
