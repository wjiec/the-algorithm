package weekly.bw76.p0findclosestnumbertozero;

/**
 * 6060. Find Closest Number to Zero
 *
 * https://leetcode-cn.com/contest/biweekly-contest-76/problems/find-closest-number-to-zero/
 *
 * Given an integer array nums of size n, return the number with the value closest to 0 in nums.
 *
 * If there are multiple answers, return the number with the largest value.
 */

public class Solution {

    public int findClosestNumber(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (var n : nums) {
            if (n == 0) return 0;
            if (n > 0 && n <= Math.abs(ans)) ans = n;
            if (n < 0 && -n < Math.abs(ans)) ans = n;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findClosestNumber(new int[]{-4,-2,1,4,8}) == 1;
        assert new Solution().findClosestNumber(new int[]{2,-1,1}) == 1;
        assert new Solution().findClosestNumber(new int[]{2,-1,-1}) == -1;
        assert new Solution().findClosestNumber(new int[]{-100000,-100000}) == -100000;
    }

}
