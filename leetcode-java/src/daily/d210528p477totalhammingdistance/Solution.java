package daily.d210528p477totalhammingdistance;

/**
 * 477. Total Hamming Distance
 *
 * https://leetcode-cn.com/problems/total-hamming-distance/
 *
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
 */

public class Solution {

    public int totalHammingDistance(int[] nums) {
        int ans = 0, sz = nums.length;
        for (int i = 0; i < 31; i++) {
            int v1 = 0;
            for (var n : nums) {
                v1 += ((n >> i) & 0x1);
            }
            ans += v1 * (sz - v1);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().totalHammingDistance(new int[]{4, 14, 2}) == 6;
    }

}
