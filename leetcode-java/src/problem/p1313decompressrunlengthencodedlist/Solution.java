package problem.p1313decompressrunlengthencodedlist;

import common.Checker;

/**
 * 1313. Decompress Run-Length Encoded List
 *
 * https://leetcode-cn.com/problems/decompress-run-length-encoded-list/
 *
 * We are given a list nums of integers representing a list compressed with run-length encoding.
 *
 * Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).
 *
 * For each such pair, there are freq elements with value val concatenated in a sublist.
 *
 * Concatenate all the sublists from left to right to generate the decompressed list.
 *
 * Return the decompressed list.
 */

public class Solution {

    public int[] decompressRLElist(int[] nums) {
        int n = 0, l = nums.length;
        for (int i = 0; i < l; i += 2) {
            n += nums[i];
        }

        int[] ans = new int[n];
        for (int i = 0, j = 0; i < l; i += 2) {
            int v = nums[i + 1];
            for (int k = 0; k < nums[i]; k++) {
                ans[j++] = v;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().decompressRLElist(new int[]{1,2,3,4}), new int[]{2,4,4,4});
        assert Checker.check(new Solution().decompressRLElist(new int[]{1,1,2,3}), new int[]{1,3,3});
    }

}
