package weekly.bw98.C;

/**
 * 6360. Minimum Impossible OR
 *
 * https://leetcode.cn/problems/minimum-impossible-or/
 *
 * You are given a 0-indexed integer array nums.
 *
 * We say that an integer x is expressible from nums if there exist
 * some integers 0 <= index1 < index2 < ... < indexk < nums.length
 * for which nums[index1] | nums[index2] | ... | nums[indexk] = x.
 *
 * In other words, an integer is expressible if it can be written as the
 * bitwise OR of some subsequence of nums.
 *
 * Return the minimum positive non-zero integer that is not expressible from nums.
 */

public class Solution {

    public int minImpossibleOR(int[] nums) {
        int mask = 0;
        for (var v : nums) {
            if ((v & (v - 1)) == 0) {
                mask |= v;
            }
        }
        mask = ~mask;
        return mask & -mask;
    }

    public static void main(String[] args) {
        assert new Solution().minImpossibleOR(new int[]{1, 4}) == 2;
        assert new Solution().minImpossibleOR(new int[]{1, 2}) == 4;
        assert new Solution().minImpossibleOR(new int[]{1, 25, 2, 72}) == 4;
    }

}
