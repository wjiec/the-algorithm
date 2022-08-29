package problem.p1674minimummovestomakearraycomplementary;

import common.TODO;

/**
 * 1674. Minimum Moves to Make Array Complementary
 *
 * https://leetcode.cn/problems/minimum-moves-to-make-array-complementary/
 *
 * You are given an integer array nums of even length n and an integer limit. In one move, you can
 * replace any integer from nums with another integer between 1 and limit, inclusive.
 *
 * The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals
 * the same number. For example, the array [1,2,3,4] is complementary because for all
 * indices i, nums[i] + nums[n - 1 - i] = 5.
 *
 * Return the minimum number of moves required to make nums complementary.
 */

public class Solution {

    @TODO
    public int minMoves(int[] nums, int limit) {
        // 2 个 [1, limit] 范围的数的和属于 [2, 2 * limit]
        int[] diff = new int[limit * 2 + 2];
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            // [2, 2 * limit] 范围 + 2
            diff[2] += 2; diff[2 * limit + 1] -= 2;
            // [1 + min(A, B), limit + max(A, B)] 范围 -1
            diff[1 + Math.min(nums[l], nums[r])] += -1;
            diff[limit + Math.max(nums[l], nums[r]) + 1] -= -1;
            // [A + B] 再 -1
            diff[nums[l] + nums[r]] += -1;
            diff[nums[l] + nums[r] + 1] -= -1;
        }

        int ans = nums.length, sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            if ((sum += diff[i]) < ans) ans = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMoves(new int[]{1,2,4,3}, 4) == 1;
        assert new Solution().minMoves(new int[]{1,2,2,1}, 2) == 2;
        assert new Solution().minMoves(new int[]{1,2,1,2}, 2) == 0;
    }

}
