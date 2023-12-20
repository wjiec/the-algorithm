package weekly.w376.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2966. Divide Array Into Arrays With Max Difference
 *
 * https://leetcode.cn/contest/weekly-contest-376/problems/divide-array-into-arrays-with-max-difference/
 *
 * You are given an integer array nums of size n and a positive integer k.
 *
 * Divide the array into one or more arrays of size 3 satisfying the following conditions:
 *
 * Each element of nums should be in exactly one array.
 *
 * The difference between any two elements in one array is less than or equal to k.
 *
 * Return a 2D array containing all the arrays. If it is impossible to satisfy
 * the conditions, return an empty array.
 *
 * And if there are multiple answers, return any of them.
 */

public class Solution {

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 3) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            int mx = Math.max(a, Math.max(b, c));
            int mi = Math.min(a, Math.min(b, c));
            if (mx - mi > k) return new int[][]{};
            ans.add(new int[]{a, b, c});
        }
        return ans.toArray(new int[][]{});
    }

    public static void main(String[] args) {
    }

}
