package weekly.w365.A;

/**
 * 2873. Maximum Value of an Ordered Triplet I
 *
 * https://leetcode.cn/contest/weekly-contest-365/problems/maximum-value-of-an-ordered-triplet-i/
 *
 * You are given a 0-indexed integer array nums.
 *
 * Return the maximum value over all triplets of indices (i, j, k) such that i < j < k.
 * If all such triplets have a negative value, return 0.
 *
 * The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].
 */

public class Solution {

    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    ans = Math.max(ans, (long) (nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
