package weekly.w429.B;

import java.util.Arrays;

/**
 * 3397. Maximum Number of Distinct Elements After Operations
 *
 * https://leetcode.cn/contest/weekly-contest-429/problems/maximum-number-of-distinct-elements-after-operations/
 *
 * You are given an integer array nums and an integer k.
 *
 * You are allowed to perform the following operation on each element of the array at most once:
 *
 * Add an integer in the range [-k, k] to the element.
 * Return the maximum possible number of distinct elements in nums after performing the operations.
 */

public class Solution {

    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0, pre = Integer.MIN_VALUE;
        // 数字都尽量在大于前一个的前提下往左靠
        for (var v : nums) {
            int l = Math.max(pre + 1, v - k);
            if (l > v + k) continue;
            ans++; pre = l;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistinctElements(new int[]{4,4,4,4}, 1) == 3;
    }

}
