package weekly.w431.C;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

/**
 * 3413. Maximum Coins From K Consecutive Bags
 *
 * https://leetcode.cn/contest/weekly-contest-431/problems/maximum-coins-from-k-consecutive-bags/
 *
 * There are an infinite amount of bags on a number line, one bag for each coordinate.
 * Some of these bags contain coins.
 *
 * You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag
 * from li to ri contains ci coins.
 *
 * The segments that coins contain are non-overlapping.
 *
 * You are also given an integer k.
 *
 * Return the maximum amount of coins you can obtain by collecting k consecutive bags.
 */

public class Solution {

    private record Range(int l, int r, long c) {}

    /** @noinspection DataFlowIssue*/
    // 无限长的数组, 求长度为 k 的最大子数组和
    public long maximumCoins(int[][] coins, int k) {
        // 因为数组之间不重叠, 按照 [l, r, c] 的 左端点进行排序
        Arrays.sort(coins, Comparator.comparingInt(v -> v[0]));

        // 然后我们通过双指针维护一个长为 k 的窗口, 并计算窗口的和
        //  - 窗口如果在一个区间的中间, 我们将窗口往右移, 实际上并不会对结果造成影响
        //      - 右边必定会多一个值, 左边有可能失去一个值
        Deque<Range> window = new ArrayDeque<>();
        // 增加一个默认的区间使得我们可以方便的对区间进行维护
        window.add(new Range(Integer.MIN_VALUE, coins[0][0] - 1, 0));

        // 枚举所有区间, 使得当前所枚举的区间被完整覆盖
        long ans = 0, curr = 0;
        for (var coin : coins) {
            window.addLast(new Range(coin[0], coin[1], coin[2]));
            curr += (coin[1] - coin[0] + 1) * (long) coin[2];

            // 去除左边无法覆盖的区间, 首先计算当前窗口最左侧的位置
            int left = window.peekLast().r - k + 1;
            // 我们从窗口最左侧开始, 移除完全不在窗口之内的区间, 也就是区间的右端点比当前窗口的最左侧位置小
            while (!window.isEmpty() && window.peekFirst().r < left) {
                var removed = window.removeFirst();
                curr -= (removed.r - removed.l + 1) * removed.c;
            }

            // 剩下的就是包含整个区间或者一部分区间
            if (!window.isEmpty() && window.peekFirst().l < left) {
                var removed = window.removeFirst();
                // 包含不完整区间的话, 我们需要删除一部分的区间
                // 我们需要删除从 [window.first().l, left) 的区间
                window.addFirst(new Range(left, removed.r, removed.c));
                curr -= (left - removed.l) * removed.c;
            }

            // 结果取最大值
            ans = Math.max(ans, curr);
        }

        window.clear(); curr = 0;
        // 同时枚举使得左侧区间获得最大覆盖
        window.add(new Range(coins[coins.length - 1][1] + 1, Integer.MAX_VALUE, 0));
        for (int i = coins.length - 1; i >= 0; i--) {
            var coin = coins[i];

            window.addFirst(new Range(coin[0], coin[1], coin[2]));
            curr += (coin[1] - coin[0] + 1) * (long) coin[2];

            int right = coin[0] + k - 1;
            while (!window.isEmpty() && window.peekLast().l > right) {
                var removed = window.removeLast();
                curr -= (removed.r - removed.l + 1) * removed.c;
            }

            if (!window.isEmpty() && window.peekLast().r > right) {
                var removed = window.removeLast();
                window.addLast(new Range(removed.l, right, removed.c));
                curr -= (removed.r - right) * removed.c;
            }

            ans = Math.max(ans, curr);
        }

        return ans;
    }

    private static class Optimization {
        private long maximum(int[][] coins, int k) {
            long ans = 0, curr = 0; int l = 0;
            for (var coin : coins) {
                curr += (coin[1] - coin[0] + 1L) * coin[2];
                // 如果窗口左侧区间的右端点完全小于当前窗口的左侧位置, 则需要删除
                for (; coins[l][1] < coin[1] - k + 1; l++) {
                    curr -= (coins[l][1] - coins[l][0] + 1L) * coins[l][2];
                }
                // 计算当前未完全覆盖的区间大小, 此时 coins[l][0] 有可能大于窗口左侧的位置
                long uncovered = Math.max(((coin[1] - k + 1L) - coins[l][0]) * coins[l][2], 0);
                ans = Math.max(ans, curr - uncovered);
            }

            return ans;
        }

        public long maximumCoins(int[][] coins, int k) {
            Arrays.sort(coins, Comparator.comparingInt(v -> v[0]));

            long ans = maximum(coins, k);
            for (int l = 0, r = coins.length - 1; l <= r; l++, r--) {
                int a = coins[l][0], b = coins[l][1], c = coins[l][2];
                int x = coins[r][0], y = coins[r][1], z = coins[r][2];

                coins[l][0] = -y; coins[l][1] = -x; coins[l][2] = z;
                coins[r][0] = -b; coins[r][1] = -a; coins[r][2] = c;
            }
            return Math.max(ans, maximum(coins, k));
        }
    }

    public static void main(String[] args) {
        assert new Optimization().maximumCoins(new int[][]{{8,12,13},{29,32,2},{13,15,2},{40,41,18},{42,48,18},{33,36,11},{37,38,6}}, 28) == 226;
        assert new Optimization().maximumCoins(new int[][]{{1,3,2}, {5,6,4}, {8,10,1}}, 4) == 10;

        assert new Solution().maximumCoins(new int[][]{{8,12,13},{29,32,2},{13,15,2},{40,41,18},{42,48,18},{33,36,11},{37,38,6}}, 28) == 226;
        assert new Solution().maximumCoins(new int[][]{{1,3,2}, {5,6,4}, {8,10,1}}, 4) == 10;
    }

}
