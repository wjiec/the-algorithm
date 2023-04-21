package weekly.bw102.B;

/**
 * 2640. Find the Score of All Prefixes of an Array
 *
 * https://leetcode.cn/contest/biweekly-contest-102/problems/find-the-score-of-all-prefixes-of-an-array/
 *
 * We define the conversion array conver of an array arr as follows:
 *
 * conver[i] = arr[i] + max(arr[0..i]) where max(arr[0..i]) is the maximum value of arr[j] over 0 <= j <= i.
 * We also define the score of an array arr as the sum of the values of the conversion array of arr.
 *
 * Given a 0-indexed integer array nums of length n, return an array ans of length n
 * where ans[i] is the score of the prefix nums[0..i].
 */

public class Solution {

    public long[] findPrefixScore(int[] nums) {
        long[] ans = new long[nums.length];
        for (int i = 0, mx = 0; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            ans[i] = nums[i] + mx + (i != 0 ? ans[i - 1] : 0);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
