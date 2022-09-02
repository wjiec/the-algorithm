package problem.p1753maximumscorefromremovingstones;

import common.Tag;

/**
 * 1753. Maximum Score From Removing Stones
 *
 * https://leetcode.cn/problems/maximum-score-from-removing-stones/
 *
 * You are playing a solitaire game with three piles of stones of sizes a, b, and c respectively.
 * Each turn you choose two different non-empty piles, take one stone from each, and add 1 point to
 * your score. The game stops when there are fewer than two non-empty piles (meaning there are no
 * more available moves).
 *
 * Given three integers a, b, and c, return the maximum score you can get.
 */

public class Solution {

    @Tag({"找规律"})
    public int maximumScore(int a, int b, int c) {
        int mi = Math.min(a, Math.min(b, c));
        int mx = Math.max(a, Math.max(b, c));

        return maxScore(mi, a + b + c - mi - mx, mx);
    }

    // a <= b <= c
    private int maxScore(int a, int b, int c) {
        if (a == 0) return b;
        if (a == b && b == c) return (a + b + c) / 2;
        if (a == b && a + b <= c) return Math.min(a + b, c);
        if (a == b && a + b > c) return c + (a + b - c) / 2;

        int score = Math.max(Math.min(b - a, c - a), 1);
        b -= score; c -= score;

        int mi = Math.min(a, Math.min(b, c));
        int mx = Math.max(a, Math.max(b, c));

        return score + maxScore(mi, a + b + c - mi - mx, mx);
    }

    public static void main(String[] args) {
        assert new Solution().maximumScore(6, 2, 1) == 3;
        assert new Solution().maximumScore(4, 4, 7) == 7;

        assert new Solution().maximumScore(2, 4, 6) == 6;
        assert new Solution().maximumScore(4, 4, 6) == 7;
        assert new Solution().maximumScore(1, 8, 8) == 8;
    }

}
