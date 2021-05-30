package weekly.bw53.p1p5755minimizemaximumpairsuminarray;

import java.util.Arrays;

/**
 * 5755. Minimize Maximum Pair Sum in Array
 *
 * https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/
 *
 * The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
 *
 * For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be
 * max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 *
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 *
 * Each element of nums is in exactly one pair, and
 * The maximum pair sum is minimized.
 *
 * Return the minimized maximum pair sum after optimally pairing up the elements.
 */

public class Solution {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, mid = n / 2, ans = 0;
        for (int i = 0; i < mid; i++) {
            ans = Math.max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minPairSum(new int[]{3,5,2,3}) == 7;
        assert new Solution().minPairSum(new int[]{3,5,4,2,4,6}) == 8;
    }

}
