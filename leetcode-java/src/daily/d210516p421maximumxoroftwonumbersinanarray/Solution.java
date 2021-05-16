package daily.d210516p421maximumxoroftwonumbersinanarray;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 *
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 *
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
 * Follow up: Could you do this in O(n) runtime?
 */

public class Solution {

    // @TODO
    public int findMaximumXOR(int[] nums) {
        int rs = 0, sz = nums.length;
        for (int i = 0; i < sz; i++) {
            for (int j = i + 1; j < sz; j++) {
                rs = Math.max(rs, nums[i] ^ nums[j]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().findMaximumXOR(new int[]{3,10,5,25,2,8}) == 28;
        assert new Solution().findMaximumXOR(new int[]{0}) == 0;
        assert new Solution().findMaximumXOR(new int[]{2,4}) == 6;
        assert new Solution().findMaximumXOR(new int[]{8,10,2}) == 10;
        assert new Solution().findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}) == 127;
    }

}
