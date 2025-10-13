package weekly.bw164.B;

/**
 * Q2. Two-Letter Card Game
 *
 * https://leetcode.cn/contest/biweekly-contest-164/problems/two-letter-card-game/
 *
 * You are given a deck of cards represented by a string array cards, and each card
 * displays two lowercase letters.
 *
 * You are also given a letter x. You play a game with the following rules:
 *
 * Start with 0 points.
 * On each turn, you must find two compatible cards from the deck that both
 * contain the letter x in any position.
 * Remove the pair of cards and earn 1 point.
 *
 * The game ends when you can no longer find a pair of compatible cards.
 * Return the maximum number of points you can gain with optimal play.
 *
 * Two cards are compatible if the strings differ in exactly 1 position.
 */

public class Solution {

    public int score(String[] cards, char x) {
        final int MAX_N = 'j' - 'a' + 1;
        // 找到恰好有一个位置不同, 另一个位置是 x 的一对元素
        //  - x? 只能和 x# 组合, 且 ? 和 # 不能等于 x, 且 ? 和 # 互不相同
        //  - ?x 只能和 #x 组合, 且 ? 和 # 不能等于 x, 且 ? 和 # 互不相同
        //  - 剩下 xx, 我们优先让其与剩下的元素进行组合

        // 找到所有的 x? 情况
        int ans = 0, xx = 0;
        int[] pre = new int[MAX_N], suf = new int[MAX_N];
        for (var card : cards) {
            int a = card.charAt(0), b = card.charAt(1);
            if (a != x && b == x) pre[a - 'a']++;
            if (a == x && b != x) suf[b - 'a']++;
            if (a == x && b == x) xx++;
        }

        int preSum = 0, preMax = pre[0], sufSum = 0, sufMax = suf[0];
        for (var v : pre) { preSum += v; preMax = Math.max(preMax, v); }
        for (var v : suf) { sufSum += v; sufMax = Math.max(sufMax, v); }

        // 因为 xx 不能和 xx 组合, 但是 xx 可以和 x? 或者 ?x 组合
        //  - 所以我们需要保证 pre 和 suf 能组合的情况下优先消耗掉所有的 xx
        //
        // 对于所有的 x? 或者 ?x, 如果我们有 k 对组合, 在 xx 存在空闲的情况下
        // 我们可以将其 k 对组合拆开和 xx 进行组合, 与之对应, xx -= 2, ans += 1
        int preAns = Math.min(preSum / 2, preSum - preMax), sufAns = Math.min(sufSum / 2, sufSum - sufMax);
        ans += preAns + sufAns; preSum -= preAns * 2; sufSum -= sufAns * 2;

        // 剩下的 xx 与 x? 或者 ?x 进行组合
        int k = Math.min(xx, preSum + sufSum);
        ans += k; xx -= k;

        // 如果还有剩的 xx, 则拆开 x? 或者 ?x 与 xx 进行组合
        xx >>= 1;
        if (xx > 0) ans += Math.min(xx, preAns + sufAns);

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().score(new String[]{
            "ed","ba","aa","ec","eb","ae","aa","ac","ba","ab","ed","dd","db",
            "aa","dc","ca","ac","ae","db","cb","ea","bb","bc","be","cb","cc",
            "cd","ed","ec","ca","ed","bc","ac","de","bd","be","ba","bc","ca",
            "ad","cc","ce","ab","ca","ac","ac","ad","dc","be","de"
        }, 'e') == 8;
        assert new Solution().score(new String[]{
            "ab","aa","ab","bc","cc","bc","bb","ac","bc","bc","aa","aa","ba",
            "bc","cb","ba","ac","bb","cb","ac","cb","cb","ba","bc","ca","ba",
            "bb","cc","cc","ca","ab","bb","bc","ba","ac","bc","ac","ac","bc",
            "bb","bc","ac","bc","aa","ba","cc","ac","bb","ba","bb"
        }, 'b') == 16;
        assert new Solution().score(new String[]{"ba","ab","be","ee","bd"}, 'b') == 1;
        assert new Solution().score(new String[]{"ac","cb","ba","ac","bc"}, 'c') == 1;

        assert new Solution().score(new String[]{"ab","be"}, 'b') == 0;
        assert new Solution().score(new String[]{"aa","ab","ba","ac"}, 'a') == 2;
        assert new Solution().score(new String[]{"aa","ab","ba"}, 'a') == 1;
        assert new Solution().score(new String[]{"aa","ab","ba","ac"}, 'b') == 0;
    }

}
