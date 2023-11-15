package weekly.w371.A;

/**
 * 2932. Maximum Strong Pair XOR I
 *
 * https://leetcode.cn/contest/weekly-contest-371/problems/maximum-strong-pair-xor-i/
 *
 * You are given a 0-indexed integer array nums. A pair of integers x
 * and y is called a strong pair if it satisfies the condition:
 *
 * |x - y| <= min(x, y)
 *
 * You need to select two integers from nums such that they form a strong pair
 * and their bitwise XOR is the maximum among all strong pairs in the array.
 *
 * Return the maximum XOR value out of all possible strong pairs in the array nums.
 *
 * Note that you can pick the same integer twice to form a pair.
 */

public class Solution {

    public int maximumStrongPairXor(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])) {
                    ans = Math.max(ans, nums[i] ^ nums[j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
