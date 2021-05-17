package problem.p268missingnumber;

/**
 * 268. Missing Number
 *
 * https://leetcode-cn.com/problems/missing-number/
 *
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */

public class Solution {

    public int missingNumber(int[] nums) {
        int actualSum = 0, expectedSum = nums.length, sz = nums.length;
        for (int i = 0; i < sz; i++) {
            actualSum += nums[i];
            expectedSum += i;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        assert new Solution().missingNumber(new int[]{3,0,1}) == 2;
        assert new Solution().missingNumber(new int[]{0,1}) == 2;
        assert new Solution().missingNumber(new int[]{0,1}) == 2;
        assert new Solution().missingNumber(new int[]{9,6,4,2,3,5,7,0,1}) == 8;
        assert new Solution().missingNumber(new int[]{0}) == 1;
    }

}
