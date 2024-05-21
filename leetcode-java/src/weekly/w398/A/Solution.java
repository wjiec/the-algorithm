package weekly.w398.A;

/**
 * 3151. Special Array I
 *
 * https://leetcode.cn/contest/weekly-contest-398/problems/special-array-i/
 *
 * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
 *
 * You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.
 */

public class Solution {

    public boolean isArraySpecial(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
