package weekly.w331.A;

import java.util.PriorityQueue;

/**
 * 2558. Take Gifts From the Richest Pile
 *
 * https://leetcode.cn/problems/take-gifts-from-the-richest-pile/
 *
 * You are given an integer array gifts denoting the number of gifts in various piles.
 * Every second, you do the following:
 *
 * Choose the pile with the maximum number of gifts.
 * If there is more than one pile with the maximum number of gifts, choose any.
 * Leave behind the floor of the square root of the number of gifts in the pile.
 * Take the rest of the gifts.
 *
 * Return the number of gifts remaining after k seconds.
 */

public class Solution {

    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (var v : gifts) pq.add(v);
        while (k-- > 0) pq.add((int) Math.sqrt(pq.remove()));

        long ans = 0;
        while (!pq.isEmpty()) ans += pq.remove();
        return ans;
    }

    public static void main(String[] args) {
    }

}
