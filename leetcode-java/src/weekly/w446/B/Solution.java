package weekly.w446.B;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 3523. Make Array Non-decreasing
 *
 * https://leetcode.cn/contest/weekly-contest-446/problems/make-array-non-decreasing/
 *
 * You are given an integer array nums. In one operation, you can select a subarray and
 * replace it with a single element equal to its maximum value.
 *
 * Return the maximum possible size of the array after performing zero or more operations
 * such that the resulting array is non-decreasing.
 */

public class Solution {

    public int maximumPossibleSize(int[] nums) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (var v : nums) {
            if (dq.isEmpty() || v >= dq.peek()) dq.push(v);
        }
        return dq.size();
    }

    public static void main(String[] args) {
        assert new Solution().maximumPossibleSize(new int[]{31,15,11,1,5,7,58,47,32}) == 2;
        assert new Solution().maximumPossibleSize(new int[]{2,27,15}) == 2;
        assert new Solution().maximumPossibleSize(new int[]{4,2,5,3,5}) == 3;
        assert new Solution().maximumPossibleSize(new int[]{1,2,3}) == 3;
    }

}
