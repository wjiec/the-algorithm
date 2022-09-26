package problem.p1927sumgame;

/**
 * 1927. Sum Game
 *
 * https://leetcode.cn/problems/sum-game/
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * You are given a string num of even length consisting of digits and '?' characters.
 * On each turn, a player will do the following if there is still at least one '?' in num:
 *
 * Choose an index i where num[i] == '?'.
 * Replace num[i] with any digit between '0' and '9'.
 * The game ends when there are no more '?' characters in num.
 *
 * For Bob to win, the sum of the digits in the first half of num must be equal to the sum of
 * the digits in the second half. For Alice to win, the sums must not be equal.
 *
 * For example, if the game ended with num = "243801", then Bob wins because 2+4+3 = 8+0+1.
 * If the game ended with num = "243803", then Alice wins because 2+4+3 != 8+0+3.
 *
 * Assuming Alice and Bob play optimally, return true if Alice will win and false if Bob will win.
 */

public class Solution {

    public boolean sumGame(String num) {
        int pq = 0, ps = 0, n = num.length();
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') pq++;
            else ps += num.charAt(i) - '0';
        }

        int sq = 0, ss = 0;
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') sq++;
            else ss += num.charAt(i) - '0';
        }

        return (pq + sq) % 2 == 1 || (ps - ss) != (sq - pq) * 9 / 2;
    }

    public static void main(String[] args) {
        assert !new Solution().sumGame("5023");
        assert new Solution().sumGame("25??");
        assert !new Solution().sumGame("?3295???");
        assert !new Solution().sumGame("?3295???");
    }

}
