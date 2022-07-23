package weekly.bw83.A;

/**
 * 6128. Best Poker Hand
 *
 * https://leetcode.cn/contest/biweekly-contest-83/problems/best-poker-hand/
 *
 * You are given an integer array ranks and a character array suits. You have 5 cards where
 * the ith card has a rank of ranks[i] and a suit of suits[i].
 *
 * The following are the types of poker hands you can make from best to worst:
 *
 * "Flush": Five cards of the same suit.
 * "Three of a Kind": Three cards of the same rank.
 * "Pair": Two cards of the same rank.
 * "High Card": Any single card.
 * Return a string representing the best type of poker hand you can make with the given cards.
 *
 * Note that the return values are case-sensitive.
 */

public class Solution {

    public String bestHand(int[] ranks, char[] suits) {
        boolean flush = true;
        for (int i = 1; i < suits.length; i++) {
            flush = flush && suits[i] == suits[i - 1];
        }
        if (flush) return "Flush";

        int[] map = new int[100];
        for (var n : ranks) map[n]++;
        for (var n : map) if (n >= 3) return "Three of a Kind";
        for (var n : map) if (n >= 2) return "Pair";
        return "High Card";
    }

    public static void main(String[] args) {
    }

}
