package weekly.w448.D;

/**
 * Q4. Find Sum of Array Product of Magical Sequences
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/find-sum-of-array-product-of-magical-sequences/
 *
 * You are given two integers, m and k, and an integer array nums.
 *
 * A sequence of integers seq is called magical if:
 * seq has a size of m.
 * 0 <= seq[i] < nums.length
 * The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
 * The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).
 *
 * Return the sum of the array products for all valid magical sequences.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A set bit refers to a bit in the binary representation of a number that has a value of 1.
 */

public class Solution {

    public int magicalSum(int m, int k, int[] nums) {
        // 使用 [0, n) 的任意组合使得 1 << seq[0] | 1 << seq[1] | ... | 1 << seq[m - 1] 有 k 个置位
        //  - 返回所有符合条件的排列的数组乘积之和
        //
        // 也就是需要有 k 个不同的 [0, n) 在组合中
        return 1;
    }

    public static void main(String[] args) {
    }

}
