package problem.p1124longestwellperforminginterval;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1124. Longest Well-Performing Interval
 *
 * https://leetcode.cn/problems/longest-well-performing-interval/
 *
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than
 * the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 */

public class Solution {

    public int longestWPI(int[] hours) {
        int[] diff = new int[hours.length];
        for (int i = 0; i < hours.length; i++) {
            diff[i] = hours[i] > 8 ? 1 : -1;
        }

        int[] sum = new int[hours.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + diff[i - 1];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < sum.length; i++) {
            if (stack.isEmpty() || sum[stack.peek()] > sum[i]) {
                stack.push(i);
            }
        }

        int ans = 0;
        for (int i = hours.length; i > ans; i--) {
            while (!stack.isEmpty() && sum[stack.peek()] < sum[i]) {
                ans = Math.max(ans, i - stack.pop());
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestWPI(new int[]{9,9,6,0,6,6,9}) == 3;
        assert new Solution().longestWPI(new int[]{6,6,6}) == 0;
        assert new Solution().longestWPI(new int[]{6,9,9}) == 3;
    }

}
