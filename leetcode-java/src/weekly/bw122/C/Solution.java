package weekly.bw122.C;

/**
 * 3012. Minimize Length of Array Using Operations
 *
 * https://leetcode.cn/contest/biweekly-contest-122/problems/minimize-length-of-array-using-operations/
 *
 * You are given a 0-indexed integer array nums containing positive integers.
 *
 * Your task is to minimize the length of nums by performing the following
 * operations any number of times (including zero):
 *
 * Select two distinct indices i and j from nums, such that nums[i] > 0 and nums[j] > 0.
 * Insert the result of nums[i] % nums[j] at the end of nums.
 * Delete the elements at indices i and j from nums.
 *
 * Return an integer denoting the minimum length of nums after performing the operation any number of times.
 */

public class Solution {

    public int minimumArrayLength(int[] nums) {
        int mi = Integer.MAX_VALUE;
        for (var v : nums) mi = Math.min(mi, v);
        for (var v : nums) if (v % mi > 0) return 1;

        int cnt = 0;
        for (var v : nums) if (v == mi) cnt++;
        return (cnt + 1) / 2;
    }

    public static void main(String[] args) {
        assert new Solution().minimumArrayLength(new int[]{15,15,15,21}) == 1;
        assert new Solution().minimumArrayLength(new int[]{2,4}) == 1;
        assert new Solution().minimumArrayLength(new int[]{5,2,2,2,9,10}) == 1;

        assert new Solution().minimumArrayLength(new int[]{1,4,3,1}) == 1;
        assert new Solution().minimumArrayLength(new int[]{5,5,5,10,5}) == 2;
        assert new Solution().minimumArrayLength(new int[]{2,3,4}) == 1;
    }

}
