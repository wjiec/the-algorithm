package daily.d240105p1944numberofvisiblepeopleinaqueue;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1944. Number of Visible People in a Queue
 *
 * https://leetcode.cn/problems/number-of-visible-people-in-a-queue
 *
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
 * You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
 *
 * A person can see another person to their right in the queue if everybody in between is shorter than both of them.
 * More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
 *
 * Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
 */

public class Solution {

    public int[] canSeePersonsCount(int[] heights) {
        // rightMax[i] 表示右边比 heights[i] 大的元素的位置
        int[] rightMax = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                rightMax[stack.remove()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) rightMax[stack.remove()] = -1;

        int[] ans = new int[heights.length];
        for (int i = 0; i < heights.length - 1; i++) {
            for (int next = i + 1; next >= 0; next = rightMax[next]) {
                ans[i]++; if (heights[next] > heights[i]) break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().canSeePersonsCount(new int[]{10,6,8,5,11,9}), new int[]{3,1,2,1,1,0});
        assert Checker.check(new Solution().canSeePersonsCount(new int[]{5,1,2,3,10}), new int[]{4,1,1,1,0});
    }

}
