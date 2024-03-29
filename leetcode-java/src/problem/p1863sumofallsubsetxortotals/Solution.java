package problem.p1863sumofallsubsetxortotals;

/**
 * 1863. Sum of All Subset XOR Totals
 *
 * https://leetcode-cn.com/problems/sum-of-all-subset-xor-totals/
 *
 * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
 *
 * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 * Given an array nums, return the sum of all XOR totals for every subset of nums.
 *
 * Note: Subsets with the same elements should be counted multiple times.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 */

public class Solution {

    public int subsetXORSum(int[] nums) {
        int ans = 0;
        for (var num : nums) {
            ans |= num;
        }
        return ans << (nums.length - 1);
    }

    public static void main(String[] args) {
        assert new Solution().subsetXORSum(new int[]{1,3}) == 6;
        assert new Solution().subsetXORSum(new int[]{5,1,6}) == 28;
    }

}
