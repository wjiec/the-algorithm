package weekly.bw81.C;

/**
 * 6105. Maximum XOR After Operations
 *
 * https://leetcode.cn/contest/biweekly-contest-81/problems/maximum-xor-after-operations/
 *
 * You are given a 0-indexed integer array nums. In one operation, select any non-negative integer x
 * and an index i, then update nums[i] to be equal to nums[i] AND (nums[i] XOR x).
 *
 * Note that AND is the bitwise AND operation and XOR is the bitwise XOR operation.
 *
 * Return the maximum possible bitwise XOR of all elements of nums after applying the operation any number of times.
 */

public class Solution {

    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int base = 1 << i;
            boolean hasOne = false;
            for (var n : nums) {
                if ((base & n) != 0) {
                    hasOne = true;
                    break;
                }
            }
            if (hasOne) ans |= base;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
