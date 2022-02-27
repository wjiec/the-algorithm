package daily.d220227p553optimaldivision;

/**
 * 553. Optimal Division
 *
 * https://leetcode-cn.com/problems/optimal-division/
 *
 * You are given an integer array nums. The adjacent integers in nums will perform the float division.
 *
 * For example, for nums = [2,3,4], we will evaluate the expression "2/3/4".
 * However, you can add any number of parenthesis at any position to change the priority of operations.
 *
 * You want to add these parentheses such the value of the expression after the evaluation is maximum.
 *
 * Return the corresponding expression that has the maximum value in string format.
 *
 * Note: your expression should not contain redundant parenthesis.
 */

public class Solution {

    public String optimalDivision(int[] nums) {
        if (nums.length == 1) return String.valueOf(nums[0]);
        if (nums.length == 2) return nums[0] + "/" + nums[1];

        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(");
        for (int i = 1; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i != nums.length - 1) sb.append("/");
        }
        return sb.append(")").toString();
    }

    public static void main(String[] args) {
        assert new Solution().optimalDivision(new int[]{1000,100,10,2}).equals("1000/(100/10/2)");
        assert new Solution().optimalDivision(new int[]{2,3,4}).equals("2/(3/4)");
        assert new Solution().optimalDivision(new int[]{2}).equals("2");
    }

}
