package problem.p1648selldiminishingvaluedcoloredballs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1648. Sell Diminishing-Valued Colored Balls
 *
 * https://leetcode.cn/problems/sell-diminishing-valued-colored-balls/
 *
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color
 * you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for
 * the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is
 * then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith
 * color that you initially own. You are also given an integer orders, which represents the total number of
 * balls that the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls.
 * As the answer may be too large, return it modulo 109 + 7.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0}); // guard, [value, count]
        for (var n : inventory) {
            if (stack.peek()[0] != n) {
                stack.push(new int[]{n, 1});
            } else stack.peek()[1]++;
        }

        long ans = 0, MOD = 1_000_000_007;
        while (orders != 0) {
            int[] a = stack.pop();
            int[] b = stack.pop();

            if (a[1] == 1) { // 一次卖一种球
                // 一次最多卖x个球
                long x = Math.min(a[0] - b[0], orders);

                // 赚到的钱是 x * (a1 + an) / 2
                ans = (ans + x * (a[0] + a[0] - x + 1) / 2) % MOD;

                // 卖出了x个球, 同时b球增加x个
                // 如果a球卖不完, 说明下一次就会退出循环
                // 如果a球卖完了, 就不需要再维护a球的数据了
                orders -= x; stack.push(new int[]{b[0], b[1] + 1});
            } else { // 一次卖多种球
                // 检查还能卖几轮的球
                long r = Math.min(orders / a[1], a[0] - b[0]);
                ans = (ans + a[1] * (r * (a[0] + a[0] - r + 1) / 2)) % MOD;
                orders -= r * a[1]; a[0] -= r; // 卖出r轮的球
                // 检查是否还能卖最后一轮的球
                if (orders < a[1]) {
                    ans = (ans + (long) orders * a[0]) % MOD;
                    orders -= orders;
                } else stack.push(new int[]{b[0], b[1] + a[1]});
            }
        }

        return (int) (ans % MOD);
    }

    private static class BinarySearch {
        public int maxProfit(int[] inventory, int orders) {
            int l = 0, r = 0;
            for (var n : inventory) r = Math.max(r, n);

            while (l < r) {
                int mid = l + (r - l) / 2;
                if (check(inventory, mid, orders)) l = mid + 1;
                else r = mid;
            }

            long ans = 0, MOD = 1_000_000_007;
            for (var n : inventory) {
                if (n > l) {
                    // 从 n 取到 l+1, n * (a1 + an) / 2
                    ans = (ans + (long) (n - l) * (n + l+1) / 2) % MOD;
                    orders -= n - l;
                }
            }

            ans += (long) l * orders;
            return (int) (ans % MOD);
        }

        // 检查以最低price价格卖出是否能凑到orders个订单(不包含price)
        private boolean check(int[] inventory, int price, int orders) {
            for (var n : inventory) {
                orders -= Math.max(n - price, 0);
                if (orders <= 0) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{3,5}, 6) == 19;
        assert new Solution().maxProfit(new int[]{2,5}, 4) == 14;
        assert new Solution().maxProfit(new int[]{2,8,4,10,6}, 20) == 110;
        assert new Solution().maxProfit(new int[]{1000000000}, 1000000000) == 21;
        assert new Solution().maxProfit(new int[]{550000,550000}, 1000000) == 497900;

        assert new BinarySearch().maxProfit(new int[]{3,5}, 6) == 19;
        assert new BinarySearch().maxProfit(new int[]{2,5}, 4) == 14;
        assert new BinarySearch().maxProfit(new int[]{2,8,4,10,6}, 20) == 110;
        assert new BinarySearch().maxProfit(new int[]{1000000000}, 1000000000) == 21;
        assert new BinarySearch().maxProfit(new int[]{550000,550000}, 1000000) == 497900;
    }

}
