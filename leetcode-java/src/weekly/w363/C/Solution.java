package weekly.w363.C;

import java.util.List;

/**
 * 2861. Maximum Number of Alloys
 *
 * https://leetcode.cn/contest/weekly-contest-363/problems/maximum-number-of-alloys/
 *
 * You are the owner of a company that creates alloys using various types of metals.
 * There are n different types of metals available, and you have access to k machines that
 * can be used to create alloys. Each machine requires a specific amount of each metal type
 * to create an alloy.
 *
 * For the ith machine to create an alloy, it needs composition[i][j] units of metal of type j.
 * Initially, you have stock[i] units of metal type i, and purchasing one unit of metal type i costs cost[i] coins.
 *
 * Given integers n, k, budget, a 1-indexed 2D array composition, and 1-indexed arrays
 * stock and cost, your goal is to maximize the number of alloys the company can create
 * while staying within the budget of budget coins.
 *
 * All alloys must be created with the same machine.
 *
 * Return the maximum number of alloys that the company can create.
 */

public class Solution {

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        long ans = 0;
        for (var machine : composition) {
            long l = 0, r = Integer.MAX_VALUE, curr = 0;
            while (l < r) {
                long mid = l + (r - l) / 2;
                if (check(machine, stock, cost, mid) <= budget) {
                    curr = mid; l = mid + 1;
                } else r = mid;
            }
            ans = Math.max(ans, curr);
        }
        return (int) ans;
    }

    private long check(List<Integer> machine, List<Integer> stock, List<Integer> cost, long n) {
        long ans = 0;
        for (int i = 0; i < machine.size(); i++) {
            ans += Math.max((n * machine.get(i) - stock.get(i)), 0) * cost.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumberOfAlloys(3, 2, 15, List.of(List.of(1,1,1), List.of(1,1,10)), List.of(0,0,0), List.of(1,2,3)) == 2;
        assert new Solution().maxNumberOfAlloys(3, 2, 15, List.of(List.of(1,1,1), List.of(1,1,10)), List.of(0,0,100), List.of(1,2,3)) == 5;
        assert new Solution().maxNumberOfAlloys(2, 3, 10, List.of(List.of(2,1), List.of(1,2), List.of(1,1)), List.of(1,1), List.of(5,5)) == 2;
    }

}
