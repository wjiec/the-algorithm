package lcci.s16.p24pairswithsumlcci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * https://leetcode.cn/problems/pairs-with-sum-lcci/
 *
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 */

public class Solution {

    public List<List<Integer>> pairSums(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        for (int l = 0, r = nums.length - 1; l < r; ) {
            if (nums[l] + nums[r] == target) {
                ans.add(List.of(nums[l++], nums[r--]));
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pairSums(new int[]{5,6,5}, 11));
        System.out.println(new Solution().pairSums(new int[]{5,6,5,6}, 11));
    }

}
