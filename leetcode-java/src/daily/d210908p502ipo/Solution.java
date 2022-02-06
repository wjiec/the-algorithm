package daily.d210908p502ipo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 502. IPO
 *
 * https://leetcode-cn.com/problems/ipo/
 *
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO.
 *
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i]
 * and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain
 * its pure profit and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize your final capital,
 * and return the final maximized capital.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 */

public class Solution {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int l = profits.length, curr = 0;
        int[][] map = new int[l][2];
        for (int i = 0; i < l; i++) {
            map[i][0] = capital[i];
            map[i][1] = profits[i];
        }
        Arrays.sort(map, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            while (curr < l && map[curr][0] <= w) {
                queue.add(map[curr][1]);
                curr++;
            }
            if (!queue.isEmpty()) {
                w += queue.remove();
            } else {
                break;
            }
        }
        return w;
    }

    public static void main(String[] args) {
        assert new Solution().findMaximizedCapital(2, 0, new int[]{1,2,3}, new int[]{0,1,1}) == 4;
        assert new Solution().findMaximizedCapital(3, 0, new int[]{1,2,3}, new int[]{0,1,2}) == 6;
    }

}
