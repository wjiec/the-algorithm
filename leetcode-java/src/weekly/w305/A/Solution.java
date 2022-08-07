package weekly.w305.A;

/**
 * 6136. Number of Arithmetic Triplets
 *
 * https://leetcode.cn/contest/weekly-contest-305/problems/number-of-arithmetic-triplets/
 *
 * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff.
 * A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
 *
 * i < j < k,
 * nums[j] - nums[i] == diff, and
 * nums[k] - nums[j] == diff.
 *
 * Return the number of unique arithmetic triplets.
 */

public class Solution {

    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
