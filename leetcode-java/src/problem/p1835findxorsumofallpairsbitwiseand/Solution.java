package problem.p1835findxorsumofallpairsbitwiseand;

/**
 * 1835. Find XOR Sum of All Pairs Bitwise AND
 *
 * https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and/
 *
 * The XOR sum of a list is the bitwise XOR of all its elements. If the list only contains
 * one element, then its XOR sum will be equal to this element.
 *
 * For example, the XOR sum of [1,2,3,4] is equal to 1 XOR 2 XOR 3 XOR 4 = 4, and the XOR sum of [3] is equal to 3.
 * You are given two 0-indexed arrays arr1 and arr2 that consist only of non-negative integers.
 *
 * Consider the list containing the result of arr1[i] AND arr2[j] (bitwise AND) for every (i, j) pair
 * where 0 <= i < arr1.length and 0 <= j < arr2.length.
 *
 * Return the XOR sum of the aforementioned list.
 */

public class Solution {

    // arr1 = [a, b, c]
    // arr2 = [x, y]
    // ans = a&x ^ a&y ^ b&x ^ b&y ^ c&x ^ c&y
    public int getXORSum(int[] arr1, int[] arr2) {
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int b = 1 << i, c1 = 0, c2 = 0;
            for (var v : arr1) if ((v & b) != 0) c1++;
            for (var v : arr2) if ((v & b) != 0) c2++;
            if ((c1 & 1) == 1 && (c2 & 1) == 1) ans |= b;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getXORSum(new int[]{1,2,3}, new int[]{6,5}) == 0;
        assert new Solution().getXORSum(new int[]{12}, new int[]{4}) == 4;
    }

}
