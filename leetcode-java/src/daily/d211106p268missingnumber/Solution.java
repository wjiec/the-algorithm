package daily.d211106p268missingnumber;

/**
 * 268. Missing Number
 *
 * https://leetcode-cn.com/problems/missing-number/
 *
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 */

public class Solution {

    public int missingNumber(int[] nums) {
        int l = nums.length, sum = 0;
        for (var n : nums) sum += n;
        return ((l + 1) * l / 2) - sum;
    }

    public static void main(String[] args) {
        assert new Solution().missingNumber(new int[]{3,0,1}) == 2;
        assert new Solution().missingNumber(new int[]{0,1}) == 2;
        assert new Solution().missingNumber(new int[]{9,6,4,2,3,5,7,0,1}) == 8;
        assert new Solution().missingNumber(new int[]{0}) == 1;
    }

}
