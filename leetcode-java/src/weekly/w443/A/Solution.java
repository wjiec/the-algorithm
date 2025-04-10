package weekly.w443.A;

/**
 * 3502. Minimum Cost to Reach Every Position
 *
 * https://leetcode.cn/contest/weekly-contest-443/problems/minimum-cost-to-reach-every-position/
 *
 * You are given an integer array cost of size n. You are currently
 * at position n (at the end of the line) in a line of n + 1 people (numbered from 0 to n).
 *
 * You wish to move forward in the line, but each person in front of you charges a specific
 * amount to swap places. The cost to swap with person i is given by cost[i].
 *
 * You are allowed to swap places with people as follows:
 *
 * If they are in front of you, you must pay them cost[i] to swap with them.
 * If they are behind you, they can swap with you for free.
 *
 * Return an array answer of size n, where answer[i] is the minimum total cost
 * to reach each position i in the line.
 */

public class Solution {

    public int[] minCosts(int[] cost) {
        int[] ans = new int[cost.length];
        for (int i = 0, mi = cost[0]; i < cost.length; i++) {
            ans[i] = mi = Math.min(mi, cost[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
