package problem.p1798maximumnumberofconsecutivevaluesyoucanmake;

import java.util.Arrays;

/**
 * 1798. Maximum Number of Consecutive Values You Can Make
 *
 * https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/
 *
 * You are given an integer array coins of length n which represents the n coins that you own.
 * The value of the ith coin is coins[i]. You can make some value x if you can choose some of
 * your n coins such that their values sum up to x.
 *
 * Return the maximum number of consecutive integer values that you can make with
 * your coins starting from and including 0.
 *
 * Note that you may have multiple coins of the same value.
 */

public class Solution {

    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int ans = 0;
        // 若是我们能构建 [0, x] 之间的所有整数, 那我们可以通过
        // 增加一个 y 则可以构建 [y, x + y] 之间的所有整数
        for (var coin : coins) {
            // 如果选择的 y 可以使得范围 [y, x + y] 能囊括
            // [x + 1] 的话, 则可以连接 [0, x] 得到更大的范围 [0, x + y]
            // 如果不能, 则说明中间有断开的地方
            if (coin > ans + 1) break;
            ans += coin;
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        assert new Solution().getMaximumConsecutive(new int[]{1,3}) == 2;
        assert new Solution().getMaximumConsecutive(new int[]{1,1,1,4}) == 8;
        assert new Solution().getMaximumConsecutive(new int[]{1,4,10,3,1}) == 20;
    }

}
