package weekly.w480.C;

/**
 * Q3. Minimum Moves to Balance Circular Array
 *
 * https://leetcode.cn/contest/weekly-contest-480/problems/minimum-moves-to-balance-circular-array/
 *
 * You are given a circular array balance of length n, where balance[i] is the net balance of person i.
 *
 * In one move, a person can transfer exactly 1 unit of balance to either their left or right neighbor.
 *
 * Return the minimum number of moves required so that every person has a non-negative balance.
 * If it is impossible, return -1.
 *
 * Note: You are guaranteed that at most 1 index has a negative balance initially.
 */

public class Solution {

    public long minMoves(int[] balance) {
        long sum = 0; int neg = -1, n = balance.length;
        for (int i = 0; i < n; i++) {
            sum += balance[i];
            if (balance[i] < 0) neg = i;
        }
        if (sum < 0) return -1;
        if (neg == -1) return 0;

        // 从 neg 的两边往 neg 进行转移
        long ans = 0;
        for (int l = neg - 1, r = neg + 1, cost = 1; balance[neg] < 0; l--, r++, cost++) {
            int moveL = Math.min(balance[(l + n) % n], -balance[neg]);
            ans += (long) moveL * cost; balance[neg] += moveL;

            int moveR = Math.min(balance[(r + n) % n], -balance[neg]);
            ans += (long) moveR * cost; balance[neg] += moveR;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
