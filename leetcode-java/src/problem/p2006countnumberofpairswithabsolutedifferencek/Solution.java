package problem.p2006countnumberofpairswithabsolutedifferencek;

/**
 * 2006. Count Number of Pairs With Absolute Difference K
 *
 * https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k/
 *
 * Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j
 * such that |nums[i] - nums[j]| == k.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0.
 * -x if x < 0.
 */

public class Solution {

    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countKDifference(new int[]{1,2,2,1}, 1) == 4;
        assert new Solution().countKDifference(new int[]{1,3}, 3) == 0;
        assert new Solution().countKDifference(new int[]{3,2,1,5,4}, 2) == 3;
    }

}
