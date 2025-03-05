package weekly.bw150.A;

/**
 * 3452. Sum of Good Numbers
 *
 * https://leetcode.cn/contest/biweekly-contest-150/problems/sum-of-good-numbers/
 *
 * Given an array of integers nums and an integer k, an element nums[i] is considered good
 * if it is strictly greater than the elements at indices i - k and i + k (if those indices exist).
 *
 * If neither of these indices exists, nums[i] is still considered good.
 *
 * Return the sum of all the good elements in the array.
 */

public class Solution {

    public int sumOfGoodNumbers(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean good = i - k < 0 || nums[i] > nums[i - k];
            good = good && (i + k >= nums.length || nums[i] > nums[i + k]);
            if (good) ans += nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
