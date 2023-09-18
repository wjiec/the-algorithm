package weekly.bw113.B;

import java.util.List;

/**
 * 2856. Minimum Array Length After Pair Removals
 *
 * https://leetcode.cn/contest/biweekly-contest-113/problems/minimum-array-length-after-pair-removals/
 *
 * You are given a 0-indexed sorted array of integers nums.
 *
 * You can perform the following operation any number of times:
 *
 * Choose two indices, i and j, where i < j, such that nums[i] < nums[j].
 *
 * Then, remove the elements at indices i and j from nums. The remaining elements
 * retain their original order, and the array is re-indexed.
 *
 * Return an integer that denotes the minimum length of nums after performing the operation
 * any number of times (including zero).
 *
 * Note that nums is sorted in non-decreasing order.
 */

public class Solution {

    public int minLengthAfterRemovals(List<Integer> nums) {
        int max = 0, curr = 1, val = nums.get(0), n = nums.size();
        for (int i = 1; i < n; i++) {
            if (nums.get(i) != val) {
                val = nums.get(i); curr = 1;
            } else curr++;
            max = Math.max(max, curr);
        }

        if (max * 2 > n) return max - (n - max);
        return n % 2;
    }

    public static void main(String[] args) {
        assert new Solution().minLengthAfterRemovals(List.of(1, 1)) == 2;
        assert new Solution().minLengthAfterRemovals(List.of(1, 1, 2, 3, 4, 4)) == 0;
        assert new Solution().minLengthAfterRemovals(List.of(1, 3, 4, 9)) == 0;
        assert new Solution().minLengthAfterRemovals(List.of(1, 3, 3, 3, 4)) == 1;
        assert new Solution().minLengthAfterRemovals(List.of(2, 3, 4, 4, 4)) == 1;
    }

}
