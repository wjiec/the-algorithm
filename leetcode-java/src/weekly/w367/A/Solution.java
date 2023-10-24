package weekly.w367.A;

/**
 * 2903. Find Indices With Index and Value Difference I
 *
 * https://leetcode.cn/contest/weekly-contest-367/problems/find-indices-with-index-and-value-difference-i/
 *
 * You are given a 0-indexed integer array nums having length n, an integer
 * indexDifference, and an integer valueDifference.
 *
 * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
 *
 * abs(i - j) >= indexDifference, and
 * abs(nums[i] - nums[j]) >= valueDifference
 * Return an integer array answer, where answer = [i, j] if there are two
 * such indices, and answer = [-1, -1] otherwise. If there are multiple choices
 * for the two indices, return any of them.
 *
 * Note: i and j may be equal.
 */

public class Solution {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (Math.abs(i - j) >= indexDifference && Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
    }

}
