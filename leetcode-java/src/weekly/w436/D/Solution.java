package weekly.w436.D;

/**
 * 3449. Maximize the Minimum Game Score
 *
 * https://leetcode.cn/contest/weekly-contest-436/problems/maximize-the-minimum-game-score/
 *
 * You are given an array points of size n and an integer m. There is another array gameScore of size n,
 * where gameScore[i] represents the score achieved at the ith game. Initially, gameScore[i] == 0 for all i.
 *
 * You start at index -1, which is outside the array (before the first position at index 0).
 * You can make at most m moves. In each move, you can either:
 *
 * Increase the index by 1 and add points[i] to gameScore[i].
 * Decrease the index by 1 and add points[i] to gameScore[i].
 * Note that the index must always remain within the bounds of the array after the first move.
 *
 * Return the maximum possible minimum value in gameScore after at most m moves.
 */

public class Solution {

    // 最大化最小值
    public long maxScore(int[] points, int m) {
        long l = 0, r = Long.MAX_VALUE;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (check(points, m, mid)) l = mid;
            else r = mid;
        }
        return l;
    }

    // 检查是否能通过 m 次操作使得 gameScore 中的值至少是 min
    private boolean check(int[] points, long m, long min) {
        // 对于 gameScore 中的每个位置都至少需要 min, 则至少需要 c = ceil(min / points[i]) 次操作
        //  从第一个数开始, 需要执行 c_0 = ceil(min / points[i]) 次操作, 其中包含一次 -1 到 0 的跳跃
        //  剩下的 k - 1 次需要在 0 和 1 之间完成. 当完成所有跳跃之后
        //  此时处于位置 i, 且 i + 1 已经执行了 pre = k - 1 次
        long[] ops = new long[points.length];
        for (int i = 0, pre = 0; i < points.length; i++) {
            long k = ((min + points[i] - 1) / points[i]) - pre;
            if (i == points.length - 1 && k <= 0) break; // 已经满足要求

            // 最少要执行一次, 且我们需要左右各操作一次
            k = Math.max(k, 1); m -= 2 * k - 1;
            if (m < 0) return false;
            pre = (int) (k - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{5, 3}, 8) == 12;
        assert new Solution().maxScore(new int[]{1, 8}, 10) == 5;
        assert new Solution().maxScore(new int[]{1, 2, 3}, 5) == 2;
        assert new Solution().maxScore(new int[]{2, 4}, 3) == 4;
    }

}
