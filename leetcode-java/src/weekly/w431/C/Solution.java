package weekly.w431.C;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    // 无限长的数组, 求长度为 k 的最大子数组和
    public long maximumCoins(int[][] coins, int k) {
        // 因为数组之间不重叠, 按照 [l, r, c] 的 左端点进行排序
        Arrays.sort(coins, Comparator.comparingInt(v -> v[0]));

        // 我们为所有相邻区间填充值为 0 的区间
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(Integer.MIN_VALUE, coins[0][0] - 1, 0));
        for (int i = 0; i < coins.length; i++) {
            if (i > 0) ranges.add(new Range(coins[i - 1][1] + 1, coins[i][0] - 1, 0));
            ranges.add(new Range(coins[i][0], coins[i][1], coins[i][2]));
        }
        ranges.add(new Range(coins[coins.length - 1][1] + 1, Integer.MAX_VALUE, 0));

        long ans = 0, curr = 0;
        // 然后我们通过双指针维护一个长为 k 的窗口, 并计算窗口的和
        PrettyPrinter.println(ranges);
        for (int i = 1, ri = 1; i < ranges.size(); i++) {
            // 在从前一个区间到当前区间的过程中, 我们从 [prevStart, prevEnd] 跳到了 [start, end]
            int start = ranges.get(i).l, end = start + k - 1;
            int prevStart = ranges.get(i - 1).l, prevEnd = prevStart + k - 1;
            //
            //     |................|         ->         |................|
            //     ^ prevStart      ^ prevEnd            ^ start          ^ end
            //

            // 从 prevStart -> start 的过程中, 实际上是移除了整个 ranges[i - 1] 区间
            ans -= (ranges.get(i - 1).r - ranges.get(i - 1).l + 1) * ranges.get(i - 1).c;

            // 接下来就是计算从 prevEnd -> end 跨越了多少个区间以及叠加了多少和
            //  - prevEnd 所在的区间索引是 ri
            while (ri < ranges.size()) {
                
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumCoins(new int[][]{{1,3,2}, {5,6,4}, {8,10,1}}, 4) == 10;
    }

}
