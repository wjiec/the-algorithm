package problem.p1004maxconsecutiveonesiii;

/**
 * 1004. Max Consecutive Ones III
 *
 * https://leetcode.cn/problems/max-consecutive-ones-iii/
 *
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 */

public class Solution {

    public int longestOnes(int[] nums, int k) {
        int ans = 0, zeros = 0, curr = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            curr++;
            if (nums[r] == 0) zeros++;
            for (; zeros > k; l++) {
                curr--;
                if (nums[l] == 0) zeros--;
            }
            if (curr > ans) ans = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2) == 6;
        assert new Solution().longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3) == 10;
    }

}
