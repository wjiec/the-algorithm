package problem.p950revealcardsinincreasingorder;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 950. Reveal Cards In Increasing Order
 *
 * https://leetcode.cn/problems/reveal-cards-in-increasing-order/
 *
 * You are given an integer array deck. There is a deck of cards where every card has a unique integer.
 * The integer on the ith card is deck[i].
 *
 * You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
 *
 * You will do the following steps repeatedly until all cards are revealed:
 *
 * Take the top card of the deck, reveal it, and take it out of the deck.
 * If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
 * If there are still unrevealed cards, go back to step 1. Otherwise, stop.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 *
 * Note that the first entry in the answer is considered to be the top of the deck.
 */

public class Solution {

    public int[] deckRevealedIncreasing(int[] deck) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (var i = 0; i < deck.length; i++) deque.add(i);

        Arrays.sort(deck);
        int[] ans = new int[deck.length];
        for (int n : deck) {
            ans[deque.remove()] = n;
            if (!deque.isEmpty()) {
                deque.add(deque.remove());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().deckRevealedIncreasing(new int[]{
            17,13,11,2,3,5,7
        }), new int[]{2,13,3,11,5,17,7});

        assert Checker.check(new Solution().deckRevealedIncreasing(new int[]{
            1,1000
        }), new int[]{1,1000});
    }

}
