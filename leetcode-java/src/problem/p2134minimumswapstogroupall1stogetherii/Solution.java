package problem.p2134minimumswapstogroupall1stogetherii;

/**
 * 2134. Minimum Swaps to Group All 1's Together II
 *
 * https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii/
 *
 * A swap is defined as taking two distinct positions in an array and swapping the values in them.
 *
 * A circular array is defined as an array where we consider the first element and the last element to be adjacent.
 *
 * Given a binary circular array nums, return the minimum number of swaps required to
 * group all 1's present in the array together at any location.
 */

public class Solution {

    public int minSwaps(int[] nums) {
        int ones = 0, n = nums.length;
        for (var v : nums) ones += v;

        int ans = n, curr = 0;
        for (int l = 0, r = 0; r < 2 * n; r++) {
            curr += nums[r % n];
            if (r - l + 1 > ones) curr -= nums[l++ % n];
            if (r - l + 1 == ones) ans = Math.min(ans, ones - curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSwaps(new int[]{0,1,0,1,1,0,0}) == 1;
        assert new Solution().minSwaps(new int[]{0,1,1,1,0,0,1,1,0}) == 2;
        assert new Solution().minSwaps(new int[]{1,1,0,0,1}) == 0;
    }

}
