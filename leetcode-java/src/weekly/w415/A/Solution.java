package weekly.w415.A;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 3289. The Two Sneaky Numbers of Digitville
 *
 * https://leetcode.cn/contest/weekly-contest-415/problems/the-two-sneaky-numbers-of-digitville/
 *
 * In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1.
 *
 * Each number was supposed to appear exactly once in the list, however, two mischievous
 * numbers sneaked in an additional time, making the list longer than usual.
 *
 * As the town detective, your task is to find these two sneaky numbers. Return an array of size
 * two containing the two numbers (in any order), so peace can return to Digitville.
 */

public class Solution {

    public int[] getSneakyNumbers(int[] nums) {
        Arrays.sort(nums);

        int[] ans = new int[2];
        for (int i = 1, j = 0; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                ans[j++] = nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().getSneakyNumbers(new int[]{0, 1, 0, 1}));
    }

}
