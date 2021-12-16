package offer.p53queshideshuzilcof;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 *
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 *
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 *
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 */

public class Solution {

    public int missingNumber(int[] nums) {
        int total = nums.length * (nums.length + 1) / 2, sum = 0;
        for (var n : nums) sum += n;
        return total - sum;
    }

    public static void main(String[] args) {
        assert new Solution().missingNumber(new int[]{0,1,3}) == 2;
        assert new Solution().missingNumber(new int[]{0,1,2,3,4,5,6,7,9}) == 8;
    }

}
