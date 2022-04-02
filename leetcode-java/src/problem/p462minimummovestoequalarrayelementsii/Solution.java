package problem.p462minimummovestoequalarrayelementsii;

import java.util.Arrays;

/**
 * 462. Minimum Moves to Equal Array Elements II
 *
 * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements-ii/
 *
 * Given an integer array nums of size n, return the minimum number of moves
 * required to make all array elements equal.
 *
 * In one move, you can increment or decrement an element of the array by 1.
 *
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 */

public class Solution {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, mid = nums[nums.length / 2];
        for (var n : nums) ans += Math.abs(mid - n);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMoves2(new int[]{1,2,3}) == 2;
        // 22 / 4 = 5.5
        // avg = 5: 4 5 3 4 = 16
        // avg = 6: 5 4 4 3 = 16
        assert new Solution().minMoves2(new int[]{1,10,2,9}) == 16;
        // 2 / 4 = 0.5 -> 20
        // avg = 0: 1 10 2 9 = 22
        // avg = 1: 0 11 1 8 = 20
        assert new Solution().minMoves2(new int[]{1,-10,2,9}) == 20;
        assert new Solution().minMoves2(new int[]{1,-10,2,9,-651,999,-991,0,2,-1,-214748364,214748364}) == 429499394;
    }

}
