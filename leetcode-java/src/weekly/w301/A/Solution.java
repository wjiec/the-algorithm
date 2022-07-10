package weekly.w301.A;

import java.util.PriorityQueue;

/**
 * 6112. Minimum Amount of Time to Fill Cups
 *
 * https://leetcode.cn/contest/weekly-contest-301/problems/minimum-amount-of-time-to-fill-cups/
 *
 * You have a water dispenser that can dispense cold, warm, and hot water. Every second, you can either
 * fill up 2 cups with different types of water, or 1 cup of any type of water.
 *
 * You are given a 0-indexed integer array amount of length 3 where amount[0], amount[1], and amount[2]
 * denote the number of cold, warm, and hot water cups you need to fill respectively.
 *
 * Return the minimum number of seconds needed to fill up all the cups.
 */

public class Solution {

    public int fillCups(int[] amount) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        if (amount[0] != 0) q.add(amount[0]);
        if (amount[1] != 0) q.add(amount[1]);
        if (amount[2] != 0) q.add(amount[2]);

        int ans = 0;
        while (!q.isEmpty()) {
            int a = q.remove();
            Integer b = q.poll();
            if (b == null) {
                ans += a;
                break;
            }

            ans++;
            if (--a != 0) q.add(a);
            if (--b != 0) q.add(b);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().fillCups(new int[]{1, 4, 2}) == 4;
        assert new Solution().fillCups(new int[]{5, 4, 4}) == 7;
        assert new Solution().fillCups(new int[]{5, 0, 0}) == 5;
    }

}
