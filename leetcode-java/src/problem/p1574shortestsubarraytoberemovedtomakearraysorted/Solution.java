package problem.p1574shortestsubarraytoberemovedtomakearraysorted;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 *
 * https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 *
 * Given an integer array arr, remove a subarray (can be empty) from arr such that
 * the remaining elements in arr are non-decreasing.
 *
 * Return the length of the shortest subarray to remove.
 *
 * A subarray is a contiguous subsequence of the array.
 */

public class Solution {

    @TODO
    public int findLengthOfShortestSubarray(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        // 找到所有可能的底部, 对于 [1, 2, 3, 10, 0, 7, 8, 9]
        // 我们可以从 1, 2, 3, 10 开始搭建非递减序列
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i);
            } else break;
        }
        // 整个数组都是非递减的, 那就不需要删除子数组了
        if (stack.size() == arr.length) return 0;

        // 从后向前枚举所有可能的顶部
        int ans = arr.length - stack.size(), prev = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= prev) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    stack.pop();
                }
                int top = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.min(ans, i - top - 1);
                prev = arr[i];
            } else break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findLengthOfShortestSubarray(new int[]{1,2,3,10,0,7,8,9}) == 2;

        assert new Solution().findLengthOfShortestSubarray(new int[]{1,2,3,10,4,2,3,5}) == 3;
        assert new Solution().findLengthOfShortestSubarray(new int[]{5,4,3,2,1}) == 4;
        assert new Solution().findLengthOfShortestSubarray(new int[]{1,2,3}) == 0;
        assert new Solution().findLengthOfShortestSubarray(new int[]{1}) == 0;
    }

}
