package daily.d220322p2038removecoloredpiecesifbothneighborsarethesamecolor;

/**
 * 2038. Remove Colored Pieces if Both Neighbors are the Same Color
 *
 * https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 *
 * There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'.
 * You are given a string colors of length n where colors[i] is the color of the ith piece.
 *
 * Alice and Bob are playing a game where they take alternating turns removing pieces from the line.
 * In this game, Alice moves first.
 *
 * Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'.
 * She is not allowed to remove pieces that are colored 'B'.
 *
 * Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'.
 * He is not allowed to remove pieces that are colored 'A'.
 *
 * Alice and Bob cannot remove pieces from the edge of the line.
 * If a player cannot make a move on their turn, that player loses and the other player wins.
 * Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.
 */

public class Solution {

    public boolean winnerOfGame(String colors) {
        char prev = ' ';
        int a = 0, b = 0, p = 0, n = colors.length();
        for (int i = 0; i < n; i++) {
            if (colors.charAt(i) != prev) {
                int count = i - p;
                if (count >= 3) {
                    switch (prev) {
                        case 'A' -> a += count - 2;
                        case 'B' -> b += count - 2;
                    }
                }

                p = i;
                prev = colors.charAt(i);
            }
        }

        if (colors.length() - p >= 3) {
            switch (prev) {
                case 'A' -> a += colors.length() - p - 2;
                case 'B' -> b += colors.length() - p - 2;
            }
        }

        return a > b;
    }

    public static void main(String[] args) {
        assert new Solution().winnerOfGame("AAABABB");
        assert !new Solution().winnerOfGame("AA");
        assert !new Solution().winnerOfGame("AABB");
        assert !new Solution().winnerOfGame("ABBBBBBBAAA");
        assert new Solution().winnerOfGame("AAABABBABBABABAAAABABBABBBBABBABAABABBABBABBBBABBABABBABABB" +
            "ABBABBBABBABABAAABAABBABBAAAAABBABABABABBABBBABBABBABABBBABABBAAAAAAAAAAAAAA");
    }

}
