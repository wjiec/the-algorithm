package offer2.p73;

/**
 * 剑指 Offer II 073. 狒狒吃香蕉
 *
 * https://leetcode.cn/problems/nZZqjQ/
 *
 * 狒狒喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 狒狒可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
 * 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。
 *
 * 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (var v : piles) r = Math.max(r, v);

        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 当这个速度能吃完香蕉，则可以更慢一点吃
            if (check(piles, h, mid)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private boolean check(int[] array, int h, int s) {
        for (var v : array) {
            h -= (v + s - 1) / s;
            if (h < 0) return false;
        }
        return h >= 0;
    }

    public static void main(String[] args) {
        assert new Solution().minEatingSpeed(new int[]{312884470}, 312884469) == 2;

        assert new Solution().minEatingSpeed(new int[]{3,6,7,11}, 8) == 4;
        assert new Solution().minEatingSpeed(new int[]{30,11,23,4,20}, 5) == 30;
        assert new Solution().minEatingSpeed(new int[]{30,11,23,4,20}, 6) == 23;
    }

}
