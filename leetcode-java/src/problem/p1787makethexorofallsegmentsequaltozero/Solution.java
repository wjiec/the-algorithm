package problem.p1787makethexorofallsegmentsequaltozero;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1787. Make the XOR of All Segments Equal to Zero
 *
 * https://leetcode.cn/problems/make-the-xor-of-all-segments-equal-to-zero/
 *
 * You are given an array nums and an integer k. The XOR of a segment [left, right] where
 * left <= right is the XOR of all the elements with indices between left and right,
 * inclusive: nums[left] XOR nums[left+1] XOR ... XOR nums[right].
 *
 * Return the minimum number of elements to change in the array such that the XOR of all
 * segments of size k is equal to zero.
 */

public class Solution {

    public int minChanges(int[] nums, int k) {
        final int DP_SIZE = 1 << 10;
        final int INF_VALUE = Integer.MAX_VALUE / 2;

        int[] curr = new int[DP_SIZE];
        for (int i = 1; i < curr.length; i++) curr[i] = INF_VALUE;
        int[] next = new int[DP_SIZE];

        for (int i = 0; i < k; i++) {
            // 计算第 i 个组的数字选择
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int j = i; j < nums.length; j += k) {
                cnt.merge(nums[j], 1, Integer::sum);
            }

            int dpMin = INF_VALUE;
            for (var v : curr) dpMin = Math.min(dpMin, v);
            Arrays.fill(next, dpMin);

            for (int mask = 0; mask < DP_SIZE; mask++) {
                for (var e : cnt.entrySet()) {
                    int ek = e.getKey(), ev = e.getValue();
                    next[mask] = Math.min(next[mask], curr[mask ^ ek] - ev);
                }
            }

            int size = nums.length / k + (i < nums.length % k ? 1 : 0);
            for (int j = 0; j < DP_SIZE; j++) {
                next[j] += size;
            }

            int[] temp = curr;
            curr = next;
            next = temp;
        }

        return curr[0];
    }

    public static void main(String[] args) {
        assert new Solution().minChanges(new int[]{23,27,14,0,14,3,7,10,14,23,5,5}, 1) == 11;
        assert new Solution().minChanges(new int[]{26,19,19,28,13,14,6,25,28,19,0,15,25,11}, 3) == 11;

        assert new Solution().minChanges(new int[]{1,2,0,3,0}, 1) == 3;
        assert new Solution().minChanges(new int[]{3,4,5,2,1,7,3,4,7}, 3) == 3;
        assert new Solution().minChanges(new int[]{1,2,4,1,2,5,1,2,6}, 3) == 3;
    }

}
