package weekly.w475.A;

/**
 * Q1. Minimum Distance Between Three Equal Elements I
 *
 * https://leetcode.cn/contest/weekly-contest-475/problems/minimum-distance-between-three-equal-elements-i/
 *
 * You are given an integer array nums.
 *
 * A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
 *
 * The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
 *
 * Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
 */

public class Solution {

    public int minimumDistance(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) continue;
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[j] != nums[k]) continue;
                    ans = Math.min(ans, (j - i) + (k - j) + (k - i));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
