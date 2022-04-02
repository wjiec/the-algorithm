package weekly.bw75.p1findtriangularsumofanarray;

/**
 * 6034. Find Triangular Sum of an Array
 *
 * https://leetcode-cn.com/contest/biweekly-contest-75/problems/find-triangular-sum-of-an-array/
 *
 * You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
 *
 * The triangular sum of nums is the value of the only element present in nums after the following process terminates:
 *
 * Let nums comprise of n elements. If n == 1, end the process. Otherwise,
 * create a new 0-indexed integer array newNums of length n - 1.
 *
 * For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10,
 * where % denotes modulo operator.
 *
 * Replace the array nums with newNums.
 * Repeat the entire process starting from step 1.
 * Return the triangular sum of nums.
 */

public class Solution {

    public int triangularSum(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            int next = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                int temp = nums[j];
                nums[j] = (nums[j] + next) % 10;
                next = temp;
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {
        assert new Solution().triangularSum(new int[]{1,2,3,4,5}) == 8;
        assert new Solution().triangularSum(new int[]{5}) == 5;
    }

}
